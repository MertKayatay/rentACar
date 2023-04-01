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
    public List<GetAllCarResponse> getAll() {
        List<Car> cars = carRepository.findAll();
        List<GetAllCarResponse> response = cars.stream().map(car -> mapper.map(car, GetAllCarResponse.class)).toList();
        return response;
    }

    @Override
    public GetCarResponse getById(int id) {
        checkIfCarExistsById(id);
        Car car = carRepository.findById(id).orElseThrow();
        GetCarResponse response = mapper.map(car , GetCarResponse.class);
        return response;
    }

    @Override
    public CreateCarResponse add(CreateCarRequest createCarRequest) {
        checkIfCarExistsByName(createCarRequest.getPlate());
        Car car = mapper.map(createCarRequest, Car.class);
        car.setId(0);
        carRepository.save(car);
        CreateCarResponse response = mapper.map(car , CreateCarResponse.class);
        return response;
    }

    @Override
    public UpdateCarResponse update(int id, UpdateCarRequest updateCarRequest) {
        checkIfCarExistsById(id);
        Car car = mapper.map(updateCarRequest, Car.class);
        car.setId(id);
        carRepository.save(car);
        UpdateCarResponse response = mapper.map(car , UpdateCarResponse.class);
        return response;
    }

    @Override
    public void delete(int id) {
        checkIfCarExistsById(id);
        carRepository.deleteById(id);

    }

    private void checkIfCarExistsById(int id) {
        if (!carRepository.existsById(id)) throw new IllegalArgumentException("Böyle bir araç mevcut değil");
    }

    private void checkIfCarExistsByName(String plate) {
        if (carRepository.existsByPlateIgnoreCase(plate)) {
            throw new RuntimeException("Bu araç sistemde kayıtlı");
        }
    }
}
