package com.example.techlab.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private double precio;
    private int stock;
    private int cantidadAComprar;
    private String description;

    public Producto() {}

    public Producto(String nombre, double precio, int stock) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.cantidadAComprar = 0;
    }

    // OTROS METODOS
    public void mostrarInfo(){
        System.out.println("#########################");
        System.out.printf("""
                Id: %s
                Nombre: %s
                Precio: %s
                Stock: %s
                """, this.id, this.nombre, this.precio, this.stock);
        System.out.println("#########################");
    }

    public boolean contieneNombre(String busqueda){
        String nombreMinuscula = this.nombre.toLowerCase();
        return nombreMinuscula.contains(busqueda.toLowerCase());
    }

}