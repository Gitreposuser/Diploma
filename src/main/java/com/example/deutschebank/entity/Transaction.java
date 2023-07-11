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
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "emitter_iban", length = 64, nullable = false)
    private String emitterIban;

    @Column(name = "receiver_iban", length = 64, nullable = false)
    private String receiverIban;

    @Column(name = "amount", precision = 10, scale = 2,
            nullable = false)
    private BigDecimal amount;

    @Column(name = "created", updatable = false)
    private LocalDateTime created;
}
