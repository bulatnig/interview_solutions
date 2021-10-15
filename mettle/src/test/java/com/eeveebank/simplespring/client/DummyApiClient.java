package com.eeveebank.simplespring.client;

import com.eeveebank.simplespring.model.Car;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Arrays.asList;

@Service
@Profile("part2")
public class DummyApiClient implements CarApiClient {

    @Override
    public List<Car> getAllCars() {
        return asList(
                new Car("1", "Hatchback", "RED"),
                new Car("2", "Sedan", "BLACK"),
                new Car("3", "4x4", "BLACK"),
                new Car("4", "4x4", "RED"),
                new Car("5", "SUV", "GREEN"));
    }

}
