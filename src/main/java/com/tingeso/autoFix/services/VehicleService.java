package com.tingeso.autoFix.services;

import com.tingeso.autoFix.entities.VehicleEntity;
import com.tingeso.autoFix.repositories.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class VehicleService {

    final
    VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public ArrayList<VehicleEntity> getVehicles() { // Lista de vehículos
        return (ArrayList<VehicleEntity>) vehicleRepository.findAll();
    }

    public VehicleEntity createVehicle(VehicleEntity vehicle){
        return vehicleRepository.save(vehicle);
    }


    public VehicleEntity getVehicleByIdVin(Long id) {
        return vehicleRepository.findById(id).get();
    }

    public VehicleEntity updateVehicle(VehicleEntity vehicleEntity){
        return vehicleRepository.save(vehicleEntity);
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

