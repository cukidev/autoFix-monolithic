package com.tingeso.autoFix.services;

import com.tingeso.autoFix.repositories.PricingAdjustmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PricingAdjustmentService {

    private final PricingAdjustmentRepository pricingAdjustmentRepository;

    @Autowired
    public PricingAdjustmentService(PricingAdjustmentRepository pricingAdjustmentRepository) {
        this.pricingAdjustmentRepository = pricingAdjustmentRepository;
    }

}