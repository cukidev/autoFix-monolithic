package com.tingeso.autoFix.services;

import com.tingeso.autoFix.entities.RepairPricesEntity;
import com.tingeso.autoFix.repositories.RepairPricesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class RepairPricesService {

    private final RepairPricesRepository repairPricesRepository;

    @Autowired
    public RepairPricesService(RepairPricesRepository repairPricesRepository) {
        this.repairPricesRepository = repairPricesRepository;
    }

    public List<RepairPricesEntity> findAllRepairPrices() {
        return repairPricesRepository.findAll();
    }

    public Optional<RepairPricesEntity> findRepairPriceById(Long repairPriceId) {
        return repairPricesRepository.findById(String.valueOf(repairPriceId));
    }

    public RepairPricesEntity getRepairTypeByType(String type) {
        return repairPricesRepository.findByType(type);
    }

    public RepairPricesEntity createOrUpdateRepairPrices(RepairPricesEntity repairPricesEntity) {
        return repairPricesRepository.save(repairPricesEntity);
    }

    public BigDecimal getPriceByRepairType(String repairType, String engineType) throws ChangeSetPersister.NotFoundException {
        RepairPricesEntity repairPricesEntity = repairPricesRepository.findByType(repairType);
        if (repairPricesEntity != null) {
            return BigDecimal.valueOf(repairPricesEntity.getPriceByEngineType(engineType));
        } else {
            throw new ChangeSetPersister.NotFoundException();
        }
    }

    @Transactional
    public void deleteRepairPrice(Long repairTypeId) throws Exception {
        long repairsCount = repairPricesRepository.countByRepairPricesId(repairTypeId);
        if (repairsCount > 0) {
            throw new Exception("Existen reparaciones asociadas a este tipo de reparación y no puede ser eliminado.");
        }
        repairPricesRepository.deleteById(String.valueOf(repairTypeId));
    }



}
