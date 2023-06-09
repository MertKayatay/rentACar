package com.turkcellrentacar.rentACar.business.dto.responses.get;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetAllCarResponse {
    private int id;
    private int modelId;
    private String plate;
    private String modelName;
    private String modelBrandName;
}
