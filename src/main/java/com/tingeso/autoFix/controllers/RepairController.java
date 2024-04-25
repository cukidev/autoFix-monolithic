package com.tingeso.autoFix.controllers;

import com.tingeso.autoFix.entities.RepairEntity;
import com.tingeso.autoFix.services.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/repair")
@CrossOrigin("http://localhost:5173")
public class RepairController {

    private final RepairService repairService;

    @Autowired
    public RepairController(RepairService repairService) {
        this.repairService = repairService;
    }

    @PostMapping("/")
    public ResponseEntity<RepairEntity> createRepair(@RequestBody RepairEntity repairEntity) {
        try {
            RepairEntity newRepairEntity = repairService.createRepair(repairEntity);
            return ResponseEntity.ok(newRepairEntity);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{idVin}")
    public ResponseEntity<RepairEntity> getRepairById(@PathVariable String idVin) {
        RepairEntity repairEntity =  repairService.getRepairById(idVin);
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
