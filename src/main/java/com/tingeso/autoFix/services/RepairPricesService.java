package com.tingeso.autoFix.services;

import com.tingeso.autoFix.entities.RepairPricesEntity;
import com.tingeso.autoFix.repositories.RepairPricesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RepairPricesService {

    private final RepairPricesRepository repairPricesRepository;

    @Autowired
    public RepairPricesService(RepairPricesRepository repairPricesRepository) {
        this.repairPricesRepository = repairPricesRepository;
    }

    public RepairPricesEntity getRepairTypeByType(String type) {
        return repairPricesRepository.findByType(type);
    }

    public RepairPricesEntity saveRepairType(RepairPricesEntity repairPricesEntity) {
        return repairPricesRepository.save(repairPricesEntity);
    }

}
