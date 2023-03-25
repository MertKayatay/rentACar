package com.turkcellrentacar.rentACar.business.abstracts;

import com.turkcellrentacar.rentACar.entities.Brand;

import java.util.List;

public interface BrandService {
    //CRUD operations

    List<Brand> getAll();
    Brand getById(int id);
    Brand add(Brand brand);
    Brand update(int id, Brand brand);
    void delete(int id);

}
