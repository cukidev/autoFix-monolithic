package com.tingeso.autoFix.controllers;

import com.tingeso.autoFix.services.RepairPricesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("repair_prices")
@CrossOrigin("*")
public class RepairPricesController {

    private final RepairPricesService repairPricesService;

    @Autowired
    public RepairPricesController(RepairPricesService repairPricesService) {
        this.repairPricesService = repairPricesService;
    }


}