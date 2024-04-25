package com.tingeso.autoFix.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "repair_type")
public class RepairTypeEntity {

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
    private Integer electric_price;

}
