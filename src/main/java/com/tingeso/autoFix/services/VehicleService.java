package com.tingeso.autoFix.services;

import com.tingeso.autoFix.entities.VehicleEntity;
import com.tingeso.autoFix.repositories.VehicleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    final
    VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public List<VehicleEntity> getVehicles() { // Lista de vehículos
        return vehicleRepository.findAll();
    }

    public VehicleEntity createVehicle(VehicleEntity vehicle){
        return vehicleRepository.save(vehicle);
    }


    public VehicleEntity getVehicleByIdVin(Long id) {
        return vehicleRepository.findById(id).get();
    }

    public VehicleEntity updateVehicle(Long id, VehicleEntity vehicleEntity){
        if(vehicleRepository.existsById(id)) {
            vehicleEntity.setId(id);
            return vehicleRepository.save(vehicleEntity);
        }
        throw new EntityNotFoundException("El vehículo con el id " + id + " no existe.");
    }
    public boolean deleteVehicle(Long id) throws Exception{
        try{
            vehicleRepository.deleteById(id);
            return true;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}

