package com.turkcellrentacar.rentACar.business.concretes;

import com.turkcellrentacar.rentACar.business.abstracts.BrandService;
import com.turkcellrentacar.rentACar.business.dto.requests.create.CreateBrandRequest;
import com.turkcellrentacar.rentACar.business.dto.responses.create.CreateBrandResponse;
import com.turkcellrentacar.rentACar.business.dto.responses.get.GetAllBrandsResponse;
import com.turkcellrentacar.rentACar.business.dto.responses.get.GetBrandResponse;
import com.turkcellrentacar.rentACar.entities.Brand;
import com.turkcellrentacar.rentACar.repository.BrandRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BrandManager implements BrandService {
    private final BrandRepository brandRepository;
    private final ModelMapper mapper;



    @Override
    public List<GetAllBrandsResponse> getAll() {
        List<Brand> brands = brandRepository.findAll();
        List<GetAllBrandsResponse> getAllBrandsResponse = brands.stream()
                .map(brand -> mapper.map(brand, GetAllBrandsResponse.class)).toList();

        return getAllBrandsResponse;
    }

    @Override
    public GetBrandResponse getById(int id) {
        checkIfBrandExists(id);
        Brand brand = brandRepository.findById(id).orElseThrow();
        GetBrandResponse getBrandResponse = mapper.map(brand, GetBrandResponse.class);

        return getBrandResponse;
    }

    @Override
    public CreateBrandResponse add(CreateBrandRequest createBrandRequest) {
        Brand brand = mapper.map(createBrandRequest, Brand.class);
        brand.setId(0);
        brandRepository.save(brand);
        CreateBrandResponse createBrandResponse = mapper.map(brand, CreateBrandResponse.class);

        return createBrandResponse;
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
