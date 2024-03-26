package com.asdeire.db_data_processing.data_access;
import com.asdeire.db_data_processing.entity.Mailbox;
import com.asdeire.db_data_processing.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MailboxDao {

    private final Connection connection;

    public MailboxDao(Connection connection) {
        this.connection = connection;
    }

    public void save(Mailbox mailbox) throws SQLException {
        String query = "INSERT INTO Mailboxes (mailbox_id, user_id, address) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, mailbox.getMailboxId());
            statement.setObject(2, mailbox.getUserId());
            statement.setString(3, mailbox.getAddress());
            statement.executeUpdate();
        }
    }

    public Mailbox findById(UUID mailboxId) throws SQLException {
        String query = "SELECT * FROM Mailboxes WHERE mailbox_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, mailboxId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return extractMailboxFromResultSet(resultSet);
                }
            }
        }
        return null;
    }

    public List<Mailbox> findAll() throws SQLException {
        List<Mailbox> mailboxes = new ArrayList<>();
        String query = "SELECT * FROM Mailboxes";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                mailboxes.add(extractMailboxFromResultSet(resultSet));
            }
        }
        return mailboxes;
    }

    public void update(Mailbox mailbox) throws SQLException {
        String query = "UPDATE Mailboxes SET user_id = ?, address = ? WHERE mailbox_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, mailbox.getUserId());
            statement.setString(2, mailbox.getAddress());
            statement.setObject(3, mailbox.getMailboxId());
            statement.executeUpdate();
        }
    }

    public void delete(Mailbox mailbox) throws SQLException {
        String query = "DELETE FROM Mailboxes WHERE mailbox_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, mailbox.getMailboxId());
            statement.executeUpdate();
        }
    }

    private Mailbox extractMailboxFromResultSet(ResultSet resultSet) throws SQLException {
        Mailbox.MailboxBuilder builder = Mailbox.builder();
        builder
                .mailboxId((UUID) resultSet.getObject("mailbox_id"))
                .userId((UUID) resultSet.getObject("user_id"))
                .address(resultSet.getString("address"));
        return builder.build();
    }

}
