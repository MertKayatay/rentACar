package com.turkcellrentacar.rentACar.business.abstracts;

import com.turkcellrentacar.rentACar.business.dto.requests.create.CreateCarRequest;
import com.turkcellrentacar.rentACar.business.dto.requests.create.CreateModelRequest;
import com.turkcellrentacar.rentACar.business.dto.requests.update.UpdateCarRequest;
import com.turkcellrentacar.rentACar.business.dto.requests.update.UpdateModelRequest;
import com.turkcellrentacar.rentACar.business.dto.responses.create.CreateCarResponse;
import com.turkcellrentacar.rentACar.business.dto.responses.create.CreateModelResponse;
import com.turkcellrentacar.rentACar.business.dto.responses.get.GetAllCarResponse;
import com.turkcellrentacar.rentACar.business.dto.responses.get.GetAllModelsResponse;
import com.turkcellrentacar.rentACar.business.dto.responses.get.GetCarResponse;
import com.turkcellrentacar.rentACar.business.dto.responses.get.GetModelResponse;
import com.turkcellrentacar.rentACar.business.dto.responses.update.UpdateCarResponse;
import com.turkcellrentacar.rentACar.business.dto.responses.update.UpdateModelResponse;

import java.util.List;

public interface CarService {
    List<GetAllCarResponse> getAll();
    GetCarResponse getById(int id);
    CreateCarResponse add(CreateCarRequest createCarRequest);
    UpdateCarResponse update(int id, UpdateCarRequest updateCarRequest);
    void delete(int id);
}
