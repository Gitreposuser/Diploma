package com.example.deutschebank.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "manager_id", nullable = false)
    private UUID managerId;

    @OneToOne
    @JoinColumn(name = "manager_id", referencedColumnName = "id",
            insertable = false, updatable = false)
    private Employee employee;

    @Column(name = "debit_account_id", nullable = false)
    private UUID debitAccountId;

    @OneToOne
    @JoinColumn(name = "debit_account_id", referencedColumnName = "id",
            insertable = false, updatable = false)
    private DebitAccount debitAccount;

    @Column(name = "personal_detail_id", nullable = false)
    private UUID personalDetailId;

    @OneToOne
    @JoinColumn(name = "personal_detail_id", referencedColumnName = "id",
            insertable = false, updatable = false)
    private PersonalDetail personalDetail;

    @Column(name = "location_id", nullable = false)
    private UUID locationId;

    @OneToOne
    @JoinColumn(name = "location_id", referencedColumnName = "id",
            insertable = false, updatable = false)
    private Location location;

    @Column(name = "active", nullable = false)
    private Boolean active;

    @CreationTimestamp
    @Column(name = "created", updatable = false)
    private LocalDateTime created;

    @UpdateTimestamp
    @Column(name = "updated")
    private  LocalDateTime updated;
}
