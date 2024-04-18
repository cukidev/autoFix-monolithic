package com.tingeso.autoFix.controllers;

import com.tingeso.autoFix.entities.VehicleEntity;
import com.tingeso.autoFix.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vehicle")
@CrossOrigin("*")

public class VehicleController {

    @Autowired
    VehicleService vehicleService;

    @GetMapping("/") // Obtener listado de todos los vehiculos
    public ResponseEntity<List<VehicleEntity>> listVehicle(){
        List<VehicleEntity> vehicleEntity = vehicleService.getVehicles();
        return ResponseEntity.ok(vehicleEntity);
    }

    @PostMapping("/")// POST - Creación de un nuevo vehiculo
    public ResponseEntity<VehicleEntity> createVehicle(@RequestBody VehicleEntity vehicleEntity){
        VehicleEntity savedVehicleEntity = vehicleService.createVehicle(vehicleEntity);
        return ResponseEntity.ok(savedVehicleEntity);
    }

    @GetMapping("/{idVin}") // GET - Obtener un vehiculo por su idVin
    public ResponseEntity<VehicleEntity> getVehicle(@PathVariable String idVin){
        VehicleEntity vehicleEntity = vehicleService.getVehicleByIdVin(idVin);
        return ResponseEntity.ok(vehicleEntity);
    }

    @PutMapping("/") // PUT -
    public ResponseEntity<VehicleEntity> updateVehicle(@RequestBody VehicleEntity vehicle){
        VehicleEntity vehicleUpdated = vehicleService.updateVehicle(vehicle);
        return ResponseEntity.ok(vehicleUpdated);
    }

    @DeleteMapping("/{idVin}")
    public ResponseEntity<VehicleEntity> deleteVehicle(@PathVariable String idVin) throws Exception{
        var isDeleted = vehicleService.deleteVehicle(idVin);
        return ResponseEntity.noContent().build();
    }

}
