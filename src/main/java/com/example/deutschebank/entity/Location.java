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
@Table(name = "locations")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "house_number", nullable = false)
    private Integer houseNumber;

    @Column(name = "street", length = 64, nullable = false)
    private String street;

    @Column(name = "city", length = 64, nullable = false)
    private String city;

    @Column(name = "state", length = 64, nullable = false)
    private String state;

    @Column(name = "country", length = 64, nullable = false)
    private String country;

    @CreationTimestamp
    @Column(name = "created", updatable = false)
    private LocalDateTime created;
}