package com.tingeso.autoFix.repositories;

import com.tingeso.autoFix.entities.PricingAdjustmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface PricingAdjustmentRepository extends JpaRepository<PricingAdjustmentEntity, String> {

    List<PricingAdjustmentEntity> findByType(String type);
    List<PricingAdjustmentEntity> findByVehicleEntity_LicensePlate(String licensePlate);

    List<PricingAdjustmentEntity> findByActiveDateBefore(Date endDate);



    void deleteById(Long id);
}
