package com.tingeso.autoFix.services;

import com.tingeso.autoFix.entities.RepairEntity;
import com.tingeso.autoFix.entities.VehicleEntity;
import com.tingeso.autoFix.repositories.VehicleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;

@Service
public class RepairCostService {

    private static final BigDecimal TAX_RATE = new BigDecimal("0.19"); // IVA 19%


    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private RepairService repairService;

    @Autowired
    private BonusService bonusService;

    @Autowired
    private SurchargeService surchargeService;

    public BigDecimal calculateDiscounts(VehicleEntity vehicle, BigDecimal totalRepairCost) {
        BigDecimal discountByRepairCount = repairService.getDiscountRateByRepairCountAndEngineType(
                vehicle.getRepairs().size(), vehicle.getEngine_type()
        ).multiply(totalRepairCost);

        BigDecimal discountByDayOfWeek = calculateDayOfWeekDiscount(vehicle);

        BigDecimal bonusDiscount = bonusService.calculateBonusForVehicle(vehicle.getId());

        return discountByRepairCount.add(discountByDayOfWeek).add(bonusDiscount);
    }

    private BigDecimal calculateDayOfWeekDiscount(VehicleEntity vehicle) {
        LocalDate entryDate = vehicle.getRepairs().get(0).getEntryDate();

        BigDecimal discount = BigDecimal.ZERO;
        if (isDiscountDay(entryDate)) {
            discount = discount.add(new BigDecimal("0.10"));
        }
        return discount;
    }

    private boolean isDiscountDay(LocalDate date) {
        DayOfWeek day = date.getDayOfWeek();
        return day == DayOfWeek.MONDAY || day == DayOfWeek.THURSDAY;
    }

    // Fórmula de cálculo de costo total de reparación
    public BigDecimal calculateTotalRepairCost(Long vehicleId) {
        VehicleEntity vehicle = vehicleRepository.findById(vehicleId) // Busca vehiculo
                .orElseThrow(() -> new EntityNotFoundException("Vehículo no encontrado con ID: " + vehicleId));

        // Suma los costos de todas las reparaciones
        BigDecimal totalRepairCost = repairService.getAllRepairs().stream()
                .filter(repair -> repair.getVehicle().equals(vehicle))
                .map(RepairEntity::getRepairCost)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Calcula Recargos
        BigDecimal totalSurcharges = surchargeService.calculateTotalSurcharge(
                vehicleId, LocalDate.now(), LocalDate.now());
        // Calcula Descuentos
        BigDecimal totalDiscounts = calculateDiscounts(vehicle, totalRepairCost);
        // Calcula antes de Impuestos
        BigDecimal subtotal = totalRepairCost.add(totalSurcharges).subtract(totalDiscounts);
        // Dps de impuestos
        BigDecimal taxAmount = subtotal.multiply(TAX_RATE);

        return subtotal.add(taxAmount);
    }



}
