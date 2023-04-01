package com.turkcellrentacar.rentACar.api.controllers;

import com.turkcellrentacar.rentACar.business.abstracts.ModelService;
import com.turkcellrentacar.rentACar.business.dto.requests.create.CreateModelRequest;
import com.turkcellrentacar.rentACar.business.dto.requests.update.UpdateModelRequest;
import com.turkcellrentacar.rentACar.business.dto.responses.create.CreateModelResponse;
import com.turkcellrentacar.rentACar.business.dto.responses.get.GetAllModelsResponse;
import com.turkcellrentacar.rentACar.business.dto.responses.get.GetModelResponse;
import com.turkcellrentacar.rentACar.business.dto.responses.update.UpdateModelResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/models")
public class ModelsController {
    private final ModelService modelService;

    @GetMapping
    public List<GetAllModelsResponse> getAll(){
        return modelService.getAll();
    }

    @GetMapping("/{id}")
    public GetModelResponse getById(@PathVariable int id){
        return modelService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)// 281
    public CreateModelResponse add(@RequestBody CreateModelRequest request){
        return modelService.add(request);
    }

    @PutMapping("/{id}")
    public UpdateModelResponse update(@PathVariable int id, @RequestBody UpdateModelRequest request){
        return modelService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)// 284
    public void delete(@PathVariable int id){
        modelService.delete(id);
    }
}
