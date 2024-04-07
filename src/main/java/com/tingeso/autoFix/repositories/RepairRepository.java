package com.tingeso.autoFix.repositories;

import com.tingeso.autoFix.entities.Repair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepairRepository extends JpaRepository<Repair, String> {
}
