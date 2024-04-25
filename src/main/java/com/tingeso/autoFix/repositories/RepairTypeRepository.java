package com.tingeso.autoFix.repositories;

import com.tingeso.autoFix.entities.RepairTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepairTypeRepository extends JpaRepository <RepairTypeEntity, String> {
    RepairTypeEntity findByType(String type);
}
