package com.tingeso.autoFix.repositories;

import com.tingeso.autoFix.entities.RepairPricesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepairPricesRepository extends JpaRepository <RepairPricesEntity, String> {
    RepairPricesEntity findByType(String type);
}
