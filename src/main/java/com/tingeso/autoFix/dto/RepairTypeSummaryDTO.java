package com.tingeso.autoFix.dto;

import lombok.*;

@Setter
@Getter
@Data
@NoArgsConstructor
public class RepairTypeSummaryDTO {
    private String repairType;
    private Long count;
    private Double totalCost;

    public RepairTypeSummaryDTO(String repairType, Long count, Double totalCost) {
        this.repairType = repairType;
        this.count = count;
        this.totalCost = totalCost;
    }

}
