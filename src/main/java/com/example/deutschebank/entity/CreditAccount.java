package com.example.deutschebank.entity;

import com.example.deutschebank.entity.enums.CreditStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static jakarta.persistence.CascadeType.MERGE;
import static jakarta.persistence.CascadeType.REFRESH;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "credit_accounts")
public class CreditAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @ManyToOne(cascade = {MERGE, REFRESH})
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;

    @Enumerated(EnumType.STRING)
    @Column(name = "credit_status", length = 20, nullable = false)
    private CreditStatus creditStatus;

    @Column(name = "debt", precision = 15, scale = 2, nullable = false)
    private BigDecimal debt;

    @Column(name = "loan_interest",
            columnDefinition = "NUMERIC(4, 2) DEFAULT '3.00'")
    private BigDecimal loanInterest;

    @Column(name = "start_from")
    private LocalDateTime startFrom;

    @Column(name = "active")
    private Boolean active;

    @CreationTimestamp
    @Column(name = "created", updatable = false)
    private LocalDateTime created;

    @UpdateTimestamp
    @Column(name = "updated")
    private  LocalDateTime updated;
}