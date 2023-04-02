package com.turkcellrentacar.rentACar.business.concretes;

import com.turkcellrentacar.rentACar.business.abstracts.CarService;
import com.turkcellrentacar.rentACar.business.dto.requests.create.CreateCarRequest;
import com.turkcellrentacar.rentACar.business.dto.requests.update.UpdateCarRequest;
import com.turkcellrentacar.rentACar.business.dto.responses.create.CreateCarResponse;
import com.turkcellrentacar.rentACar.business.dto.responses.create.CreateModelResponse;
import com.turkcellrentacar.rentACar.business.dto.responses.get.GetAllCarResponse;
import com.turkcellrentacar.rentACar.business.dto.responses.get.GetAllModelsResponse;
import com.turkcellrentacar.rentACar.business.dto.responses.get.GetCarResponse;
import com.turkcellrentacar.rentACar.business.dto.responses.get.GetModelResponse;
import com.turkcellrentacar.rentACar.business.dto.responses.update.UpdateCarResponse;
import com.turkcellrentacar.rentACar.business.dto.responses.update.UpdateModelResponse;
import com.turkcellrentacar.rentACar.entities.Car;
import com.turkcellrentacar.rentACar.entities.Model;
import com.turkcellrentacar.rentACar.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CarManager implements CarService {
    private final CarRepository carRepository;
    private final ModelMapper mapper;

    @Override
    public List<GetAllCarResponse> getAll(int preference) {
        List<Car> cars;
        if (preference == 1) {
            cars = carRepository.findAllByStateNot(3);
        } else {
            cars = carRepository.findAll();
        }
        List<GetAllCarResponse> responses = cars
                .stream()
                .map(car -> mapper.map(car, GetAllCarResponse.class))
                .toList();
        return responses;
    }

    @Override
    public GetCarResponse getById(int id) {
        checkIfCarExist(id);
        Car car = carRepository.findById(id).orElseThrow();
        GetCarResponse response = mapper.map(car , GetCarResponse.class);
        return response;
    }

    @Override
    public CreateCarResponse add(CreateCarRequest createCarRequest) {

        Car car = mapper.map(createCarRequest, Car.class);
        car.setId(0);
        carRepository.save(car);
        CreateCarResponse response = mapper.map(car, CreateCarResponse.class);
        return response;
    }

    @Override
    public UpdateCarResponse update(int id, UpdateCarRequest updateCarRequest) {
        checkIfCarExist(id);
        Car car = mapper.map(updateCarRequest, Car.class);
        car.setId(id);
        carRepository.save(car);
        UpdateCarResponse response = mapper.map(car, UpdateCarResponse.class);
        return response;
    }

    @Override
    public void delete(int id) {
        checkIfCarExist(id);
        carRepository.deleteById(id);

    }

    private void checkIfCarExist(int id) {

        if (!carRepository.existsById(id)) throw new IllegalArgumentException("There is no such a car!");
    }



}
