package com.tingeso.autoFix.services;

import com.tingeso.autoFix.entities.RepairEntity;
import com.tingeso.autoFix.entities.VehicleEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecargosService {

    public double calculateTotalCost(List<RepairEntity> repairs, VehicleEntity vehicle, int dayOfWeek) {
        double totalCost = 0.0;
        double discount = 0.0;
        double surcharge = 0.0;

        // Se calcula el costo base de las reparaciones
        for (RepairEntity repair : repairs) { // Changed from Repair to RepairEntity
            totalCost += repair.getTotalCost();
        }

        // Descuentos y recargos según el tipo de vehículo
        switch (vehicle.getV_type()) {
            case "Moto":
                if (vehicle.getYear_of_manufacture() < 1999) {
                    discount = 0.10; // 10% descuento para motos antiguas
                }
                break;
            case "Automovil":
                if (vehicle.getYear_of_manufacture() > 2020) {
                    surcharge = 0.15; // 15% recargo para autos nuevos
                }
                break;
            case "Camion":
                surcharge = 0.20;
                break;
            default:
                break;
        }

        // Descuentos adicionales según el día de la semana
        if (dayOfWeek == 3 || dayOfWeek == 5) {
            discount += 0.05;
        }

        // Aplicar descuentos y recargos
        totalCost = totalCost - (totalCost * discount) + (totalCost * surcharge);

        return totalCost;
    }
}
