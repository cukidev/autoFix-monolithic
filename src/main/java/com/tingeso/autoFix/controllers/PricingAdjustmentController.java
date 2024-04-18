package com.tingeso.autoFix.controllers;

import com.tingeso.autoFix.entities.PricingAdjustment;
import com.tingeso.autoFix.services.PricingAdjustmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pricing_adjustment")
public class PricingAdjustmentController {

    private final PricingAdjustmentService pricingAdjustmentService;

    @Autowired
    public PricingAdjustmentController(PricingAdjustmentService pricingAdjustmentService){
        this.pricingAdjustmentService = pricingAdjustmentService;
    }

    @GetMapping
    public ResponseEntity<List<PricingAdjustment>> getAll() {
        return ResponseEntity.ok(pricingAdjustmentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PricingAdjustment> getById(@PathVariable Long id) {
        return pricingAdjustmentService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PricingAdjustment> create(@RequestBody PricingAdjustment pricingAdjustment) {
        return ResponseEntity.ok(pricingAdjustmentService.save(pricingAdjustment));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PricingAdjustment> update(@PathVariable Long id, @RequestBody PricingAdjustment pricingAdjustment) {
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