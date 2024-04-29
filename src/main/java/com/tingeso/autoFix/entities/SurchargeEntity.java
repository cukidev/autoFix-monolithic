package com.tingeso.autoFix.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "surcharge")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SurchargeEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",unique = true, nullable = false)
    private Long id;

    @Column(name = "amount", nullable = false)
    private Long amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "surcharge_type", nullable = false)
    private SurchargeType surchargeType;

    @Column(name = "applicable_type")
    private String applicableType;

    public enum SurchargeType {
        MILEAGE, VEHICLE_AGE, DELAY_PICKUP
    }

}
