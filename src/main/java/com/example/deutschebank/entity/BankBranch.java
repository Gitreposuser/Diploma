package com.example.deutschebank.entity;

import com.example.deutschebank.entity.enums.BranchStatus;
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
@Table(name = "bank_branches")
public class BankBranch {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "branch_number", nullable = false)
    private Integer branchNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "branch_status", length = 128, nullable = false)
    private BranchStatus branchStatus;

    @OneToOne(cascade = {MERGE, REFRESH}, orphanRemoval = true)
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private Location location;

    @Column(name = "general_phone", length = 32, nullable = false)
    private String generalPhone;

    @Column(name = "hot_line", length = 32, nullable = false)
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