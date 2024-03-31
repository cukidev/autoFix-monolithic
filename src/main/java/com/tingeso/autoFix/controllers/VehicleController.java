package com.tingeso.autoFix.controllers;

import com.tingeso.autoFix.entities.Vehicle;
import com.tingeso.autoFix.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService){
        this.vehicleService = vehicleService;
    }

    @PostMapping// POST - Creación de un nuevo vehiculo
    public ResponseEntity<Vehicle> createVehicle(@RequestBody Vehicle vehicle){
        Vehicle savedVehicle = vehicleService.createVehicle(vehicle);
        return ResponseEntity.ok(savedVehicle);
    }

    @GetMapping // GET - Obtener un vehiculo por su idVin
    public ResponseEntity<Vehicle> getVehicle(@PathVariable String idVin){
        Vehicle vehicle = (Vehicle) vehicleService.getVehicleByIdVin(idVin);
        return ResponseEntity.ok(vehicle);
    }
}
