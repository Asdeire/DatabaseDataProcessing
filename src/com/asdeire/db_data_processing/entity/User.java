package com.asdeire.db_data_processing.entity;
import java.util.UUID;

public class User {

    private UUID userId;
    private String username;
    private String email;

    // Private constructor to enforce the use of the builder
    private User(UserBuilder builder) {
        this.userId = builder.userId;
        this.username = builder.username;
        this.email = builder.email;
    }

    // Getters
    public UUID getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    // Builder class
    public static class UserBuilder {
        private UUID userId;
        private String username;
        private String email;

        public UserBuilder() {
            this.userId = UUID.randomUUID();
        }

        public UserBuilder userId(UUID userId) {
            this.userId = userId;
            return this;
        }

        public UserBuilder username(String username) {
            this.username = username;
            return this;
        }

        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }
}

