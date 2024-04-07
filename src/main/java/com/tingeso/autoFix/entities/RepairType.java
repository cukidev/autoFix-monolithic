package com.tingeso.autoFix.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter


@Entity
@Table(name = "repair_types")
public class RepairType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type", nullable = false, unique = true)
    private String type;

    @Column(name = "gasoline_price", nullable = false)
    private Integer gasoline_price;

    @Column(name = "diesel_price", nullable = false)
    private Integer diesel_price;

    @Column(name = "hybrid_price", nullable = false)
    private Integer hybrid_price;

    @Column(name = "electric_price", nullable = false)
    private Integer electric_rice;

}
