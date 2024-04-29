package com.tingeso.autoFix.controllers;

import com.tingeso.autoFix.entities.SurchargeEntity;
import com.tingeso.autoFix.services.SurchargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/surcharge")
@CrossOrigin("*")
public class SurchargeController {

    private final SurchargeService surchargeService;

    @Autowired
    public SurchargeController(SurchargeService surchargeService) {
        this.surchargeService = surchargeService;
    }

    // Obtener todos los recargos
    @GetMapping("/")
    public ResponseEntity<List<SurchargeEntity>> getAllSurcharges() {
        List<SurchargeEntity> surchargeEntities = surchargeService.getAllSurcharges();
        return ResponseEntity.ok(surchargeEntities);
    }

    // Crear un nuevo recargo
    @PostMapping("/")
    public ResponseEntity<SurchargeEntity> createSurcharge(@RequestBody SurchargeEntity surchargeEntity) {
        SurchargeEntity savedSurchargeEntity = surchargeService.createSurcharge(surchargeEntity);
        return ResponseEntity.ok(savedSurchargeEntity);
    }

    // Obtener un recargo por ID
    @GetMapping("/{id}")
    public ResponseEntity<SurchargeEntity> getSurchargeById(@PathVariable Long id) {
        SurchargeEntity surchargeEntity = surchargeService.getSurchargeById(id);
        if (surchargeEntity != null) {
            return ResponseEntity.ok(surchargeEntity);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Actualizar un recargo
    @PutMapping("/{id}")
    public ResponseEntity<SurchargeEntity> updateSurcharge(@PathVariable Long id, @RequestBody SurchargeEntity surcharge) {
        SurchargeEntity surchargeUpdated = surchargeService.updateSurcharge(id, surcharge);
        if (surchargeUpdated != null) {
            return ResponseEntity.ok(surchargeUpdated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un recargo
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSurcharge(@PathVariable Long id) {
        boolean isDeleted = surchargeService.deleteSurcharge(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
