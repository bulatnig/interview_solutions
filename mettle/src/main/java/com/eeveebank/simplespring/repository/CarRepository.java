package com.eeveebank.simplespring.repository;

import com.eeveebank.simplespring.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, String> {

}
