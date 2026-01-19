package com.example.customer_service.dto;

import com.example.customer_service.entity.Transaction.TransactionType;
import java.time.LocalDateTime;

public class TransactionDTO {

    // ===== Request DTO =====
    public static class Request {
        private TransactionType type;
        private String amount;

        public TransactionType getType() {
            return type;
        }
        public void setType(TransactionType type) {
            this.type = type;
        }

        public String getAmount() {
            return amount;
        }
        public void setAmount(String amount) {
            this.amount = amount;
        }
    }

    // ===== Response DTO =====
    public static class Response {
        private Long id;
        private String ssnId;
        private String type;
        private String amount;
        private LocalDateTime createdAt;

        public Response(Long id, String ssnId, String type, String amount, LocalDateTime createdAt) {
            this.id = id;
            this.ssnId = ssnId;
            this.type = type;
            this.amount = amount;
            this.createdAt = createdAt;
        }

        public Long getId() {
            return id;
        }
        public String getSsnId() {
            return ssnId;
        }
        public String getType() {
            return type;
        }
        public String getAmount() {
            return amount;
        }
        public LocalDateTime getCreatedAt() {
            return createdAt;
        }
    }
}
