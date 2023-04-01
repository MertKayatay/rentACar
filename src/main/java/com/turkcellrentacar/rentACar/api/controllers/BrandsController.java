package com.turkcellrentacar.rentACar.api.controllers;

import com.turkcellrentacar.rentACar.business.abstracts.BrandService;
import com.turkcellrentacar.rentACar.business.dto.requests.create.CreateBrandRequest;
import com.turkcellrentacar.rentACar.business.dto.requests.update.UpdateBrandRequest;
import com.turkcellrentacar.rentACar.business.dto.responses.create.CreateBrandResponse;
import com.turkcellrentacar.rentACar.business.dto.responses.get.GetAllBrandsResponse;
import com.turkcellrentacar.rentACar.business.dto.responses.get.GetBrandResponse;
import com.turkcellrentacar.rentACar.business.dto.responses.update.UpdateBrandResponse;
import com.turkcellrentacar.rentACar.entities.Brand;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brands")
@AllArgsConstructor
public class BrandsController {
   private final BrandService brandService;

   @GetMapping
    public List<GetAllBrandsResponse> getAll(){
       return brandService.getAll();
   }

   @GetMapping("/{id}")
    public GetBrandResponse getById(@PathVariable int id){
       return brandService.getById(id);
   }

   @PostMapping
   @ResponseStatus(HttpStatus.CREATED)
    public CreateBrandResponse add(@RequestBody CreateBrandRequest createBrandRequest){
       return brandService.add(createBrandRequest);
   }

    @PutMapping("/{id}")
    public UpdateBrandResponse update(@PathVariable int id, @RequestBody UpdateBrandRequest updateBrandRequest) {
        //brand.setId(id);
        return brandService.update(id, updateBrandRequest);
    }

   @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
       brandService.delete(id);
   }




}
