package com.example.techlab.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.techlab.entity.Producto;
import java.util.List;
import com.example.techlab.exception.ProductNotFoundException;
import com.example.techlab.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping("/")
    public ResponseEntity<Producto> createProduct(@RequestBody Producto producto){
        try {
            Producto productoCreado = this.service.agregarProducto(producto);
            return ResponseEntity.status(HttpStatus.CREATED).body(productoCreado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<Producto>> obtenerListadoProductos(){
        List<Producto> productos = this.service.listarProductos();
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/find")
    public ResponseEntity<List<Producto>> obtenerProductos(@RequestParam String nombreBusqueda){
        try {
            List<Producto> productos = this.service.buscarProducto(nombreBusqueda);
            return ResponseEntity.ok(productos);
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProductoPorId(@PathVariable Long id){
        try {
            Producto producto = this.service.buscarPorId(id);
            return ResponseEntity.ok(producto);
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> editarPrecioProducto(@PathVariable Long id, @RequestParam Double nuevoPrecio){
        try {
            Producto producto = this.service.editarProducto(id, nuevoPrecio);
            return ResponseEntity.ok(producto);
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Producto> borrarProducto(@PathVariable Long id){
        try {
            Producto producto = this.service.eliminarProducto(id);
            return ResponseEntity.ok(producto);
        } catch (ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}