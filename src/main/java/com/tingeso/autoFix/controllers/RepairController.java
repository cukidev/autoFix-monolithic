package com.tingeso.autoFix.controllers;

import com.tingeso.autoFix.entities.RepairEntity;
import com.tingeso.autoFix.services.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/repair")
@CrossOrigin("*")
public class RepairController {

    @Autowired
    private RepairService repairService;

    // Obtener todas las reparaciones
    @GetMapping("/")
    public ResponseEntity<List<RepairEntity>> getAllRepairs() {
        List<RepairEntity> repairs = repairService.getAllRepairs();
        return ResponseEntity.ok(repairs);
    }

    // Crear una nueva reparación
    @PostMapping("/")
    public ResponseEntity<RepairEntity> createRepair(@RequestBody RepairEntity newRepair) {
        RepairEntity repair = repairService.createRepair(newRepair);
        return ResponseEntity.ok(repair);
    }

    // Obtener una reparación por ID
    @GetMapping("/{id}")
    public ResponseEntity<RepairEntity> getRepairById(@PathVariable Long id) {
        RepairEntity repair = repairService.getRepairById(id);
        if (repair != null) {
            return ResponseEntity.ok(repair);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Actualizar una reparación
    @PutMapping("/{id}")
    public ResponseEntity<RepairEntity> updateRepair(@PathVariable Long id, @RequestBody RepairEntity repairDetails) {
        RepairEntity updatedRepair = repairService.updateRepair(id, repairDetails);
        if (updatedRepair != null) {
            return ResponseEntity.ok(updatedRepair);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar una reparación
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRepair(@PathVariable Long id) {
        boolean isDeleted = repairService.deleteRepair(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
