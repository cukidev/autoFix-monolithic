package com.tingeso.autoFix.repositories;

import com.tingeso.autoFix.entities.PricingAdjustment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PricingAdjustmentRepository extends JpaRepository<PricingAdjustment, String> {
    void deleteById(Long id);
}
