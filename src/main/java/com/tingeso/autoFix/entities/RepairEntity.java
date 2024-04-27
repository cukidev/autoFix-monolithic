package com.tingeso.autoFix.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "repair")
public class RepairEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "entry_date", nullable = false)
    private LocalDateTime entryDate;

    @Column(name = "exit_date")
    private LocalDateTime exitDate;

    @Column(name = "total_cost")
    private Double totalCost;

    @Column(name = "departure_date")
    private LocalDateTime departureDate;

    @Column(name = "status")
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "license_plate", referencedColumnName = "license_plate", nullable = false)
    private VehicleEntity vehicleEntity;

    @ManyToMany
    @JoinTable(
            name = "repair_to_repair_prices",
            joinColumns = @JoinColumn(name = "repair_id"),
            inverseJoinColumns = @JoinColumn(name = "repair_prices_id")
    )
    private Set<RepairPricesEntity> repairPrices = new HashSet<>();

    private BigDecimal basePrice;
    private BigDecimal adjustedPrice;


}