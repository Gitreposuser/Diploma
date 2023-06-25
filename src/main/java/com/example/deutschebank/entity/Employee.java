package com.example.deutschebank.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "personal_detail_id", nullable = false)
    private UUID personalDetailId;

    @OneToOne
    @JoinColumn(name = "personal_detail_id", referencedColumnName = "id",
        insertable = false, updatable = false)
    private PersonalDetail personalDetail;

    @Column(name = "work_detail_id", nullable = false)
    private UUID workDetailId;

    @OneToOne
    @JoinColumn(name = "work_detail_id", referencedColumnName = "id",
        insertable = false, updatable = false)
    private WorkDetail workDetail;

    @Column(name = "location_id", nullable = false)
    private UUID locationId;

    @OneToOne
    @JoinColumn(name = "location_id", referencedColumnName = "id",
        insertable = false, updatable = false)
    private Location location;

    @Column(name = "branch_id", nullable = false)
    private UUID branchId;

    @ManyToOne
    @JoinColumn(name = "branch_id", referencedColumnName = "id",
        insertable = false, updatable = false)
    private BankBranch bankBranch;

    @Column(name = "active", nullable = false)
    private Boolean active;

    @CreationTimestamp
    @Column(name = "created", updatable = false)
    private LocalDateTime created;
}