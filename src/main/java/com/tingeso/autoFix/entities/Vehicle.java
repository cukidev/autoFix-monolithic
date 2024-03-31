package com.tingeso.autoFix.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "vehicle")

/*
  Clase correspondiente al Vehiculo, el cual contiene todos sus datos personales
  @author Paloma Zepeda Garrido
*/

public class Vehicle {

    /*
    =============
    = ATRIBUTOS =
    =============
    */

    @Getter
    @Setter

    @Id
    @Column(name = "id_vin", nullable = false) // Chassis
    private String idVin;

    @Column(name = "license_plate", nullable = false, unique = true) // Patente
    private String licensePlate;

    @Column(name = "brand", nullable = false) // Marca
    private String brand;

    @Column(name = "model", nullable = false) // Modelo
    private String model;

    @Column(name = "v_type", nullable = false) //Tipo de Vehículo
    private String vType;

    @Column(name = "year_of_manufacture", nullable = false) //Año de fabricación
    private Integer yearOfManufacture;

    @Column(name = "engine_type", nullable = false) //Tipo de motor
    private String engineType;

    @Column(name = "seats", nullable = false) //Número de asientos
    private Integer seats;

}
