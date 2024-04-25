package com.tingeso.autoFix.repositories;

import com.tingeso.autoFix.entities.RepairEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepairRepository extends JpaRepository<RepairEntity, String> {
}
