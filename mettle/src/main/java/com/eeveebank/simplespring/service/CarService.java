package com.eeveebank.simplespring.service;

import com.eeveebank.simplespring.model.Car;
import com.eeveebank.simplespring.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public Car getById(String id) {
        return carRepository.getOne(id);
    }
}