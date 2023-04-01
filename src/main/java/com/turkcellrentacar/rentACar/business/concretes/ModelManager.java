package com.turkcellrentacar.rentACar.business.concretes;

import com.turkcellrentacar.rentACar.business.abstracts.ModelService;
import com.turkcellrentacar.rentACar.business.dto.requests.create.CreateModelRequest;
import com.turkcellrentacar.rentACar.business.dto.requests.update.UpdateModelRequest;
import com.turkcellrentacar.rentACar.business.dto.responses.create.CreateModelResponse;
import com.turkcellrentacar.rentACar.business.dto.responses.get.GetAllModelsResponse;
import com.turkcellrentacar.rentACar.business.dto.responses.get.GetBrandResponse;
import com.turkcellrentacar.rentACar.business.dto.responses.get.GetModelResponse;
import com.turkcellrentacar.rentACar.business.dto.responses.update.UpdateModelResponse;
import com.turkcellrentacar.rentACar.entities.Model;
import com.turkcellrentacar.rentACar.repository.ModelRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ModelManager implements ModelService {
    private final ModelRepository modelRepository;
    private final ModelMapper mapper;

    @Override
    public List<GetAllModelsResponse> getAll() {
        List<Model> models = modelRepository.findAll();
        List<GetAllModelsResponse> response = models.stream().map(model -> mapper.map(model, GetAllModelsResponse.class)).toList();
        return response;
    }

    @Override
    public GetModelResponse getById(int id) {
        checkIfModelExistsById(id);
        Model model = modelRepository.findById(id).orElseThrow();
        GetModelResponse response = mapper.map(model , GetModelResponse.class);

        return response;
    }

    @Override
    public CreateModelResponse add(CreateModelRequest createModelRequest) {
        checkIfModelExistsByName(createModelRequest.getName());
        Model model = mapper.map(createModelRequest, Model.class);
        model.setId(0);
        modelRepository.save(model);
        CreateModelResponse response = mapper.map(model , CreateModelResponse.class);

        return response;
    }

    @Override
    public UpdateModelResponse update(int id, UpdateModelRequest updateModelRequest) {
        checkIfModelExistsById(id);
        Model model = mapper.map(updateModelRequest, Model.class);
        model.setId(id);
        modelRepository.save(model);
        UpdateModelResponse response = mapper.map(model , UpdateModelResponse.class);

        return response;
    }

    @Override
    public void delete(int id) {
        checkIfModelExistsById(id);
        modelRepository.deleteById(id);

    }

    private void checkIfModelExistsById(int id) {
        if (!modelRepository.existsById(id)) throw new IllegalArgumentException("Böyle bir model mevcut değil");
    }

    private void checkIfModelExistsByName(String name) {
        if (modelRepository.existsByNameIgnoreCase(name)) {
            throw new RuntimeException("Bu model sistemde kayıtlı");
        }
    }
}
