package com.tingeso.autoFix.repositories;

import com.tingeso.autoFix.entities.RepairPricesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RepairPricesRepository extends JpaRepository <RepairPricesEntity, String> {
    RepairPricesEntity findByType(String type);

    Optional<Object> findById(Long repairPricesId);
}
