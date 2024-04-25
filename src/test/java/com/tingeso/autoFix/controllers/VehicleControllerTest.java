package com.tingeso.autoFix.controllers;

import com.tingeso.autoFix.entities.VehicleEntity;
import com.tingeso.autoFix.services.VehicleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(VehicleController.class)
public class VehicleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VehicleService vehicleService;

    @Test
    public void listVehicle_ShouldReturnVehicles() throws Exception {
        VehicleEntity vehicle1 = new VehicleEntity();
        vehicle1.setId(1L);
        vehicle1.setLicense_plate("AAA123");
        vehicle1.setBrand("Toyota");
        vehicle1.setModel("Corolla");
        vehicle1.setV_type("Car");
        vehicle1.setYear_of_manufacture(2020);
        vehicle1.setEngine_type("Gasoline");
        vehicle1.setSeats(5);
        vehicle1.setMileage(10000);

        VehicleEntity vehicle2 = new VehicleEntity();
        vehicle2.setId(2L);
        vehicle2.setLicense_plate("BBB456");
        vehicle2.setBrand("Honda");
        vehicle2.setModel("Civic");
        vehicle2.setV_type("Car");
        vehicle2.setYear_of_manufacture(2019);
        vehicle2.setEngine_type("Diesel");
        vehicle2.setSeats(5);
        vehicle2.setMileage(15000);

        List<VehicleEntity> vehicleList = Arrays.asList(vehicle1, vehicle2);

        given(vehicleService.getVehicles()).willReturn(vehicleList);

        mockMvc.perform(get("/api/v1/vehicle/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].brand", is("Toyota")))
                .andExpect(jsonPath("$[1].brand", is("Honda")));
    }

}
