package com.tingeso.autoFix.services;

import com.tingeso.autoFix.dto.*;
import com.tingeso.autoFix.repositories.RepairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportService {

    @Autowired
    private RepairRepository repairRepository;

    public List<RepairCalculationDetailsDTO> getRepairDetailsByVehicle(Long vehicleId) {
        return repairRepository.findCalculationDetailsByVehicleId(vehicleId);
    }

    public List<RepairTypeSummaryDTO> getRepairTypeSummaries() {
        return repairRepository.findRepairTypesWithVehicleCountsAndTotalCost().stream()
                .map(obj -> new RepairTypeSummaryDTO((String) obj[0], (Long) obj[1], (Double) obj[2]))
                .collect(Collectors.toList());
    }

    public List<AverageRepairTimeByBrandDTO> getAverageRepairTimesByBrand() {
        return repairRepository.findAverageRepairTimesByBrand().stream()
                .map(obj -> new AverageRepairTimeByBrandDTO((String) obj[0], ((Number) obj[1]).doubleValue()))
                .collect(Collectors.toList());
    }

    public List<RepairTypeSummaryDTO> getRepairTypeAndEngineSummaries() {
        return repairRepository.findRepairTypesAndEngineTypesWithCountsAndTotalCost().stream()
                .map(obj -> new RepairTypeSummaryDTO((String) obj[0] + " - " + (String) obj[1], (Long) obj[2], (Double) obj[3]))
                .collect(Collectors.toList());
    }
}
