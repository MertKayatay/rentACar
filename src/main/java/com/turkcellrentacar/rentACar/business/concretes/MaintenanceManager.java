package com.turkcellrentacar.rentACar.business.concretes;

import com.turkcellrentacar.rentACar.business.abstracts.CarService;
import com.turkcellrentacar.rentACar.business.abstracts.MaintenanceService;
import com.turkcellrentacar.rentACar.business.dto.requests.create.CreateMaintenanceRequest;
import com.turkcellrentacar.rentACar.business.dto.requests.update.UpdateCarRequest;
import com.turkcellrentacar.rentACar.business.dto.requests.update.UpdateMaintenanceRequest;
import com.turkcellrentacar.rentACar.business.dto.responses.create.CreateMaintenanceResponse;
import com.turkcellrentacar.rentACar.business.dto.responses.get.GetAllMaintenancesResponse;
import com.turkcellrentacar.rentACar.business.dto.responses.get.GetCarResponse;
import com.turkcellrentacar.rentACar.business.dto.responses.get.GetMaintenanceResponse;
import com.turkcellrentacar.rentACar.business.dto.responses.update.UpdateMaintenanceResponse;
import com.turkcellrentacar.rentACar.entities.Maintenance;
import com.turkcellrentacar.rentACar.repository.MaintenanceRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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
        List<GetAllMaintenancesResponse> responses = maintenances
                .stream()
                .map(maintenance -> mapper.map(maintenance, GetAllMaintenancesResponse.class))
                .toList();
        return responses;

    }

    @Override
    public GetMaintenanceResponse getById(int id) {
        checkIfMaintenanceExist(id);
        Maintenance maintenance = maintenanceRepository.findById(id).orElseThrow();
        GetMaintenanceResponse response = mapper.map(maintenance, GetMaintenanceResponse.class);
        return response;
    }

    @Override
    public CreateMaintenanceResponse add(CreateMaintenanceRequest request) {
        GetCarResponse carResponse=carService.getById(request.getCarId());
        checkIfCarAvailable(carResponse.getState());
        Maintenance maintenance = mapper.map(request, Maintenance.class);
        maintenance.setId(0);

        carResponse.getState();
        carService.update(carResponse.getId(),mapper.map(carResponse, UpdateCarRequest.class));//db savelemek için
        maintenanceRepository.save(maintenance);
        CreateMaintenanceResponse response = mapper.map(maintenance, CreateMaintenanceResponse.class);
        response.setCarState(3);
        return response;
    }

    @Override
    public UpdateMaintenanceResponse update(int id, UpdateMaintenanceRequest request) {
        checkIfMaintenanceExist(id);
        Maintenance maintenance = mapper.map(request, Maintenance.class);
        maintenance.setId(id);
        maintenanceRepository.save(maintenance);
        UpdateMaintenanceResponse response = mapper.map(maintenance, UpdateMaintenanceResponse.class);
        return response;
    }

    @Override
    public void delete(int id) {
        checkIfMaintenanceExist(id);
        maintenanceRepository.deleteById(id);
    }

    private void checkIfMaintenanceExist(int id) {

        if (!maintenanceRepository.existsById(id)) throw new IllegalArgumentException("There is no such a maintenance!");
    }

    private void checkIfCarAvailable(int state){
        if(state!=1) throw new IllegalArgumentException("The car is not available!");
    }





}
