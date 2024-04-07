package com.tingeso.autoFix.services;

import com.tingeso.autoFix.entities.Vehicle;
import com.tingeso.autoFix.repositories.VehicleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    @Autowired
    public VehicleService(VehicleRepository vehicleRepository){
        this.vehicleRepository = vehicleRepository;
    }

    public Vehicle createVehicle(Vehicle vehicle) {
        if (vehicle.getIdVin() == null || vehicle.getIdVin().length() != 17) {
            throw new IllegalArgumentException("El ID VIN debe ser un string de 17 caracteres.");
        }

        if (vehicleRepository.existsById(vehicle.getIdVin())) {
            throw new IllegalArgumentException("El ID VIN ya existe.");
        }

        return vehicleRepository.save(vehicle);
    }

    public Vehicle getVehicleByIdVin(String idVin) {
        return vehicleRepository.findById(idVin)
                .orElseThrow(() -> new EntityNotFoundException("No fue encontrado el vehiculo con la id: " + idVin));
    }
    }

