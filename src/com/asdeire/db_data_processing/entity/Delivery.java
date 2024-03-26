package com.asdeire.db_data_processing.entity;
import java.time.LocalDateTime;
import java.util.UUID;

public class Delivery {

    private UUID deliveryId;
    private UUID letterId;
    private UUID mailboxId;
    private LocalDateTime deliveryDate;

    private Delivery(DeliveryBuilder builder) {
        this.deliveryId = builder.deliveryId;
        this.letterId = builder.letterId;
        this.mailboxId = builder.mailboxId;
        this.deliveryDate = builder.deliveryDate;
    }

    public UUID getDeliveryId() {
        return deliveryId;
    }

    public UUID getLetterId() {
        return letterId;
    }

    public UUID getMailboxId() {
        return mailboxId;
    }

    public LocalDateTime getDeliveryDate() {
        return deliveryDate;
    }

    public static class DeliveryBuilder {
        private UUID deliveryId;
        private UUID letterId;
        private UUID mailboxId;
        private LocalDateTime deliveryDate;

        public DeliveryBuilder() {
            this.deliveryId = UUID.randomUUID();
        }

        public DeliveryBuilder deliveryId(UUID deliveryId) {
            this.deliveryId = deliveryId;
            return this;
        }

        public DeliveryBuilder letterId(UUID letterId) {
            this.letterId = letterId;
            return this;
        }

        public DeliveryBuilder mailboxId(UUID mailboxId) {
            this.mailboxId = mailboxId;
            return this;
        }

        public DeliveryBuilder deliveryDate(LocalDateTime deliveryDate) {
            this.deliveryDate = deliveryDate;
            return this;
        }

        public Delivery build() {
            return new Delivery(this);
        }
    }

    public static DeliveryBuilder builder(){
        return new DeliveryBuilder();
    }
}
