package com.tingeso.autoFix.repositories;

import com.tingeso.autoFix.dto.RepairCalculationDetailsDTO;
import com.tingeso.autoFix.entities.RepairEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepairRepository extends JpaRepository<RepairEntity, Long> {

    @Query("SELECT new com.tingeso.autoFix.dto.RepairCalculationDetailsDTO(r.vehicle.licensePlate, rp.type, r.repairCost, r.adjustedPrice, r.basePrice) FROM RepairEntity r JOIN r.repairPrice rp WHERE r.vehicle.id = :vehicleId")
    List<RepairCalculationDetailsDTO> findCalculationDetailsByVehicleId(@Param("vehicleId") Long vehicleId);

    @Query("SELECT rp.type, COUNT(DISTINCT v.v_type), SUM(r.repairCost) FROM RepairEntity r JOIN r.vehicle v JOIN r.repairPrice rp GROUP BY rp.type ORDER BY SUM(r.repairCost) DESC")
    List<Object[]> findRepairTypesWithVehicleCountsAndTotalCost();

    @Query(value = "SELECT v.brand, AVG(TIMESTAMPDIFF(HOUR, r.entry_date, r.exit_date)) AS averageHours FROM repair r JOIN vehicle v ON r.vehicle_id = v.id_vehicle GROUP BY v.brand ORDER BY averageHours ASC", nativeQuery = true)
    List<Object[]> findAverageRepairTimesByBrand();

    @Query("SELECT rp.type, v.engine_type, COUNT(v), SUM(r.repairCost) FROM RepairEntity r JOIN r.vehicle v JOIN r.repairPrice rp GROUP BY rp.type, v.engine_type ORDER BY SUM(r.repairCost) DESC")
    List<Object[]> findRepairTypesAndEngineTypesWithCountsAndTotalCost();








}
