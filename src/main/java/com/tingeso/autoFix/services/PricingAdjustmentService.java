package com.tingeso.autoFix.services;

import com.tingeso.autoFix.entities.PricingAdjustmentEntity;
import com.tingeso.autoFix.entities.RepairEntity;
import com.tingeso.autoFix.entities.VehicleEntity;
import com.tingeso.autoFix.repositories.PricingAdjustmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PricingAdjustmentService {

    private final PricingAdjustmentRepository pricingAdjustmentRepository;
    private final VehicleService vehicleService;
    private final RepairService repairService;
    private final RecargosService recargosService;

    @Autowired
    public PricingAdjustmentService(PricingAdjustmentRepository pricingAdjustmentRepository,
                                    VehicleService vehicleService,
                                    RepairService repairService,
                                    RecargosService recargosService) {
        this.pricingAdjustmentRepository = pricingAdjustmentRepository;
        this.vehicleService = vehicleService;
        this.repairService = repairService;
        this.recargosService = recargosService;
    }

    public List<PricingAdjustmentEntity> findAll() {
        return pricingAdjustmentRepository.findAll();
    }

    public Optional<PricingAdjustmentEntity> findById(Long id) {
        return pricingAdjustmentRepository.findById(String.valueOf(id));
    }

    public PricingAdjustmentEntity save(PricingAdjustmentEntity pricingAdjustmentEntity) {
        return pricingAdjustmentRepository.save(pricingAdjustmentEntity);
    }

    public void delete(Long id) {
        pricingAdjustmentRepository.deleteById(id);
    }

    public Boolean calcularAjustesDePrecios(int diaDeLaSemana) {
        List<VehicleEntity> vehiculos = vehicleService.getVehicles();

        for (VehicleEntity vehiculo : vehiculos) {
            List<RepairEntity> reparaciones = repairService.findRepairsByLicensePlate(vehiculo.getLicensePlate());
            double costoTotal = recargosService.calculateTotalCost(reparaciones, vehiculo, diaDeLaSemana);

            PricingAdjustmentEntity ajuste = new PricingAdjustmentEntity();
            ajuste.setVehicleEntity(vehiculo);
            ajuste.setType("ADICIONAL"); // o "DESCUENTO", según corresponda
            ajuste.setDescription("Descripción del ajuste"); // Puedes personalizar esta descripción
            ajuste.setAmount(costoTotal);
            pricingAdjustmentRepository.save(ajuste);
        }

        return true;
    }
}
