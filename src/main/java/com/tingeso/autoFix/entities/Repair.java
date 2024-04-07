package com.tingeso.autoFix.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "repairs")
@Data
public class Repair {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_vin", nullable = false)
    private Vehicle vehicle;

    @Column(name = "entry_date", nullable = false)
    private LocalDateTime entryDate;

    @Column(name = "repair_type", nullable = false)
    private String repairType;

    @Column(name = "total_cost", nullable = false)
    private Double totalCost;

    @Column(name = "departure_date")
    private LocalDateTime departureDate;

}