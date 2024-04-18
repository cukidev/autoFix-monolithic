package com.tingeso.autoFix.services;

import com.tingeso.autoFix.entities.Repair;
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

    public Repair createRepair(Repair repair) {
        return repairRepository.save(repair);
    }

    public ArrayList<Repair> getAllRepairs() {
        return (ArrayList<Repair>) repairRepository.findAll();
    }

    public Repair getRepairById(String id) {
        return repairRepository.findById(id).orElse(null);
    }

    public Repair updateRepair(Repair repair) {
        return repairRepository.save(repair);
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
