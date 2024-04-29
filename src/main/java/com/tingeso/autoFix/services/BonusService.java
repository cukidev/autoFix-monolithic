package com.tingeso.autoFix.services;

import com.tingeso.autoFix.entities.BonusEntity;
import com.tingeso.autoFix.entities.VehicleEntity;
import com.tingeso.autoFix.repositories.BonusRepository;
import com.tingeso.autoFix.repositories.VehicleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

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
        Optional<VehicleEntity> vehicleOptional = vehicleRepository.findById(vehicleId);

        VehicleEntity vehicle = vehicleOptional.get();
        Optional<BonusEntity> existingBonus = bonusRepository.findByVehicle_Id(vehicleId);

        Optional<BonusEntity> availableBonus = bonusRepository.findFirstByBrandAndVehicleIsNull(brand);

        BonusEntity bonus = availableBonus.get();
        bonus.setVehicle(vehicle);
        return bonusRepository.save(bonus);
    }
    public BigDecimal calculateBonusForVehicle(Long vehicleId) {
        Optional<BonusEntity> bonus = bonusRepository.findByVehicle_Id(vehicleId);
        if (bonus.isPresent()) {
            return bonus.get().getAmount();
        } else {
            return BigDecimal.ZERO;
        }
    }

}