package com.tingeso.autoFix.services;

import com.tingeso.autoFix.entities.BonusEntity;
import com.tingeso.autoFix.entities.VehicleEntity;
import com.tingeso.autoFix.repositories.BonusRepository;
import com.tingeso.autoFix.repositories.VehicleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BonusService {

    final BonusRepository bonusRepository;
    final VehicleRepository vehicleRepository;

    public BonusService(BonusRepository bonusRepository, VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
        this.bonusRepository = bonusRepository;
    }

    public List<BonusEntity> getAllBonus() {
        return bonusRepository.findAll();
    }

    public BonusEntity getBonusById(Long id) {
        return bonusRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("El bonus con el id " + id + "no existe."));
    }

    public BonusEntity createBonus(BonusEntity bonus) {
        return bonusRepository.save(bonus);
    }

    public BonusEntity updateBonus(Long id, BonusEntity bonusEntity){
        if(bonusRepository.existsById(id)){
            bonusEntity.setId(id);
            return bonusRepository.save(bonusEntity);
        }
        throw new EntityNotFoundException("El bonus con el id " + id + "no existe.");
    }

    public boolean deleteBonus(Long id) throws Exception{
        try{
            bonusRepository.deleteById(id);
            return true;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public BonusEntity applyBonusToVehicle(Long vehicleId, String brand) {
        VehicleEntity vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("No se encontró vehículo " + vehicleId));

        bonusRepository.findByVehicle_Id(vehicleId)
                .ifPresent(bonus -> {
                    throw new RuntimeException("Bono ya asignado");
                });

        return bonusRepository.findFirstByBrandAndVehicleIsNull(brand)
                .map(bonus -> {
                    bonus.setVehicle(vehicle);
                    return bonusRepository.save(bonus);
                })
                .orElseThrow(() -> new RuntimeException("Bono no disponible " + brand));
    }

    public BigDecimal calculateBonusForVehicle(Long vehicleId) {
        return bonusRepository.findByVehicle_Id(vehicleId)
                .map(BonusEntity::getAmount)
                .orElse(BigDecimal.ZERO);
    }

}