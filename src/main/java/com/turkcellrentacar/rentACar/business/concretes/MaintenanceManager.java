package com.turkcellrentacar.rentACar.business.concretes;

import com.turkcellrentacar.rentACar.business.abstracts.CarService;
import com.turkcellrentacar.rentACar.business.abstracts.MaintenanceService;
import com.turkcellrentacar.rentACar.business.dto.requests.create.CreateMaintenanceRequest;
import com.turkcellrentacar.rentACar.business.dto.requests.update.UpdateMaintenanceRequest;
import com.turkcellrentacar.rentACar.business.dto.responses.create.CreateMaintenanceResponse;
import com.turkcellrentacar.rentACar.business.dto.responses.get.GetAllMaintenancesResponse;
import com.turkcellrentacar.rentACar.business.dto.responses.get.GetMaintenanceResponse;
import com.turkcellrentacar.rentACar.business.dto.responses.update.UpdateMaintenanceResponse;
import com.turkcellrentacar.rentACar.entities.Maintenance;
import com.turkcellrentacar.rentACar.entities.enums.State;
import com.turkcellrentacar.rentACar.repository.MaintenanceRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class MaintenanceManager implements MaintenanceService {
    private final MaintenanceRepository maintenanceRepository;
    private final ModelMapper mapper;
    private final CarService carService;

    @Override
    public List<GetAllMaintenancesResponse> getAll() {
        List<Maintenance> maintenances = maintenanceRepository.findAll();
        List<GetAllMaintenancesResponse> response = maintenances
                .stream()
                .map(maintenance -> mapper.map(maintenance, GetAllMaintenancesResponse.class))
                .toList();

        return response;
    }

    @Override
    public GetMaintenanceResponse getById(int id) {
        checkIfMaintenanceExists(id);
        Maintenance maintenance = maintenanceRepository.findById(id).orElseThrow();
        GetMaintenanceResponse response = mapper.map(maintenance, GetMaintenanceResponse.class);

        return response;
    }

    @Override
    public GetMaintenanceResponse returnCarFromMaintenance(int carId) {
        checkIfCarIsNotUnderMaintenance(carId);
        Maintenance maintenance = maintenanceRepository.findByCarIdAndIsCompletedIsFalse(carId);
        maintenance.setCompleted(true);
        maintenance.setEndDate(LocalDateTime.now());
        maintenanceRepository.save(maintenance); // Update
        carService.changeState(carId, State.AVAILABLE);
        GetMaintenanceResponse response = mapper.map(maintenance, GetMaintenanceResponse.class);

        return response;
    }

    @Override
    public CreateMaintenanceResponse add(CreateMaintenanceRequest request) {
        checkIfCarUnderMaintenance(request);
        checkCarAvailabilityForMaintenance(request);
        Maintenance maintenance = mapper.map(request, Maintenance.class);
        maintenance.setId(0);
        maintenance.setCompleted(false);
        maintenance.setStartDate(LocalDateTime.now());
        maintenance.setEndDate(null);
        maintenanceRepository.save(maintenance);
        carService.changeState(request.getCarId(), State.MAINTENANCE);
        CreateMaintenanceResponse response = mapper.map(maintenance, CreateMaintenanceResponse.class);

        return response;
    }

    @Override
    public UpdateMaintenanceResponse update(int id, UpdateMaintenanceRequest request) {
        checkIfMaintenanceExists(id);
        Maintenance maintenance = mapper.map(request, Maintenance.class);
        maintenance.setId(id);
        maintenanceRepository.save(maintenance);
        UpdateMaintenanceResponse response = mapper.map(maintenance, UpdateMaintenanceResponse.class);

        return response;
    }

    @Override
    public void delete(int id) {
        checkIfMaintenanceExists(id);
        maintenanceRepository.deleteById(id);
    }

    private void checkIfMaintenanceExists(int id) {
        if (!maintenanceRepository.existsById(id)) {
            throw new RuntimeException("Böyle bir bakım bilgisine ulaşılamadı!");
        }
    }

    private void checkIfCarIsNotUnderMaintenance(int carId) {
        if (!maintenanceRepository.existsByCarIdAndIsCompletedIsFalse(carId)) {
            throw new RuntimeException("Bakımda böyle bir araç bulunamadı!");
        }
    }

    private void checkIfCarUnderMaintenance(CreateMaintenanceRequest request) {
        if (maintenanceRepository.existsByCarIdAndIsCompletedIsFalse(request.getCarId())) {
            throw new RuntimeException("Araç şuanda bakımda!");
        }
    }

    private void checkCarAvailabilityForMaintenance(CreateMaintenanceRequest request) {
        if (carService.getById(request.getCarId()).getState().equals(State.RENTED)) {
            throw new RuntimeException("Araç kirada olduğu için bakıma alınamaz!");
        }
    }
}
