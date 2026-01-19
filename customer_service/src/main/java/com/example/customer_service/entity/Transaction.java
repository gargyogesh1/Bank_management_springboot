package com.example.customer_service.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔗 Relation to Customer by ssnId
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ssn_id", referencedColumnName = "ssnId", nullable = false)
    private Customer customer;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType type;

    @Column(nullable = false)
    private String amount;

    private LocalDateTime createdAt;

    public Transaction() {
        this.createdAt = LocalDateTime.now(); // auto-set date
    }

    // ===== ENUM =====
    public enum TransactionType {
        DEBIT,
        CREDIT
    }

    // ===== Getters & Setters =====

    public Long getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
