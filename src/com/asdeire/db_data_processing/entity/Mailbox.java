package com.asdeire.db_data_processing.entity;
import java.util.UUID;

public class Mailbox {

    private UUID mailboxId;
    private UUID userId;
    private String address;

    private Mailbox(MailboxBuilder builder) {
        this.mailboxId = builder.mailboxId;
        this.userId = builder.userId;
        this.address = builder.address;
    }

    public UUID getMailboxId() {
        return mailboxId;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getAddress() {
        return address;
    }

    public static class MailboxBuilder {
        private UUID mailboxId;
        private UUID userId;
        private String address;

        public MailboxBuilder() {
            this.mailboxId = UUID.randomUUID();
        }

        public MailboxBuilder mailboxId(UUID mailboxId) {
            this.mailboxId = mailboxId;
            return this;
        }

        public MailboxBuilder userId(UUID userId) {
            this.userId = userId;
            return this;
        }

        public MailboxBuilder address(String address) {
            this.address = address;
            return this;
        }

        public Mailbox build() {
            return new Mailbox(this);
        }
    }
    public static MailboxBuilder builder(){
        return new MailboxBuilder();
    }
}
