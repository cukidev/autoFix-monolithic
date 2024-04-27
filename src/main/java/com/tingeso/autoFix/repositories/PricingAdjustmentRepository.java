package com.tingeso.autoFix.repositories;

import com.tingeso.autoFix.entities.PricingAdjustmentEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface PricingAdjustmentRepository extends JpaRepository<PricingAdjustmentEntity, Long> {

    List<PricingAdjustmentEntity> findByType(String type);
    List<PricingAdjustmentEntity> findByVehicleEntityLicensePlate(String licensePlate);

    List<PricingAdjustmentEntity> findByActiveDateBefore(Date endDate);
    void deleteById(@NotNull Long id);
}
