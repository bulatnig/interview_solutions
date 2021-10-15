package com.eeveebank.simplespring;

import com.eeveebank.simplespring.controller.SimpleController;
import com.eeveebank.simplespring.model.Car;
import com.eeveebank.simplespring.repository.CarRepository;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class PartOneIntegrationTest {

    @Autowired
    CarRepository carRepository;
    @Autowired
    MockMvc mockMvc;

    @Test
    public void test() throws Exception {
        Car car = new Car("1", "fast", "red");
        carRepository.save(car);

        mockMvc.perform(get("/cars").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", CoreMatchers.is(car.getId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].type", CoreMatchers.is(car.getType())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].colour", CoreMatchers.is(car.getColour())));
    }
}
