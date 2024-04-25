package com.tingeso.autoFix.controllers;

import com.tingeso.autoFix.entities.RepairTypeEntity;
import com.tingeso.autoFix.services.RepairTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("repair_types")
@CrossOrigin("http://localhost:5173")
public class RepairTypeController {

    private final RepairTypeService repairTypeService;

    @Autowired
    public RepairTypeController(RepairTypeService repairTypeService) {
        this.repairTypeService = repairTypeService;
    }

    @GetMapping("/{type}")
    public ResponseEntity<RepairTypeEntity> getRepairTypeByType(@PathVariable String type) {
        RepairTypeEntity repairTypeEntity = repairTypeService.getRepairTypeByType(type);
        if (repairTypeEntity != null) {
            return ResponseEntity.ok(repairTypeEntity);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
