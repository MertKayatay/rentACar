package com.turkcellrentacar.rentACar.business.concretes;

import com.turkcellrentacar.rentACar.business.abstracts.BrandService;
import com.turkcellrentacar.rentACar.business.dto.requests.create.CreateBrandRequest;
import com.turkcellrentacar.rentACar.business.dto.requests.update.UpdateBrandRequest;
import com.turkcellrentacar.rentACar.business.dto.responses.create.CreateBrandResponse;
import com.turkcellrentacar.rentACar.business.dto.responses.get.GetAllBrandsResponse;
import com.turkcellrentacar.rentACar.business.dto.responses.get.GetBrandResponse;
import com.turkcellrentacar.rentACar.business.dto.responses.update.UpdateBrandResponse;
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
        checkIfBrandExistsById(id);
        Brand brand = brandRepository.findById(id).orElseThrow();
        GetBrandResponse getBrandResponse = mapper.map(brand, GetBrandResponse.class);

        return getBrandResponse;
    }

    @Override
    public CreateBrandResponse add(CreateBrandRequest createBrandRequest) {
        checkIfBrandExistsByName(createBrandRequest.getName());

        Brand brand = mapper.map(createBrandRequest, Brand.class);
        brand.setId(0);
        Brand createdBrand = brandRepository.save(brand);
        CreateBrandResponse createBrandResponse = mapper.map(createdBrand, CreateBrandResponse.class);

        return createBrandResponse;
    }

    @Override
    public UpdateBrandResponse update(int id, UpdateBrandRequest updateBrandRequest) {
        checkIfBrandExistsById(id);
        Brand brand = mapper.map(updateBrandRequest, Brand.class);
        brand.setId(id);
        brandRepository.save(brand);
        UpdateBrandResponse updateBrandResponse = mapper.map(brand, UpdateBrandResponse.class);
        return updateBrandResponse;
    }

    @Override
    public void delete(int id) {
        checkIfBrandExistsById(id);
        brandRepository.deleteById(id);

    }

    //Business rules

    private void checkIfBrandExistsById(int id) {
        if (!brandRepository.existsById(id)) throw new IllegalArgumentException("Böyle bir marka mevcut değil");
    }

    private void checkIfBrandExistsByName(String name) {
        if (brandRepository.existsByNameIgnoreCase(name)) {
            throw new RuntimeException("Bu marka sistemde kayıtlı");
        }
    }
}
