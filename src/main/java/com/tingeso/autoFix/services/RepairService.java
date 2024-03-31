package com.tingeso.autoFix.services;

import com.tingeso.autoFix.entities.Repair;
import com.tingeso.autoFix.repositories.RepairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RepairService {

    private final RepairRepository repairRepository;

    @Autowired
    public RepairService(RepairRepository repairRepository){
        this.repairRepository = repairRepository;
    }

    public Repair createRepair(Repair repair) {
        // Se añadirán lógicas de negocios futuramente
        return repairRepository.save(repair);
    }

    public Object getRepairByIdVin(String idVin) {
        return repairRepository.findById(idVin);
    }
}
