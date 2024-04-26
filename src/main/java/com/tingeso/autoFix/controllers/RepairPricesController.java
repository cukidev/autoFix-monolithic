package com.tingeso.autoFix.controllers;

import com.tingeso.autoFix.entities.RepairPricesEntity;
import com.tingeso.autoFix.services.RepairPricesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("repair_types")
@CrossOrigin("*")
public class RepairPricesController {

    private final RepairPricesService repairPricesService;

    @Autowired
    public RepairPricesController(RepairPricesService repairPricesService) {
        this.repairPricesService = repairPricesService;
    }

    @GetMapping("/{type}")
    public ResponseEntity<RepairPricesEntity> getRepairTypeByType(@PathVariable String type) {
        RepairPricesEntity repairPricesEntity = repairPricesService.getRepairTypeByType(type);
        if (repairPricesEntity != null) {
            return ResponseEntity.ok(repairPricesEntity);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
