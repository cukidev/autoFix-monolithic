package com.tingeso.autoFix.dto;

import lombok.*;

@Setter
@Getter
@Data
@NoArgsConstructor
public class AverageRepairTimeByBrandDTO {
    private String brand;
    private Double averageHours;

    public AverageRepairTimeByBrandDTO(String brand, Double averageHours) {
        this.brand = brand;
        this.averageHours = averageHours;
    }

}
