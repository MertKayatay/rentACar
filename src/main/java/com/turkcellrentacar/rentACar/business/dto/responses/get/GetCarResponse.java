package com.turkcellrentacar.rentACar.business.dto.responses.get;

import com.turkcellrentacar.rentACar.entities.enums.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetCarResponse {
    private int id;
    private int modelYear;
    private String plate;
    private State state;//1-Available 2-Rented 3-Maintenance
    private double dailyPrice;
    private int modelId;
}
