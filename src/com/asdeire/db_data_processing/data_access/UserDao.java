package com.asdeire.db_data_processing.data_access;
import com.asdeire.db_data_processing.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserDao {

    private final Connection connection;

    public UserDao(Connection connection) {
        this.connection = connection;
    }

    public void save(User user) throws SQLException {
        String query = "INSERT INTO Users (user_id, username, email) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, user.getUserId());
            statement.setString(2, user.getUsername());
            statement.setString(3, user.getEmail());
            statement.executeUpdate();
        }
    }

    public User findById(UUID userId) throws SQLException {
        String query = "SELECT * FROM Users WHERE user_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return extractUserFromResultSet(resultSet);
                }
            }
        }
        return null;
    }

    public List<User> findAll() throws SQLException {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM Users";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                users.add(extractUserFromResultSet(resultSet));
            }
        }
        return users;
    }

    public void update(User user) throws SQLException {
        String query = "UPDATE Users SET username = ?, email = ? WHERE user_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getEmail());
            statement.setObject(3, user.getUserId());
            statement.executeUpdate();
        }
    }

    public void delete(User user) throws SQLException {
        String query = "DELETE FROM Users WHERE user_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, user.getUserId());
            statement.executeUpdate();
        }
    }

    private User extractUserFromResultSet(ResultSet resultSet) throws SQLException {
        User.UserBuilder builder = User.builder();
        builder
                .userId((UUID) resultSet.getObject("user_id"))
                .username(resultSet.getString("username"))
                .email(resultSet.getString("email"));
        return builder.build();
    }
}
