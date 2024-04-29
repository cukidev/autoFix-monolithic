package com.tingeso.autoFix.services;

import com.tingeso.autoFix.entities.SurchargeEntity;
import com.tingeso.autoFix.entities.VehicleEntity;
import com.tingeso.autoFix.repositories.SurchargeRepository;
import com.tingeso.autoFix.repositories.VehicleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class SurchargeService {

    private final SurchargeRepository surchargeRepository;
    private final VehicleRepository vehicleRepository;

    @Autowired
    public SurchargeService(SurchargeRepository surchargeRepository, VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
        this.surchargeRepository = surchargeRepository;
    }

    // Obtener todos los recargos
    public List<SurchargeEntity> getAllSurcharges() {
        return surchargeRepository.findAll();
    }

    // Obtener un recargo por ID
    public SurchargeEntity getSurchargeById(Long id) {
        return surchargeRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("La sobrecarga con el id " + id + " no existe."));
    }

    // Crear una nuevo recargo
    public SurchargeEntity createSurcharge(SurchargeEntity surcharge) {
        return surchargeRepository.save(surcharge);
    }

    // Actualizar un recargo
    public SurchargeEntity updateSurcharge(Long id, SurchargeEntity surchargeEntity) {
        if (surchargeRepository.existsById(id)) {
            surchargeEntity.setId(id);
            return surchargeRepository.save(surchargeEntity);
        }
        throw new EntityNotFoundException("La sobrecarga con el id " + id + " no existe.");
    }

    // Eliminar un recargo
    public boolean deleteSurcharge(Long id) {
        if (surchargeRepository.existsById(id)) {
            surchargeRepository.deleteById(id);
            return true;
        } else {
            throw new EntityNotFoundException("La sobrecarga con el id " + id + " no existe.");
        }
    }

    public BigDecimal calculateTotalSurcharge(Long vehicleId, LocalDate readyDate, LocalDate pickupDate) {
        VehicleEntity vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("No se encontró el vehículo: " + vehicleId));

        BigDecimal mileageSurcharge = calculateMileageSurcharge(vehicle.getMileage(), vehicle.getV_type());
        BigDecimal ageSurcharge = calculateAgeSurcharge(vehicle.getYear_of_manufacture(), vehicle.getV_type());
        BigDecimal delaySurcharge = calculateDelaySurcharge(readyDate, pickupDate);

        return mileageSurcharge.add(ageSurcharge).add(delaySurcharge);
    }

    private BigDecimal calculateMileageSurcharge(int mileage, String vehicleType) {
        int[] mileageBrackets = {5000, 12000, 25000, 40000};
        BigDecimal[] sedanHatchbackRates = {new BigDecimal("0.00"), new BigDecimal("0.03"), new BigDecimal("0.07"), new BigDecimal("0.12"), new BigDecimal("0.20")};
        BigDecimal[] suvPickupVanRates = {new BigDecimal("0.00"), new BigDecimal("0.05"), new BigDecimal("0.09"), new BigDecimal("0.12"), new BigDecimal("0.20")};

        for (int i = mileageBrackets.length - 1; i >= 0; i--) {
            if (mileage > mileageBrackets[i]) {
                if ("sedan".equalsIgnoreCase(vehicleType) || "hatchback".equalsIgnoreCase(vehicleType)) {
                    return sedanHatchbackRates[i + 1];
                } else {
                    return suvPickupVanRates[i + 1];
                }
            }
        }
        return BigDecimal.ZERO; // Si el kilometraje es menor o igual a 5,000
    }
    private BigDecimal calculateAgeSurcharge(int manufactureYear, String vehicleType) {
        int vehicleAge = LocalDate.now().getYear() - manufactureYear;
        int[] ageBrackets = {5, 10, 15};
        BigDecimal[] standardRates = {new BigDecimal("0.00"), new BigDecimal("0.05"), new BigDecimal("0.09"), new BigDecimal("0.15")};
        BigDecimal[] suvPickupVanRates = {new BigDecimal("0.00"), new BigDecimal("0.05"), new BigDecimal("0.11"), new BigDecimal("0.20")};

        for (int i = ageBrackets.length - 1; i >= 0; i--) {
            if (vehicleAge > ageBrackets[i]) {
                if ("suv".equalsIgnoreCase(vehicleType) || "pickup".equalsIgnoreCase(vehicleType) || "furgoneta".equalsIgnoreCase(vehicleType)) {
                    return suvPickupVanRates[i + 1];
                } else {
                    return standardRates[i + 1];
                }
            }
        }
        return BigDecimal.ZERO; // Si la edad del vehículo es de 5 años o menos
    }

    private BigDecimal calculateDelaySurcharge(LocalDate readyDate, LocalDate pickupDate) {
        long delayDays = Period.between(readyDate, pickupDate).getDays();
        return BigDecimal.valueOf(delayDays).multiply(new BigDecimal("0.05"));
    }
}