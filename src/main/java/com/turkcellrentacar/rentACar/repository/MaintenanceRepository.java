package com.turkcellrentacar.rentACar.repository;

import com.turkcellrentacar.rentACar.entities.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaintenanceRepository extends JpaRepository<Maintenance, Integer> {
    boolean existsByCarId(int carId);
}
