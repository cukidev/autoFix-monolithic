package com.tingeso.autoFix.repositories;

import com.tingeso.autoFix.entities.RepairType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepairTypeRepository extends JpaRepository <RepairType, String> {
    RepairType findByType(String type);
}
