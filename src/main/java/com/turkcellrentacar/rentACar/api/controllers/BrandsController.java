package com.turkcellrentacar.rentACar.api.controllers;

import com.turkcellrentacar.rentACar.business.abstracts.BrandService;
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
    public List<Brand> getAll(){
       return brandService.getAll();
   }

   @GetMapping("/{id}")
    public Brand getById(@PathVariable int id){
       return brandService.getById(id);
   }

   @PostMapping
   @ResponseStatus(HttpStatus.CREATED)
    public Brand add(@RequestBody Brand brand){
       return brandService.add(brand);
   }

   @PutMapping("/{id}")
    public Brand update(@PathVariable int id,@RequestBody Brand brand){
       return brandService.update(id,brand);
   }

   @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
       brandService.delete(id);
   }




}
