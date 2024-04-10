package com.tingeso.autoFix.controllers;

import com.tingeso.autoFix.entities.RepairTypes;
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
    public ResponseEntity<RepairTypes> getRepairTypeByType(@PathVariable String type) {
        RepairTypes repairTypes = repairTypeService.getRepairTypeByType(type);
        if (repairTypes != null) {
            return ResponseEntity.ok(repairTypes);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
