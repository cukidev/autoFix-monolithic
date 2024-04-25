package com.tingeso.autoFix.repositories;

import com.tingeso.autoFix.entities.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<VehicleEntity, Long> {

    @Query("select v from VehicleEntity v where v.engine_type = :engineType")
    List<VehicleEntity> findByEngineType(@Param("engineType") String engineType);

    Optional<VehicleEntity> findByLicensePlate(String licensePlate);
}
