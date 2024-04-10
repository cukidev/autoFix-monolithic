package com.tingeso.autoFix.services;

import com.tingeso.autoFix.entities.RepairTypes;
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

    public RepairTypes getRepairTypeByType(String type) {
        return repairTypeRepository.findByType(type);
    }

    public RepairTypes saveRepairType(RepairTypes repairTypes) {
        return repairTypeRepository.save(repairTypes);
    }

}
