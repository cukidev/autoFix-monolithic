package com.tingeso.autoFix.services;

import com.tingeso.autoFix.entities.PricingAdjustmentEntity;
import com.tingeso.autoFix.entities.RepairEntity;
import com.tingeso.autoFix.repositories.PricingAdjustmentRepository;
import com.tingeso.autoFix.repositories.RepairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class RecargosService {


    @Autowired
    private PricingAdjustmentRepository pricingAdjustmentRepository;
    @Autowired
    private RepairRepository repairRepository;

    public int calculateTotalCost(String licensePlate) {

        List<RepairEntity> repairs = repairRepository.findRepairsByLicensePlate(licensePlate);
        if (repairs.isEmpty()) {
            return 0;
        }

        int totalCost =  0;
        for (RepairEntity repair : repairs) {
            totalCost += repair.getTotalCost();
        }

        List<PricingAdjustmentEntity> adjustments = pricingAdjustmentRepository.findByVehicleEntityLicensePlate(licensePlate);
        for (PricingAdjustmentEntity adjustment : adjustments) {
            BigDecimal cost = BigDecimal.valueOf(totalCost);
            BigDecimal percentage = BigDecimal.valueOf(adjustment.getAmount())
                    .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
            if ("Discount".equals(adjustment.getType())) {
                totalCost = cost.subtract(cost.multiply(percentage)).intValue();
            } else if ("Surcharge".equals(adjustment.getType())) {
                totalCost = cost.add(cost.multiply(percentage)).intValue();
            }
        }

        return totalCost;
    }
}


