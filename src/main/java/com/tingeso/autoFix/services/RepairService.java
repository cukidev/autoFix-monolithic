package com.tingeso.autoFix.services;

import com.tingeso.autoFix.entities.RepairEntity;
import com.tingeso.autoFix.entities.RepairPricesEntity;
import com.tingeso.autoFix.entities.VehicleEntity;
import com.tingeso.autoFix.repositories.RepairPricesRepository;
import com.tingeso.autoFix.repositories.RepairRepository;
import com.tingeso.autoFix.repositories.VehicleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RepairService {

    @Autowired
    private RepairRepository repairRepository;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private RepairPricesRepository repairPriceRepository;

    public RepairService(RepairRepository repairRepository) {
        this.repairRepository = repairRepository;
    }

    public List<RepairEntity> getAllRepairs() {
        return repairRepository.findAll();
    }

    public RepairEntity getRepairById(Long id) {
        return repairRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("La reparación con el id " + id + " no existe."));
    }

    public RepairEntity createRepair(RepairEntity repair){
        return repairRepository.save(repair);
    }


    public RepairEntity updateRepair(Long id, RepairEntity repairEntity){
        if(repairRepository.existsById(id)) {
            repairEntity.setId(id);
            return repairRepository.save(repairEntity);
        }
        throw new EntityNotFoundException("La reparación con el id " + id + " no existe.");
    }

    public RepairEntity saveRepair(Long vehicleId, Long repairPriceId, BigDecimal repairPrice) {
        VehicleEntity vehicle = vehicleRepository.findById(vehicleId).orElseThrow(() -> new EntityNotFoundException("Vehicle not found"));
        RepairPricesEntity repairPriceEntity = repairPriceRepository.findById(String.valueOf(repairPriceId)).orElseThrow(() -> new EntityNotFoundException("RepairPrice not found"));

        RepairEntity repair = new RepairEntity();
        repair.setVehicle(vehicle);
        repair.setRepairPrice(repairPriceEntity);

        String engineType = vehicle.getEngine_type();
        Integer cost = repairPriceEntity.getPriceByEngineType(engineType);
        repair.setRepairCost(BigDecimal.valueOf(cost));

        return repairRepository.save(repair);
    }


    public boolean deleteRepair(Long id) {
        if (repairRepository.existsById(id)) {
            repairRepository.deleteById(id);
            return true;
        } else {
            throw new EntityNotFoundException("La reparación con el id " + id + " no existe.");
        }
    }

    public BigDecimal calculateRepairDiscount(Long vehicleId, String engineType, BigDecimal repairCosts) {
        List<RepairEntity> repairsLastYear = getRepairsLastYear(vehicleId);
        int repairCount = repairsLastYear.size();

        BigDecimal discountRate = getDiscountRateByRepairCountAndEngineType(repairCount, engineType);
        return repairCosts.multiply(discountRate);
    }

    private List<RepairEntity> getRepairsLastYear(Long vehicleId) {
        return repairRepository.findAll()
                .stream()
                .filter(repair -> repair.getVehicle().getId().equals(vehicleId) &&
                        repair.getEntryDate().isAfter(LocalDate.now().minusYears(1)))
                .collect(Collectors.toList());
    }

    public BigDecimal getDiscountRateByRepairCountAndEngineType(int repairCount, String engineType) {
        final Map<String, BigDecimal[]> discountRatesByEngineType = Map.of(
                "Gasolina", new BigDecimal[]{new BigDecimal("0.05"), new BigDecimal("0.10"), new BigDecimal("0.15"), new BigDecimal("0.20")},
                "Diésel", new BigDecimal[]{new BigDecimal("0.07"), new BigDecimal("0.12"), new BigDecimal("0.17"), new BigDecimal("0.22")},
                "Híbrido", new BigDecimal[]{new BigDecimal("0.10"), new BigDecimal("0.15"), new BigDecimal("0.20"), new BigDecimal("0.25")},
                "Eléctrico", new BigDecimal[]{new BigDecimal("0.08"), new BigDecimal("0.13"), new BigDecimal("0.18"), new BigDecimal("0.23")}
        );

        BigDecimal[] discountRates = discountRatesByEngineType.getOrDefault(engineType, new BigDecimal[0]);
        BigDecimal discountRate = BigDecimal.ZERO;

        if (repairCount >= 10) {
            discountRate = discountRates.length > 3 ? discountRates[3] : BigDecimal.ZERO;
        } else if (repairCount >= 6) {
            discountRate = discountRates.length > 2 ? discountRates[2] : BigDecimal.ZERO;
        } else if (repairCount >= 3) {
            discountRate = discountRates.length > 1 ? discountRates[1] : BigDecimal.ZERO;
        } else if (repairCount >= 1) {
            discountRate = discountRates.length > 0 ? discountRates[0] : BigDecimal.ZERO;
        }

        return discountRate;
    }
}
