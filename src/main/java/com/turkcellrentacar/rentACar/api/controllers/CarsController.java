package com.turkcellrentacar.rentACar.api.controllers;

import com.turkcellrentacar.rentACar.business.abstracts.CarService;
import com.turkcellrentacar.rentACar.business.dto.requests.create.CreateCarRequest;
import com.turkcellrentacar.rentACar.business.dto.requests.update.UpdateCarRequest;
import com.turkcellrentacar.rentACar.business.dto.responses.create.CreateCarResponse;
import com.turkcellrentacar.rentACar.business.dto.responses.get.GetAllCarResponse;
import com.turkcellrentacar.rentACar.business.dto.responses.get.GetCarResponse;
import com.turkcellrentacar.rentACar.business.dto.responses.update.UpdateCarResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/cars")
public class CarsController {
    private final CarService service;
    @GetMapping("/filter/{pref}")
    List<GetAllCarResponse> getAll(@PathVariable int pref){
        return  service.getAll(pref);
    }
    @GetMapping("/{id}") public GetCarResponse getById(@PathVariable int id){
        return  service.getById(id);
    }
    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreateCarResponse add(@RequestBody CreateCarRequest request){
        return  service.add(request);
    }
    @PostMapping("/{id}")
    public UpdateCarResponse update(@PathVariable int id, @RequestBody UpdateCarRequest request){
        return service.update(id,request);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
        service.delete(id);
    }

}
