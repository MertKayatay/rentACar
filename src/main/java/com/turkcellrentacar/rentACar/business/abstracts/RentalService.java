package com.turkcellrentacar.rentACar.business.abstracts;

import com.turkcellrentacar.rentACar.business.dto.requests.create.CreateRentalRequest;
import com.turkcellrentacar.rentACar.business.dto.requests.update.UpdateRentalRequest;
import com.turkcellrentacar.rentACar.business.dto.responses.create.CreateRentalResponse;
import com.turkcellrentacar.rentACar.business.dto.responses.get.GetAllRentalsResponse;
import com.turkcellrentacar.rentACar.business.dto.responses.get.GetRentalResponse;
import com.turkcellrentacar.rentACar.business.dto.responses.update.UpdateRentalResponse;

import java.util.List;

public interface RentalService {
    List<GetAllRentalsResponse> getAll();
    GetRentalResponse getById(int id);
    CreateRentalResponse add(CreateRentalRequest request);
    UpdateRentalResponse update(int id, UpdateRentalRequest request);
    void delete(int id);
}
