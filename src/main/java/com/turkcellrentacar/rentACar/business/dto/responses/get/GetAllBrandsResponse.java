package com.turkcellrentacar.rentACar.business.dto.responses.get;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GetAllBrandsResponse {
    private int id;
    private String name;
    //private List<GetAllModelsResponse> models;
}
