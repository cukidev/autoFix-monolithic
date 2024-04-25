package com.tingeso.autoFix.services;

import com.tingeso.autoFix.entities.RepairEntity;
import com.tingeso.autoFix.entities.VehicleEntity;
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

    @Autowired
    public RepairService(RepairRepository repairRepository, VehicleRepository vehicleRepository){
        this.repairRepository = repairRepository;
        this.vehicleRepository = vehicleRepository;
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
        return repairRepository.findById(id).orElse(null);
    }

    public RepairEntity updateRepair(RepairEntity repairEntity) {
        return repairRepository.save(repairEntity);
    }

    public boolean deleteRepair(String id) {
        try {
            repairRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
