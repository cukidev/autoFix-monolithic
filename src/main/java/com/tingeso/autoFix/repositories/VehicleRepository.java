package com.tingeso.autoFix.repositories;

import com.tingeso.autoFix.entities.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository <VehicleEntity, String> {
}


