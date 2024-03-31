package com.tingeso.autoFix.services;

import com.tingeso.autoFix.entities.Vehicle;
import com.tingeso.autoFix.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    @Autowired
    public VehicleService(VehicleRepository vehicleRepository){
        this.vehicleRepository = vehicleRepository;
    }

    public Vehicle saveVehicle(Vehicle vehicle){
        return vehicleRepository.save(vehicle);
    }


    public Vehicle createVehicle(Vehicle vehicle){
        //
        return vehicleRepository.save(vehicle);
    }

    public Object getVehicleByIdVin(String idVin) {
        return vehicleRepository.findById(idVin);
    }
}
