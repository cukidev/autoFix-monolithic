package com.tingeso.autoFix.controllers;

import com.tingeso.autoFix.entities.RepairPricesEntity;
import com.tingeso.autoFix.services.RepairPricesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/repair/prices")
@CrossOrigin("*")
public class RepairPricesController {

    private final RepairPricesService repairPricesService;

    @Autowired
    public RepairPricesController(RepairPricesService repairPricesService) {
        this.repairPricesService = repairPricesService;
    }

    @PostMapping("/")
    public ResponseEntity<RepairPricesEntity> createRepairPrices(@RequestBody RepairPricesEntity repairPrices) {
        try {
            RepairPricesEntity savedRepairPrice = repairPricesService.createOrUpdateRepairPrices(repairPrices);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedRepairPrice);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<RepairPricesEntity>> getAllRepairPrices() {
        List<RepairPricesEntity> repairPrices = repairPricesService.findAllRepairPrices();
        return repairPrices.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(repairPrices);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RepairPricesEntity> getRepairPriceById(@PathVariable("id") Long id) {
        return repairPricesService.findRepairPriceById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteRepairPrices(@PathVariable("id") Long id) {
        try {
            repairPricesService.deleteRepairPrice(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }


}