package com.tingeso.autoFix.controllers;

import com.tingeso.autoFix.entities.RepairEntity;
import com.tingeso.autoFix.services.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/repair")
@CrossOrigin("*")
public class RepairController {

    private final RepairService repairService;

    @Autowired
    public RepairController(RepairService repairService) {
        this.repairService = repairService;
    }

    @PostMapping("/")
    public ResponseEntity<RepairEntity> createRepair(@RequestBody RepairEntity newRepair, @RequestParam String licensePlate) {
        Optional<RepairEntity> repairOpt = repairService.createRepair(newRepair, licensePlate);
        if (!repairOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(repairOpt.get());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RepairEntity> getRepairById(@PathVariable String id) {
        RepairEntity repairEntity =  repairService.getRepairById(id);
        if (repairEntity != null) {
            return ResponseEntity.ok(repairEntity);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/")
    public ResponseEntity<RepairEntity> updateRepair(@RequestBody RepairEntity repairEntity){
        RepairEntity repairEntityUpdated = repairService.updateRepair(repairEntity);
        return  ResponseEntity.ok(repairEntityUpdated);
    }


}
