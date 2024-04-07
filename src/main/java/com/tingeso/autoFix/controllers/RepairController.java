package com.tingeso.autoFix.controllers;

import com.tingeso.autoFix.entities.Repair;
import com.tingeso.autoFix.services.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/repair")
public class RepairController {

    private final RepairService repairService;

    @Autowired
    public RepairController(RepairService repairService) {
        this.repairService = repairService;
    }

    @PostMapping
    public ResponseEntity<Repair> createRepair(@RequestBody Repair repair) {
        try {
            Repair newRepair = repairService.createRepair(repair);
            return ResponseEntity.ok(newRepair);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{idVin}")
    public ResponseEntity<Repair> getRepairById(@PathVariable String idVin) {
        Repair repair = (Repair) repairService.getRepairByIdVin(idVin);
        if (repair != null) {
            return ResponseEntity.ok(repair);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
