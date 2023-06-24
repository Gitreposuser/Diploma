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
@Table(name = "bank_branches")
public class BankBranch {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "branch_number", nullable = false)
    private Integer branchNumber;

    @Column(name = "status", length = 128, nullable = false)
    private String status;

    @Column(name = "location_id", nullable = false)
    private UUID locationId;

    @ManyToOne
    @JoinColumn(name = "location_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Location location;

    @Column(name = "general_phone", length = 20, nullable = false)
    private String generalPhone;

    @Column(name = "hot_line", length = 20, nullable = false)
    private String hotLine;

    @Column(name = "active", nullable = false)
    private Boolean active;

    @CreationTimestamp
    @Column(name = "created", updatable = false)
    private LocalDateTime created;
}