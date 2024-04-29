package com.tingeso.autoFix.services;

import com.tingeso.autoFix.entities.RepairEntity;
import com.tingeso.autoFix.entities.VehicleEntity;
import com.tingeso.autoFix.repositories.RepairRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepairService {

    final
    RepairRepository repairRepository;

    public RepairService(RepairRepository repairRepository) {
        this.repairRepository = repairRepository;
    }

    public List<RepairEntity> getAllRepairs() {
        return repairRepository.findAll();
    }

    public RepairEntity getRepairById(Long id) {
        return repairRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("La reparación con el id " + id + " no existe."));
    }

    public RepairEntity createRepair(RepairEntity repair){
        return repairRepository.save(repair);
    }


    public RepairEntity updateRepair(Long id, RepairEntity repairEntity){
        if(repairRepository.existsById(id)) {
            repairEntity.setId(id);
            return repairRepository.save(repairEntity);
        }
        throw new EntityNotFoundException("La reparación con el id " + id + " no existe.");
    }


    public boolean deleteRepair(Long id) {
        if (repairRepository.existsById(id)) {
            repairRepository.deleteById(id);
            return true;
        } else {
            throw new EntityNotFoundException("La reparación con el id " + id + " no existe.");
        }
    }




}
