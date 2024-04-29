package com.tingeso.autoFix.controllers;

import com.tingeso.autoFix.entities.BonusEntity;
import com.tingeso.autoFix.services.BonusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bonus")
@CrossOrigin("*")
public class BonusController {

    private final BonusService bonusService;

    @Autowired
    public BonusController(BonusService bonusService) {
        this.bonusService = bonusService;
    }

    @GetMapping("/")
    public ResponseEntity<List<BonusEntity>> getAllBonuses() {
        List<BonusEntity> bonusEntity = bonusService.getAllBonus();
        return ResponseEntity.ok(bonusEntity);
    }

    @PostMapping("/")
    public ResponseEntity<BonusEntity> createBonus(@RequestBody BonusEntity bonusEntity) {
        BonusEntity savedBonusEntity = bonusService.createBonus(bonusEntity);
        return ResponseEntity.ok(savedBonusEntity);
    }


    @GetMapping("/{id}")
    public ResponseEntity<BonusEntity> getBonusById(@PathVariable Long id){
        BonusEntity bonusEntity = bonusService.getBonusById(id);
        if (bonusEntity != null) {
            return ResponseEntity.ok(bonusEntity);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBonus(@PathVariable Long id) {
        try {
            bonusService.deleteBonus(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/apply/{vehicleId}/{brand}")
    public ResponseEntity<BonusEntity> applyBonusToVehicle(@PathVariable Long vehicleId, @PathVariable String brand) {
        try {
            BonusEntity bonus = bonusService.applyBonusToVehicle(vehicleId, brand);
            return ResponseEntity.ok(bonus);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
