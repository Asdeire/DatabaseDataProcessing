package com.asdeire.db_data_processing.entity;
import java.util.UUID;

public class Letter {

    private UUID letterId;
    private UUID senderId;
    private UUID recipientId;
    private String subject;
    private String content;

    private Letter(LetterBuilder builder) {
        this.letterId = builder.letterId;
        this.senderId = builder.senderId;
        this.recipientId = builder.recipientId;
        this.subject = builder.subject;
        this.content = builder.content;
    }

    public UUID getLetterId() {
        return letterId;
    }

    public UUID getSenderId() {
        return senderId;
    }

    public UUID getRecipientId() {
        return recipientId;
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }

    public static class LetterBuilder {
        private UUID letterId;
        private UUID senderId;
        private UUID recipientId;
        private String subject;
        private String content;

        public LetterBuilder() {
            this.letterId = UUID.randomUUID();
        }

        public LetterBuilder letterId(UUID letterId) {
            this.letterId = letterId;
            return this;
        }

        public LetterBuilder senderId(UUID senderId) {
            this.senderId = senderId;
            return this;
        }

        public LetterBuilder recipientId(UUID recipientId) {
            this.recipientId = recipientId;
            return this;
        }

        public LetterBuilder subject(String subject) {
            this.subject = subject;
            return this;
        }

        public LetterBuilder content(String content) {
            this.content = content;
            return this;
        }

        public Letter build() {
            return new Letter(this);
        }
    }
    public static LetterBuilder builder(){
        return new LetterBuilder();
    }
}
