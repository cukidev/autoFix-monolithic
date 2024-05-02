package com.tingeso.autoFix.controllers;

import com.tingeso.autoFix.dto.*;
import com.tingeso.autoFix.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/repairDetails/{vehicleId}")
    public ResponseEntity<List<RepairCalculationDetailsDTO>> getRepairDetailsByVehicle(@PathVariable Long vehicleId) {
        return ResponseEntity.ok(reportService.getRepairDetailsByVehicle(vehicleId));
    }

    @GetMapping("/repairTypeSummaries")
    public ResponseEntity<List<RepairTypeSummaryDTO>> getRepairTypeSummaries() {
        return ResponseEntity.ok(reportService.getRepairTypeSummaries());
    }

    @GetMapping("/averageRepairTimes")
    public ResponseEntity<List<AverageRepairTimeByBrandDTO>> getAverageRepairTimesByBrand() {
        return ResponseEntity.ok(reportService.getAverageRepairTimesByBrand());
    }

    @GetMapping("/repairTypeEngineSummaries")
    public ResponseEntity<List<RepairTypeSummaryDTO>> getRepairTypeAndEngineSummaries() {
        return ResponseEntity.ok(reportService.getRepairTypeAndEngineSummaries());
    }
}
