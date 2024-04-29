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

    private final VehicleService vehicleService;
    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

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

    @GetMapping("/{id}") // GET - Obtener un vehiculo por su idVin
    public ResponseEntity<VehicleEntity> getVehicle(@PathVariable Long id){
        VehicleEntity vehicleEntity = vehicleService.getVehicleByIdVin(id);
        return ResponseEntity.ok(vehicleEntity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehicleEntity> updateVehicle(@PathVariable Long id, @RequestBody VehicleEntity vehicle){
        VehicleEntity vehicleUpdated = vehicleService.updateVehicle(id, vehicle);
        return ResponseEntity.ok(vehicleUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<VehicleEntity> deleteVehicle(@PathVariable Long id) throws Exception{
        var isDeleted = vehicleService.deleteVehicle(id);
        return ResponseEntity.noContent().build();
    }

}
