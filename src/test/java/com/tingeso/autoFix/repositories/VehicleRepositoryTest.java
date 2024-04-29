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

    @Test
    public void whenSaveVehicle_thenRetrieveSame() {
        // Given
        VehicleEntity newVehicle = new VehicleEntity();
        newVehicle.setLicensePlate("XYZ789");
        newVehicle.setBrand("Ford");
        newVehicle.setModel("Fiesta");
        newVehicle.setV_type("Car");
        newVehicle.setYear_of_manufacture(2018);
        newVehicle.setEngine_type("Diesel");
        newVehicle.setSeats(5);
        newVehicle.setMileage(20000);

        // When
        VehicleEntity savedVehicle = entityManager.persistFlushFind(newVehicle);

        // Then
        Optional<VehicleEntity> found = vehicleRepository.findById(savedVehicle.getId());
        assertThat(found).isPresent();
        assertThat(found.get().getLicensePlate()).isEqualTo(newVehicle.getLicensePlate());
    }

    @Test
    public void whenUpdateVehicle_thenChangesAreReflected() {
        // Given
        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setLicensePlate("XYZ789");
        vehicle.setBrand("Ford");
        vehicle.setModel("Fiesta");
        vehicle.setV_type("Car");
        vehicle.setYear_of_manufacture(2018);
        vehicle.setEngine_type("Diesel");
        vehicle.setSeats(5);
        vehicle.setMileage(20000);

        // When
        VehicleEntity saved = entityManager.persistFlushFind(vehicle);
        saved.setMileage(25000);
        entityManager.persistAndFlush(saved);

        // Then
        Optional<VehicleEntity> updated = vehicleRepository.findById(saved.getId());
        assertThat(updated).isPresent();
        assertThat(updated.get().getMileage()).isEqualTo(25000);
    }

    @Test
    public void whenDeleteVehicle_thenNoLongerExists() {
        // Given
        VehicleEntity vehicle = new VehicleEntity();
        vehicle.setLicensePlate("XYZ789");
        vehicle.setBrand("Ford");
        vehicle.setModel("Fiesta");
        vehicle.setV_type("Car");
        vehicle.setYear_of_manufacture(2018);
        vehicle.setEngine_type("Diesel");
        vehicle.setSeats(5);
        vehicle.setMileage(20000);

        // When
        VehicleEntity saved = entityManager.persistFlushFind(vehicle);
        vehicleRepository.deleteById(saved.getId());
        entityManager.flush(); // Ensure changes are flushed and handled in the database

        // Then
        Optional<VehicleEntity> found = vehicleRepository.findById(saved.getId());
        assertThat(found).isNotPresent();
    }


}
