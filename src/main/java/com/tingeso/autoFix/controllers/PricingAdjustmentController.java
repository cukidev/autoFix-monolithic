package com.tingeso.autoFix.controllers;

import com.tingeso.autoFix.entities.PricingAdjustmentEntity;
import com.tingeso.autoFix.services.PricingAdjustmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pricing_adjustment")
@CrossOrigin("*")
public class PricingAdjustmentController {

    private final PricingAdjustmentService pricingAdjustmentService;

    @Autowired
    public PricingAdjustmentController(PricingAdjustmentService pricingAdjustmentService){
        this.pricingAdjustmentService = pricingAdjustmentService;
    }

    @GetMapping
    public ResponseEntity<List<PricingAdjustmentEntity>> getAll() {
        return ResponseEntity.ok(pricingAdjustmentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PricingAdjustmentEntity> getById(@PathVariable Long id) {
        return pricingAdjustmentService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PricingAdjustmentEntity> create(@RequestBody PricingAdjustmentEntity pricingAdjustmentEntity) {
        return ResponseEntity.ok(pricingAdjustmentService.save(pricingAdjustmentEntity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PricingAdjustmentEntity> update(@PathVariable Long id, @RequestBody PricingAdjustmentEntity pricingAdjustmentEntity) {
        return pricingAdjustmentService.findById(id)
                .map(existingAdjustment -> {

                    return ResponseEntity.ok(pricingAdjustmentService.save(existingAdjustment));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pricingAdjustmentService.delete(id);
        return ResponseEntity.ok().build();
    }


}