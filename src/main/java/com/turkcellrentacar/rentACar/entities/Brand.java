package com.turkcellrentacar.rentACar.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "brands")
public class Brand {
    @Id //Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

}
