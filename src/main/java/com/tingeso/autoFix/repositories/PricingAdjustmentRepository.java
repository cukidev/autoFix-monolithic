package com.tingeso.autoFix.repositories;

import com.tingeso.autoFix.entities.PricingAdjustmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PricingAdjustmentRepository extends JpaRepository<PricingAdjustmentEntity, String> {
    void deleteById(Long id);
}
