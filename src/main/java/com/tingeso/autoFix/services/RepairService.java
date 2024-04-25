package com.tingeso.autoFix.services;

import com.tingeso.autoFix.entities.RepairEntity;
import com.tingeso.autoFix.repositories.RepairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class RepairService {

    private final RepairRepository repairRepository;

    @Autowired
    public RepairService(RepairRepository repairRepository){
        this.repairRepository = repairRepository;
    }

    public RepairEntity createRepair(RepairEntity repairEntity) {
        return repairRepository.save(repairEntity);
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
