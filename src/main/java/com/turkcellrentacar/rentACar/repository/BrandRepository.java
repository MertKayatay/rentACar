package com.turkcellrentacar.rentACar.repository;

import com.turkcellrentacar.rentACar.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BrandRepository extends JpaRepository<Brand, Integer> {
    // Custom queries
}
