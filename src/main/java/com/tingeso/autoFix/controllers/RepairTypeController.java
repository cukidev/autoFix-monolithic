package com.tingeso.autoFix.controllers;

import com.tingeso.autoFix.entities.RepairType;
import com.tingeso.autoFix.services.RepairTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("repair_types")
public class RepairTypeController {

    private final RepairTypeService repairTypeService;

    @Autowired
    public RepairTypeController(RepairTypeService repairTypeService) {
        this.repairTypeService = repairTypeService;
    }

    @GetMapping("/{type}")
    public ResponseEntity<RepairType> getRepairTypeByType(@PathVariable String type) {
        RepairType repairType = repairTypeService.getRepairTypeByType(type);
        if (repairType != null) {
            return ResponseEntity.ok(repairType);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
