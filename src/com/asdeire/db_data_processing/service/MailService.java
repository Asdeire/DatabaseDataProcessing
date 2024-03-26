package com.asdeire.db_data_processing.service;

import com.asdeire.db_data_processing.data_access.DeliveryDao;
import com.asdeire.db_data_processing.data_access.LetterDao;
import com.asdeire.db_data_processing.data_access.MailboxDao;
import com.asdeire.db_data_processing.data_access.UserDao;
import com.asdeire.db_data_processing.entity.Delivery;
import com.asdeire.db_data_processing.entity.Letter;
import com.asdeire.db_data_processing.entity.Mailbox;
import com.asdeire.db_data_processing.entity.User;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class MailService {

    private final UserDao userDao;
    private final LetterDao letterDao;
    private final MailboxDao mailboxDao;
    private final DeliveryDao deliveryDao;

    public MailService(Connection connection) {
        this.userDao = new UserDao(connection);
        this.letterDao = new LetterDao(connection);
        this.mailboxDao = new MailboxDao(connection);
        this.deliveryDao = new DeliveryDao(connection);
    }

    // User CRUD operations
    public void addUser(UUID userId, String username, String email) throws SQLException {
        User user = User.builder()
                .userId(userId)
                .username(username)
                .email(email)
                .build();
        userDao.save(user);
    }

    public List<User> getAllUsers() throws SQLException {
        return userDao.findAll();
    }

    public User getUserById(UUID userId) throws SQLException {
        return userDao.findById(userId);
    }

    public void updateUser(UUID userId, String username, String email) throws SQLException {
        User user = User.builder()
                .userId(userId)
                .username(username)
                .email(email)
                .build();
        userDao.update(user);
    }

    // User CRUD operations
    public void deleteUser(UUID userId) throws SQLException {
        User user = User.builder()
                .userId(userId)
                .build();
        userDao.delete(user);
    }


    // Letter CRUD operations
    public void addLetter(UUID letterId, UUID senderId, UUID recipientId, String subject, String content) throws SQLException {
        Letter letter = Letter.builder()
                .letterId(letterId)
                .senderId(senderId)
                .recipientId(recipientId)
                .subject(subject)
                .content(content)
                .build();
        letterDao.save(letter);
    }

    public List<Letter> getAllLetters() throws SQLException {
        return letterDao.findAll();
    }

    public Letter getLetterById(UUID letterId) throws SQLException {
        return letterDao.findById(letterId);
    }

    public void updateLetter(UUID letterId, UUID senderId, UUID recipientId, String subject, String content) throws SQLException {
        Letter letter = Letter.builder()
                .letterId(letterId)
                .senderId(senderId)
                .recipientId(recipientId)
                .subject(subject)
                .content(content)
                .build();
        letterDao.update(letter);
    }

    public void deleteLetter(UUID letterId) throws SQLException {
        Letter letter = Letter.builder()
                .letterId(letterId)
                .build();
        letterDao.delete(letter);
    }

    // Mailbox CRUD operations
    public void addMailbox(UUID mailboxId, UUID userId, String address) throws SQLException {
        Mailbox mailbox = Mailbox.builder()
                .mailboxId(mailboxId)
                .userId(userId)
                .address(address)
                .build();
        mailboxDao.save(mailbox);
    }

    public List<Mailbox> getAllMailboxes() throws SQLException {
        return mailboxDao.findAll();
    }

    public Mailbox getMailboxById(UUID mailboxId) throws SQLException {
        return mailboxDao.findById(mailboxId);
    }

    public void updateMailbox(UUID mailboxId, UUID userId, String address) throws SQLException {
        Mailbox mailbox = Mailbox.builder()
                .mailboxId(mailboxId)
                .userId(userId)
                .address(address)
                .build();
        mailboxDao.update(mailbox);
    }

    public void deleteMailbox(UUID mailboxId) throws SQLException {
        Mailbox mailbox = Mailbox.builder()
                .mailboxId(mailboxId)
                .build();
        mailboxDao.delete(mailbox);
    }

    // Delivery CRUD operations
    public void addDelivery(UUID deliveryId, UUID letterId, UUID mailboxId, LocalDateTime deliveryDate) throws SQLException {
        Delivery delivery = Delivery.builder()
                .deliveryId(deliveryId)
                .letterId(letterId)
                .mailboxId(mailboxId)
                .deliveryDate(deliveryDate)
                .build();
        deliveryDao.save(delivery);
    }

    public List<Delivery> getAllDeliveries() throws SQLException {
        return deliveryDao.findAll();
    }

    public Delivery getDeliveryById(UUID deliveryId) throws SQLException {
        return deliveryDao.findById(deliveryId);
    }

    public void updateDelivery(UUID deliveryId, UUID letterId, UUID mailboxId, LocalDateTime deliveryDate) throws SQLException {
        Delivery delivery = Delivery.builder()
                .deliveryId(deliveryId)
                .letterId(letterId)
                .mailboxId(mailboxId)
                .deliveryDate(deliveryDate)
                .build();
        deliveryDao.update(delivery);
    }

    public void deleteDelivery(UUID deliveryId) throws SQLException {
        Delivery delivery = Delivery.builder()
                .deliveryId(deliveryId)
                .build();
        deliveryDao.delete(delivery);
    }
}
