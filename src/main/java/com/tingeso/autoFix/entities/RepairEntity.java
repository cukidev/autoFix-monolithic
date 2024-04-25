package com.tingeso.autoFix.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "repairs")
@Data
public class RepairEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_veh", nullable = false)
    private VehicleEntity vehicleEntity;

    @Column(name = "entry_date", nullable = false)
    private LocalDateTime entryDate;

    @Column(name = "exit_date", nullable = false)
    private LocalDateTime exitDate;

    @Column(name = "repair_type", nullable = false)
    private String repairType;

    @Column(name = "total_cost", nullable = false)
    private Double totalCost;

    @Column(name = "departure_date", nullable = false)
    private LocalDateTime departureDate;

    @Column(name = "status", nullable = false)
    private String status;

}