package com.tingeso.autoFix.controllers;

import com.tingeso.autoFix.services.PricingAdjustmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("pricing_adjustment")
public class PricingAdjustmentController {

    private final PricingAdjustmentService pricingAdjustmentService;

    @Autowired
    public PricingAdjustmentController(PricingAdjustmentService pricingAdjustmentService){
        this.pricingAdjustmentService = pricingAdjustmentService;
    }


}