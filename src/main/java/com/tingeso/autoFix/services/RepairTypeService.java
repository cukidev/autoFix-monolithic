package com.tingeso.autoFix.services;

import com.tingeso.autoFix.entities.RepairType;
import com.tingeso.autoFix.repositories.RepairTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RepairTypeService {

    private final RepairTypeRepository repairTypeRepository;

    @Autowired
    public RepairTypeService(RepairTypeRepository repairTypeRepository) {
        this.repairTypeRepository = repairTypeRepository;
    }

    public RepairType getRepairTypeByType(String type) {
        return repairTypeRepository.findByType(type);
    }

    public RepairType saveRepairType(RepairType repairType) {
        return repairTypeRepository.save(repairType);
    }

}
