package com.tingeso.autoFix.repositories;

import com.tingeso.autoFix.entities.VehicleEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class VehicleRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Test
    public void whenFindByLicensePlate_thenReturnVehicle() {
        // given
        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setLicensePlate("AAA123");
        vehicle.setBrand("Toyota");
        vehicle.setModel("Corolla");
        vehicle.setV_type("Car");
        vehicle.setYear_of_manufacture(2020);
        vehicle.setEngine_type("Gasoline");
        vehicle.setSeats(5);
        vehicle.setMileage(10000);

        entityManager.persistAndFlush(vehicle);

        // when
        Optional<VehicleEntity> found = vehicleRepository.findByLicensePlate(vehicle.getLicensePlate());

        // then
        assertThat(found).isPresent();
        assertThat(found.get().getLicensePlate()).isEqualTo(vehicle.getLicensePlate());
    }

    /*
    @Test
    public void whenSaveVehicle_thenRetrieveSame() {
        VehicleEntity newVehicle = new VehicleEntity(null, "XYZ789", "Ford", "Fiesta", "Car", 2018, "Diesel", 5, 20000, null, null);
        VehicleEntity savedVehicle = entityManager.persistFlushFind(newVehicle);
        Optional<VehicleEntity> found = vehicleRepository.findById(savedVehicle.getId());
        assertThat(found).isPresent();
        assertThat(found.get().getLicensePlate()).isEqualTo(newVehicle.getLicensePlate());
    }

    @Test
    public void whenUpdateVehicle_thenChangesAreReflected() {
        VehicleEntity vehicle = new VehicleEntity(null, "XYZ789", "Ford", "Fiesta", "Car", 2018, "Diesel", 5, 20000, null, null);
        VehicleEntity saved = entityManager.persistFlushFind(vehicle);
        saved.setMileage(25000);
        entityManager.persistAndFlush(saved);

        Optional<VehicleEntity> updated = vehicleRepository.findById(saved.getId());
        assertThat(updated).isPresent();
        assertThat(updated.get().getMileage()).isEqualTo(25000);
    }

    @Test
    public void whenDeleteVehicle_thenNoLongerExists() {
        VehicleEntity vehicle = new VehicleEntity(null, "XYZ789", "Ford", "Fiesta", "Car", 2018, "Diesel", 5, 20000, null, null);
        VehicleEntity saved = entityManager.persistFlushFind(vehicle);
        vehicleRepository.deleteById(saved.getId());
        entityManager.flush();  // Ensure changes are flushed and handled in the database

        Optional<VehicleEntity> found = vehicleRepository.findById(saved.getId());
        assertThat(found).isNotPresent();
    }

     */


}
