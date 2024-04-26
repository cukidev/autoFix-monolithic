package com.tingeso.autoFix.services;

import com.tingeso.autoFix.entities.RepairEntity;
import com.tingeso.autoFix.entities.RepairPricesEntity;
import com.tingeso.autoFix.entities.VehicleEntity;
import com.tingeso.autoFix.repositories.RepairPricesRepository;
import com.tingeso.autoFix.repositories.RepairRepository;
import com.tingeso.autoFix.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class RepairService {

    private final RepairRepository repairRepository;
    private final VehicleRepository vehicleRepository;
    private final RepairPricesRepository repairPricesRepository;

    @Autowired
    public RepairService(RepairRepository repairRepository,
                         VehicleRepository vehicleRepository,
                         RepairPricesRepository repairPricesRepository) {
        this.repairRepository = repairRepository;
        this.vehicleRepository = vehicleRepository;
        this.repairPricesRepository = repairPricesRepository;
    }

    public Optional<RepairEntity> createRepair(RepairEntity newRepair, String licensePlate) {
        // Directamente obtiene un Optional<VehicleEntity>
        Optional<VehicleEntity> vehicleOpt = vehicleRepository.findByLicensePlate(licensePlate);

        if (!vehicleOpt.isPresent()) {
            return Optional.empty();
        }

        // Ahora puedes usar get() para obtener el VehicleEntity si está presente
        newRepair.setVehicleEntity(vehicleOpt.get());
        newRepair.setEntryDate(LocalDateTime.now());
        RepairEntity savedRepair = repairRepository.save(newRepair);

        return Optional.of(savedRepair);
    }


    public ArrayList<RepairEntity> getAllRepairs() {
        return (ArrayList<RepairEntity>) repairRepository.findAll();
    }

    public RepairEntity getRepairById(String id) {
        return (RepairEntity) repairRepository.findById(Long.valueOf(id)).orElse(null);
    }

    public RepairEntity updateRepair(RepairEntity repairEntity) {
        return repairRepository.save(repairEntity);
    }

    public boolean deleteRepair(String id) {
        try {
            repairRepository.deleteById(Long.valueOf(id));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Optional<RepairEntity> addRepairTypeToRepair(Long repairId, Long repairPricesId) {
        Optional<RepairEntity> repairOpt = repairRepository.findById(repairId);
        Optional<RepairPricesEntity> repairPricesOpt = repairPricesRepository.findById(String.valueOf(repairPricesId));
        if (repairOpt.isPresent() && repairPricesOpt.isPresent()) {
            RepairEntity repair = repairOpt.get();
            RepairPricesEntity repairPrices = repairPricesOpt.get();
            repair.getRepairPrices().add(repairPrices); // Agrega el tipo de precio a la reparación
            repairRepository.save(repair); // Guarda la reparación con la nueva relación
            return Optional.of(repair);
        }
        return Optional.empty();
    }

    public Optional<RepairEntity> removeRepairTypeFromRepair(Long repairId, Long repairPricesId) {
        Optional<RepairEntity> repairOpt = repairRepository.findById(repairId);
        Optional<RepairPricesEntity> repairPricesOpt = repairPricesRepository.findById(String.valueOf(repairPricesId));
        if (repairOpt.isPresent() && repairPricesOpt.isPresent()) {
            RepairEntity repair = repairOpt.get();
            RepairPricesEntity repairPrices = repairPricesOpt.get();
            repair.getRepairPrices().remove(repairPrices);
            repairRepository.save(repair);
            return Optional.of(repair);
        }
        return Optional.empty();
    }



}
