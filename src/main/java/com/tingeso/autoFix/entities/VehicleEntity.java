package com.tingeso.autoFix.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Table(name = "vehicle")
@Data
@NoArgsConstructor
@AllArgsConstructor

/*
  Clase correspondiente al Vehiculo, el cual contiene todos sus datos personales
*/

public class VehicleEntity {

    /*
    =============
    = ATRIBUTOS =
    =============
    */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_vehicle",unique = true, nullable = false) // Chassis
    private Long id;

    @Column(name = "license_plate", nullable = false, unique = true)
    private String licensePlate;

    @Column(name = "brand", nullable = false) // Marca
    private String brand;

    @Column(name = "model", nullable = false) // Modelo
    private String model;

    @Column(name = "v_type", nullable = false) //Tipo de Vehículo
    private String v_type;

    @Column(name = "year_of_manufacture", nullable = false) //Año de fabricación
    private Integer year_of_manufacture;

    @Column(name = "engine_type", nullable = false) //Tipo de motor
    private String engine_type;

    @Column(name = "seats", nullable = false) //Número de asientos
    private Integer seats;

    @Column(name = "mileage", nullable = false)
    private Integer mileage;

    // RELACIONES

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<RepairEntity> repairs;

}
