package com.example.deutschebank.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

import static jakarta.persistence.CascadeType.MERGE;
import static jakarta.persistence.CascadeType.REFRESH;

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

    @OneToOne(cascade = {MERGE, REFRESH}, orphanRemoval = true)
    @JoinColumn(name = "personal_detail_id", referencedColumnName = "id")
    private PersonalDetail personalDetail;

    @OneToOne(cascade = {MERGE, REFRESH}, orphanRemoval = true)
    @JoinColumn(name = "debit_account_id", referencedColumnName = "id")
    private DebitAccount debitAccount;

    @OneToOne(cascade = {MERGE, REFRESH}, orphanRemoval = true)
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private Location location;

    @ManyToOne(cascade = {MERGE, REFRESH})
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;

    @Column(name = "active")
    private Boolean active;

    @CreationTimestamp
    @Column(name = "created", updatable = false)
    private LocalDateTime created;

    @UpdateTimestamp
    @Column(name = "updated")
    private  LocalDateTime updated;
}
