package com.eeveebank.simplespring.controller;

import com.eeveebank.simplespring.client.CarApiClient;
import com.eeveebank.simplespring.model.Car;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
public class ClientCarController {

    private final CarApiClient carApiClient;

    public ClientCarController(CarApiClient carApiClient) {
        this.carApiClient = carApiClient;
    }

    @GetMapping(value = "/client-cars")
    public long count(@RequestParam("colour") String colour) {
        List<Car> cars = Optional.ofNullable(carApiClient.getAllCars()).orElse(Collections.EMPTY_LIST);
        if (cars == null) {
            return 0;
        }
        return cars.stream().filter(car -> colour.equals(car.getColour())).count();
    }

}
