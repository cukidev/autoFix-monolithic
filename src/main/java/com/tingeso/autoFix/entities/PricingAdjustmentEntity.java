package com.tingeso.autoFix.entities;


import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "pricing_adjustment")

public class PricingAdjustmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type", nullable = false)
    private String type; // Esto corresponderá a si es un DESCUENTO o un ADICIONAL

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", referencedColumnName = "id", nullable = false)
    private VehicleEntity vehicleEntity;

    public void setAmount(Double amount) {
        this.amount = BigDecimal.valueOf(amount);
    }


    public BigDecimal getAmount() {
        return amount;
    }
}
