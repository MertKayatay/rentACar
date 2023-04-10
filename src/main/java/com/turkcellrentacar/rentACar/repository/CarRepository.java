package com.turkcellrentacar.rentACar.repository;

import com.turkcellrentacar.rentACar.entities.Car;
import com.turkcellrentacar.rentACar.entities.enums.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Integer> {
    List<Car> findAllByStateIsNot(State state);


}
