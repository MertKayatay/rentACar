package com.turkcellrentacar.rentACar.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int modelYear;
    private String plate;
    private int state;  //1-Available 2- Ranted 3-Maintance
    private double dailyPrice;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private Model model;
}