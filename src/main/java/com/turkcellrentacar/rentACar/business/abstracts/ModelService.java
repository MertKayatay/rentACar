package com.turkcellrentacar.rentACar.business.abstracts;



import com.turkcellrentacar.rentACar.business.dto.requests.create.CreateModelRequest;
import com.turkcellrentacar.rentACar.business.dto.requests.update.UpdateModelRequest;
import com.turkcellrentacar.rentACar.business.dto.responses.create.CreateModelResponse;
import com.turkcellrentacar.rentACar.business.dto.responses.get.GetAllModelsResponse;
import com.turkcellrentacar.rentACar.business.dto.responses.get.GetModelResponse;
import com.turkcellrentacar.rentACar.business.dto.responses.update.UpdateModelResponse;

import java.util.List;

public interface ModelService {
    List<GetAllModelsResponse> getAll();
    GetModelResponse getById(int id);
    CreateModelResponse add(CreateModelRequest createModelRequest);
    UpdateModelResponse update(int id, UpdateModelRequest updateModelRequest);
    void delete(int id);
}
