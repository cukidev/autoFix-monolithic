package com.tingeso.autoFix.services;

import com.tingeso.autoFix.entities.PricingAdjustment;
import com.tingeso.autoFix.repositories.PricingAdjustmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PricingAdjustmentService {

    private final PricingAdjustmentRepository pricingAdjustmentRepository;

    @Autowired
    public PricingAdjustmentService(PricingAdjustmentRepository pricingAdjustmentRepository) {
        this.pricingAdjustmentRepository = pricingAdjustmentRepository;
    }

    public List<PricingAdjustment> findAll() {
        return pricingAdjustmentRepository.findAll();
    }

    public Optional<PricingAdjustment> findById(Long id) {
        return pricingAdjustmentRepository.findById(String.valueOf(id));
    }

    public PricingAdjustment save(PricingAdjustment pricingAdjustment) {
        return pricingAdjustmentRepository.save(pricingAdjustment);
    }

    public void delete(Long id) {
        pricingAdjustmentRepository.deleteById(id);
    }
}

