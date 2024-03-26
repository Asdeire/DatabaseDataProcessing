package com.asdeire.db_data_processing.data_access;
import com.asdeire.db_data_processing.entity.Letter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LetterDao {

    private final Connection connection;

    public LetterDao(Connection connection) {
        this.connection = connection;
    }

    public void save(Letter letter) throws SQLException {
        String query = "INSERT INTO Letters (letter_id, sender_id, recipient_id, subject, content) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, letter.getLetterId());
            statement.setObject(2, letter.getSenderId());
            statement.setObject(3, letter.getRecipientId());
            statement.setString(4, letter.getSubject());
            statement.setString(5, letter.getContent());
            statement.executeUpdate();
        }
    }

    public Letter findById(UUID letterId) throws SQLException {
        String query = "SELECT * FROM Letters WHERE letter_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, letterId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return extractLetterFromResultSet(resultSet);
                }
            }
        }
        return null;
    }

    public List<Letter> findAll() throws SQLException {
        List<Letter> letters = new ArrayList<>();
        String query = "SELECT * FROM Letters";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                letters.add(extractLetterFromResultSet(resultSet));
            }
        }
        return letters;
    }

    public void update(Letter letter) throws SQLException {
        String query = "UPDATE Letters SET sender_id = ?, recipient_id = ?, subject = ?, content = ? WHERE letter_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, letter.getSenderId());
            statement.setObject(2, letter.getRecipientId());
            statement.setString(3, letter.getSubject());
            statement.setString(4, letter.getContent());
            statement.setObject(5, letter.getLetterId());
            statement.executeUpdate();
        }
    }

    public void delete(Letter letter) throws SQLException {
        String query = "DELETE FROM Letters WHERE letter_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, letter.getLetterId());
            statement.executeUpdate();
        }
    }

    private Letter extractLetterFromResultSet(ResultSet resultSet) throws SQLException {
        Letter.LetterBuilder builder = Letter.builder();
        builder
                .letterId((UUID) resultSet.getObject("letter_id"))
                .senderId((UUID) resultSet.getObject("sender_id"))
                .recipientId((UUID) resultSet.getObject("recipient_id"))
                .subject(resultSet.getString("subject"))
                .content(resultSet.getString("content"));
        return builder.build();
    }
}

