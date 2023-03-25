package com.turkcellrentacar.rentACar.business.concretes;

import com.turkcellrentacar.rentACar.business.abstracts.BrandService;
import com.turkcellrentacar.rentACar.entities.Brand;
import com.turkcellrentacar.rentACar.repository.BrandRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BrandManager implements BrandService {
    private final BrandRepository brandRepository;



    @Override
    public List<Brand> getAll() {
        return brandRepository.findAll();
    }

    @Override
    public Brand getById(int id) {
        checkIfBrandExists(id);
        return brandRepository.findById(id).orElseThrow();
    }

    @Override
    public Brand add(Brand brand) {
        return brandRepository.save(brand);
    }

    @Override
    public Brand update(int id, Brand brand) {
        checkIfBrandExists(id);
        brand.setId(id);
        return brandRepository.save(brand);
    }

    @Override
    public void delete(int id) {
        checkIfBrandExists(id);
        brandRepository.deleteById(id);

    }

    //Business rules

    private void checkIfBrandExists(int id) {
        if (!brandRepository.existsById(id)) throw new IllegalArgumentException("Böyle bir marka mevcut değil");
    }
}
