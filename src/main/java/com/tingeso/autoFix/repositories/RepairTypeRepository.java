package com.tingeso.autoFix.repositories;

import com.tingeso.autoFix.entities.RepairTypes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepairTypeRepository extends JpaRepository <RepairTypes, String> {
    RepairTypes findByType(String type);
}
