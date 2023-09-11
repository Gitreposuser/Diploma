package com.example.deutschebank.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "bank_info")
public class BankInfo {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name", length = 64, nullable = false)
    private String name;

    @Column(name = "iban", length = 64)
    private String iban;

    @Column(name = "balance", precision = 20, scale = 2)
    private BigDecimal balance;

    @Column(name = "capitalization", precision = 20, scale = 2)
    private BigDecimal capitalization;

    @Column(name = "owner", length = 128, nullable = false)
    private String owner;

    @Column(name = "`group`", length = 128, nullable = false)
    private String group;

    @Lob
    @Column(name = "logo")
    private byte[] logo;

    @CreationTimestamp
    @Column(name = "created", updatable = false)
    private LocalDateTime created;

    @UpdateTimestamp
    @Column(name = "updated")
    private LocalDateTime updated;
}