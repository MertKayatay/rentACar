package com.turkcellrentacar.rentACar.repository;

import com.turkcellrentacar.rentACar.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Integer> {
    boolean existsByPlateIgnoreCase(String plate);


}
