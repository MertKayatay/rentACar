package com.turkcellrentacar.rentACar.repository;

import com.turkcellrentacar.rentACar.entities.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental, Integer> {
}
