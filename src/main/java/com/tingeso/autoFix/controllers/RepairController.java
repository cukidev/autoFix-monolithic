package com.tingeso.autoFix.controllers;

import com.tingeso.autoFix.entities.RepairEntity;
import com.tingeso.autoFix.services.RepairCostService;
import com.tingeso.autoFix.services.RepairService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/repair")
@CrossOrigin("*")
public class RepairController {

    private final RepairService repairService;
    private final RepairCostService repairCostService;

    @Autowired
    public RepairController(RepairService repairService, RepairCostService repairCostService) {
        this.repairService = repairService;
        this.repairCostService = repairCostService;
    }

    // Obtener todas las reparaciones
    @GetMapping("/")
    public ResponseEntity<List<RepairEntity>> getAllRepairs() {
        List<RepairEntity> repairEntity = repairService.getAllRepairs();
        return ResponseEntity.ok(repairEntity);
    }

    // Crear una nueva reparación
    @PostMapping("/")
    public ResponseEntity<RepairEntity> createRepair(@RequestBody RepairEntity repairEntity) {
        RepairEntity savedRepairEntity = repairService.createRepair(repairEntity);
        return ResponseEntity.ok(savedRepairEntity);
    }

    // Obtener una reparación por ID
    @GetMapping("/{id}")
    public ResponseEntity<RepairEntity> getRepairById(@PathVariable Long id) {
        RepairEntity repairEntity = repairService.getRepairById(id);
        if (repairEntity != null) {
            return ResponseEntity.ok(repairEntity);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    // Actualizar una reparación
    @PutMapping("/{id}")
    public ResponseEntity<RepairEntity> updateRepair(@PathVariable Long id, @RequestBody RepairEntity repair) {
        RepairEntity repairUpdated = repairService.updateRepair(id, repair);
        if (repairUpdated != null) {
            return ResponseEntity.ok(repairUpdated);
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

    // Fórmula de cálculo de costo total de reparación
    @GetMapping("/cost/{vehicleId}")
    public ResponseEntity<BigDecimal> getRepairCost(@PathVariable Long vehicleId) {
        try {
            BigDecimal totalCost = repairCostService.calculateTotalRepairCost(vehicleId);
            return ResponseEntity.ok(totalCost);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
