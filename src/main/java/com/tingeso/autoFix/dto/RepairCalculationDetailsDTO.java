package com.tingeso.autoFix.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@Data
@NoArgsConstructor
public class RepairCalculationDetailsDTO {
    private String licensePlate;
    private String repairType;
    private BigDecimal  repairCost;
    private BigDecimal  adjustedPrice;
    private BigDecimal basePrice;

    public RepairCalculationDetailsDTO(String licensePlate, String repairType, BigDecimal repairCost, BigDecimal adjustedPrice, BigDecimal basePrice) {
        this.licensePlate = licensePlate;
        this.repairType = repairType;
        this.repairCost = repairCost;
        this.adjustedPrice = adjustedPrice;
        this.basePrice = basePrice;
    }
}


