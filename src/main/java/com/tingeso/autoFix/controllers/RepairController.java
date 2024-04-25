package com.tingeso.autoFix.controllers;

import com.tingeso.autoFix.entities.RepairEntity;
import com.tingeso.autoFix.services.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/repair")
@CrossOrigin("*")
public class RepairController {

    private final RepairService repairService;

    @Autowired
    public RepairController(RepairService repairService) {
        this.repairService = repairService;
    }

    @PostMapping("/repairs")
    public ResponseEntity<?> createRepair(@RequestBody RepairEntity newRepair, @RequestParam String licensePlate) {
        Optional<RepairEntity> repairOpt = repairService.createRepair(newRepair, licensePlate);
        if (!repairOpt.isPresent()) {
            // Aquí podrías devolver un estado de error personalizado, por ejemplo, 404 Not Found
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(repairOpt.get());
    }

    @GetMapping("/{id}")
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
