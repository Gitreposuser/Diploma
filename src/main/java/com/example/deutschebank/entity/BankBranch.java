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
@Table(name = "bank_branches")
public class BankBranch {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "branch_number", nullable = false, unique = true)
    private Integer branchNumber;

    @Column(name = "status", length = 128, nullable = false)
    private String status;

    @Column(name = "location_id", nullable = false)
    private UUID locationId;

    @OneToOne
    @JoinColumn(name = "location_id", referencedColumnName = "id",
            insertable = false, updatable = false)
    private Location location;

    @Column(name = "general_phone", length = 20, nullable = false)
    private String generalPhone;

    @Column(name = "hot_line", length = 20, nullable = false)
    private String hotLine;

    @Column(name = "active")
    private Boolean active;

    @CreationTimestamp
    @Column(name = "created", updatable = false)
    private LocalDateTime created;

    @UpdateTimestamp
    @Column(name = "updated")
    private LocalDateTime updated;
}