package com.tingeso.autoFix.services;

import com.tingeso.autoFix.entities.PricingAdjustmentEntity;
import com.tingeso.autoFix.entities.RepairEntity;
import com.tingeso.autoFix.repositories.PricingAdjustmentRepository;
import com.tingeso.autoFix.repositories.RepairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class RecargosService {


    @Autowired
    private PricingAdjustmentRepository pricingAdjustmentRepository;
    @Autowired
    private RepairRepository repairRepository;

    public int calculateTotalCost(String licensePlate) {

        List<RepairEntity> repairs = repairRepository.findByVehicleEntity_LicensePlate(licensePlate);
        if (repairs.isEmpty()) {
            return 0;
        }

        int totalCost =  0;
        for (RepairEntity repair : repairs) {
            totalCost += repair.getTotalCost();
        }

        List<PricingAdjustmentEntity> adjustments = pricingAdjustmentRepository.findByVehicleEntity_LicensePlate(licensePlate);
        for (PricingAdjustmentEntity adjustment : adjustments) {
            BigDecimal cost = BigDecimal.valueOf(totalCost);
            BigDecimal percentage = BigDecimal.valueOf(adjustment.getAmount()).divide(BigDecimal.valueOf(100));
            if (adjustment.getType().equals("Discount")) {
                totalCost = cost.subtract(cost.multiply(percentage)).intValue();
            } else if (adjustment.getType().equals("Surcharge")) {
                totalCost = cost.add(cost.multiply(percentage)).intValue();
            }
        }

        return totalCost;
    }
}


