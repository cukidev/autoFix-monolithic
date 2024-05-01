package com.tingeso.autoFix.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "repair")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RepairEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_repair",unique = true, nullable = false) // Chassis
    private Long id;

    @Column(name = "entry_date", nullable = false)
    private LocalDate entryDate;

    @Column(name = "entry_time", nullable = false)
    private LocalTime entryTime;

    @Column(name = "exit_date")
    private LocalDateTime exitDate;

    @Column(name = "exit_time")
    private LocalDateTime exitTime;

    @Column(name = "pickup_date") // era más facil manejar esta variable
    private LocalDate pickupDate;

    @Column(name = "pickup_time") // era más facil manejar esta variable
    private LocalTime pickupTime;

    @Column(name = "repair_cost", nullable = false)
    private BigDecimal repairCost;

    @Column(name = "status")
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id")
    @JsonBackReference
    private VehicleEntity vehicle;

    @ManyToOne
    @JoinColumn(name = "repair_price_id")
    private RepairPricesEntity repairPrice;

    private BigDecimal basePrice;
    private BigDecimal adjustedPrice;

}