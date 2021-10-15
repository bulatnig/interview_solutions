package com.eeveebank.simplespring.controller;

import com.eeveebank.simplespring.model.Car;
import com.eeveebank.simplespring.repository.CarRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
public class SimpleController {

    private final CarRepository carRepository;

    public SimpleController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @GetMapping(value = "/cars")
    public List<Car> getCars() {
        return carRepository.findAll();
    }

}
