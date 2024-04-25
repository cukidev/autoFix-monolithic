package com.tingeso.autoFix.services;

import com.tingeso.autoFix.entities.PricingAdjustmentEntity;
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

    public List<PricingAdjustmentEntity> findAll() {
        return pricingAdjustmentRepository.findAll();
    }

    public Optional<PricingAdjustmentEntity> findById(Long id) {
        return pricingAdjustmentRepository.findById(String.valueOf(id));
    }

    public PricingAdjustmentEntity save(PricingAdjustmentEntity pricingAdjustmentEntity) {
        return pricingAdjustmentRepository.save(pricingAdjustmentEntity);
    }

    public void delete(Long id) {
        pricingAdjustmentRepository.deleteById(id);
    }
}

