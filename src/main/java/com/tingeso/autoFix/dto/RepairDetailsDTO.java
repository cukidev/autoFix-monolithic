package com.tingeso.autoFix.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
@Data

public class RepairDetailsDTO {
    private Long id;
    private String licensePlate;
    private String repairType;
    private LocalDate entryDate;
    private BigDecimal repairCost;
    private String status;

    public RepairDetailsDTO(Long id, String licensePlate, String repairType, LocalDate entryDate, BigDecimal repairCost, String status) {
        this.id = id;
        this.licensePlate = licensePlate;
        this.repairType = repairType;
        this.entryDate = entryDate;
        this.repairCost = repairCost;
        this.status = status;
    }
}