package com.turkcellrentacar.rentACar.business.abstracts;

import com.turkcellrentacar.rentACar.business.dto.requests.create.CreateBrandRequest;
import com.turkcellrentacar.rentACar.business.dto.responses.create.CreateBrandResponse;
import com.turkcellrentacar.rentACar.business.dto.responses.get.GetAllBrandsResponse;
import com.turkcellrentacar.rentACar.business.dto.responses.get.GetBrandResponse;
import com.turkcellrentacar.rentACar.entities.Brand;

import java.util.List;

public interface BrandService {
    //CRUD operations

    List<GetAllBrandsResponse> getAll();
    GetBrandResponse getById(int id);
    CreateBrandResponse add(CreateBrandRequest createBrandRequest);
    Brand update(int id, Brand brand);
    void delete(int id);

}
