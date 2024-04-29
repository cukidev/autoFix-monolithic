package com.tingeso.autoFix.repositories;

import com.tingeso.autoFix.entities.SurchargeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurchargeRepository extends JpaRepository<SurchargeEntity, Long> {
}