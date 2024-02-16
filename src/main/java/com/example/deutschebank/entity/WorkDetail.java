package com.example.deutschebank.entity;

import com.example.deutschebank.entity.enums.WorkStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Table(name = "work_details")
public class WorkDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "position", length = 128, nullable = false)
    private String position;

    @Enumerated(EnumType.STRING)
    @Column(name = "work_status", length = 256, nullable = false)
    private WorkStatus workStatus;

    @Column(name = "salary", precision = 15, scale = 2, nullable = false)
    private BigDecimal salary;

    @Column(name = "work_phone", length = 30, nullable = false)
    private String workPhone;

    @Column(name = "work_email", length = 128, nullable = false)
    private String workEmail;

    @Column(name = "start_from")
    private LocalDateTime startFrom;

    @Column(name = "end_at")
    private LocalDateTime endAt;

    @Column(name = "active")
    private Boolean active;

    @CreationTimestamp
    @Column(name = "created", updatable = false)
    private LocalDateTime created;

    @UpdateTimestamp
    @Column(name = "updated")
    private  LocalDateTime updated;
}