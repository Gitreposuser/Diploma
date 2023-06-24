package com.example.deutschebank.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "debit_accounts")
public class DebitAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "client_id", nullable = false)
    private UUID clientId;

    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id",
            insertable = false, updatable = false)
    private Client client;

    @Column(name = "iban", length = 64, nullable = false)
    private String iban;

    @Column(name = "debit_status", length = 20, nullable = false)
    private String debitStatus;

    @Column(name = "balance",
            columnDefinition = "NUMERIC(15, 2) DEFAULT '0.00'")
    private BigDecimal balance;

    @Column(name = "deposit_interest",
            columnDefinition = "NUMERIC(4, 2) DEFAULT '3.00'")
    private BigDecimal depositInterest;

    @Column(name = "credit_line",
            columnDefinition = "NUMERIC(7, 2) DEFAULT '500.00'")
    private BigDecimal creditLine;

    @Column(name = "start_from", updatable = false)
    private LocalDateTime startFrom;

    @Column(name = "active", nullable = false)
    private Boolean active;

    @CreationTimestamp
    @Column(name = "created", updatable = false)
    private LocalDateTime created;
}