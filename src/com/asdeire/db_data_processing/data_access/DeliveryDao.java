package com.asdeire.db_data_processing.data_access;
import com.asdeire.db_data_processing.entity.Delivery;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DeliveryDao {

    private final Connection connection;

    public DeliveryDao(Connection connection) {
        this.connection = connection;
    }

    public void save(Delivery delivery) throws SQLException {
        String query = "INSERT INTO Deliveries (delivery_id, letter_id, mailbox_id, delivery_date) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, delivery.getDeliveryId());
            statement.setObject(2, delivery.getLetterId());
            statement.setObject(3, delivery.getMailboxId());
            statement.setTimestamp(4, Timestamp.valueOf(delivery.getDeliveryDate()));
            statement.executeUpdate();
        }
    }

    public Delivery findById(UUID deliveryId) throws SQLException {
        String query = "SELECT * FROM Deliveries WHERE delivery_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, deliveryId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return extractDeliveryFromResultSet(resultSet);
                }
            }
        }
        return null;
    }

    public List<Delivery> findAll() throws SQLException {
        List<Delivery> deliveries = new ArrayList<>();
        String query = "SELECT * FROM Deliveries";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                deliveries.add(extractDeliveryFromResultSet(resultSet));
            }
        }
        return deliveries;
    }

    public void update(Delivery delivery) throws SQLException {
        String query = "UPDATE Deliveries SET letter_id = ?, mailbox_id = ?, delivery_date = ? WHERE delivery_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, delivery.getLetterId());
            statement.setObject(2, delivery.getMailboxId());
            statement.setTimestamp(3, Timestamp.valueOf(delivery.getDeliveryDate()));
            statement.setObject(4, delivery.getDeliveryId());
            statement.executeUpdate();
        }
    }

    public void delete(Delivery delivery) throws SQLException {
        String query = "DELETE FROM Deliveries WHERE delivery_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, delivery.getDeliveryId());
            statement.executeUpdate();
        }
    }

    private Delivery extractDeliveryFromResultSet(ResultSet resultSet) throws SQLException {
        Delivery.DeliveryBuilder builder = Delivery.builder();
        builder
                .deliveryId((UUID) resultSet.getObject("delivery_id"))
                .letterId((UUID) resultSet.getObject("letter_id"))
                .mailboxId((UUID) resultSet.getObject("mailbox_id"))
                .deliveryDate(resultSet.getTimestamp("delivery_date").toLocalDateTime());
        return builder.build();
    }
}
