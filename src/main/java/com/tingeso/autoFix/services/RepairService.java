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
import java.util.List;

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

    public RepairEntity getRepairById(Long id) {
        return repairRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró la reparación con el ID: " + id));
    }

    public RepairEntity createRepair(RepairEntity newRepair) {
        String licensePlate = newRepair.getVehicleEntity().getLicensePlate();

        VehicleEntity vehicle = vehicleRepository.findByLicensePlate(licensePlate)
                .orElseThrow(() -> new RuntimeException("Vehículo no encontrado con la patente: " + licensePlate));

        newRepair.setVehicleEntity(vehicle);
        newRepair.setEntryDate(LocalDateTime.now());
        return repairRepository.save(newRepair);
    }

    public List<RepairEntity> findRepairsByLicensePlate(String licensePlate) {
        return repairRepository.findRepairsByLicensePlate(licensePlate);
    }

    public ArrayList<RepairEntity> getAllRepairs() {
        return (ArrayList<RepairEntity>) repairRepository.findAll();
    }


    public RepairEntity updateRepair(Long id, RepairEntity repairEntity) {
        return repairRepository.save(repairEntity);
    }

    public boolean deleteRepair(Long id) {
        RepairEntity repair = getRepairById(id);
        repairRepository.delete(repair);
        return true;
    }

    public RepairEntity addRepairTypeToRepair(Long repairId, Long repairPricesId) {
        RepairEntity repair = getRepairById(repairId);
        RepairPricesEntity repairPrices = repairPricesRepository.findById(String.valueOf(repairPricesId))
                .orElseThrow(() -> new RuntimeException("Tipo de precio de reparación no encontrado con el id: " + repairPricesId));

        repair.getRepairPrices().add(repairPrices);
        return repairRepository.save(repair);
    }

    public RepairEntity removeRepairTypeFromRepair(Long repairId, Long repairPricesId) {
        RepairEntity repair = getRepairById(repairId);
        RepairPricesEntity repairPrices = (RepairPricesEntity) repairPricesRepository.findById(repairPricesId)
                .orElseThrow(() -> new RuntimeException("Tipo de precio de reparación no encontrado con el id: " + repairPricesId));

        repair.getRepairPrices().remove(repairPrices);
        return repairRepository.save(repair);
    }

}
