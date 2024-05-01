package com.tingeso.autoFix.repositories;

import com.tingeso.autoFix.entities.RepairPricesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RepairPricesRepository extends JpaRepository <RepairPricesEntity, String> {
    RepairPricesEntity findByType(String type);

    @Query("SELECT COUNT(r) FROM RepairEntity r WHERE r.repairPrice.id_repair_prices = :repairPricesId")
    long countByRepairPricesId(@Param("repairPricesId") Long repairPricesId);

}
