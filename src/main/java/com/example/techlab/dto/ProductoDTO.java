package com.example.techlab.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductoDTO {
    private Long id;

    private String nombre;
    private double precio;
    private int stock;
    private int cantidadAComprar;
    private String description;

}