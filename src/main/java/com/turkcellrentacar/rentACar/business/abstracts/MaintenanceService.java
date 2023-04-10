package com.turkcellrentacar.rentACar.business.abstracts;

import com.turkcellrentacar.rentACar.business.dto.requests.create.CreateMaintenanceRequest;
import com.turkcellrentacar.rentACar.business.dto.requests.update.UpdateMaintenanceRequest;
import com.turkcellrentacar.rentACar.business.dto.responses.create.CreateMaintenanceResponse;
import com.turkcellrentacar.rentACar.business.dto.responses.get.GetAllMaintenancesResponse;
import com.turkcellrentacar.rentACar.business.dto.responses.get.GetMaintenanceResponse;
import com.turkcellrentacar.rentACar.business.dto.responses.update.UpdateMaintenanceResponse;

import java.util.List;

public interface MaintenanceService {
    List<GetAllMaintenancesResponse> getAll();
    GetMaintenanceResponse getById(int id);
    GetMaintenanceResponse returnCarFromMaintenance(int carId);
    CreateMaintenanceResponse add(CreateMaintenanceRequest request);
    UpdateMaintenanceResponse update(int id, UpdateMaintenanceRequest request);
    void delete(int id);
}
