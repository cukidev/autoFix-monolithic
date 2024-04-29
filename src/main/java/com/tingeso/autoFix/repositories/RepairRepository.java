package com.tingeso.autoFix.repositories;

import com.tingeso.autoFix.entities.RepairEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepairRepository extends JpaRepository<RepairEntity, Long> {

    @Query("SELECT r FROM RepairEntity r WHERE r.vehicle.licensePlate = :licensePlate")
    List<RepairEntity> findRepairsByLicensePlate(@Param("licensePlate") String licensePlate);

}
