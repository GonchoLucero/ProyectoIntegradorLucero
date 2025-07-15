package com.example.techlab.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.techlab.entity.Producto;
import com.example.techlab.exception.ProductNotFoundException;
import com.example.techlab.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Producto agregarProducto(Producto producto){
        if (producto.getNombre() == null || producto.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto no puede estar vacío");
        }

        if (producto.getPrecio() <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor a 0");
        }

        if (producto.getStock() < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo");
        }

        Long nextId = (long) (repository.listarProductos().size() + 1);
        producto.setId(nextId);

        repository.agregarProducto(producto);
        return producto;
    }

    public List<Producto> listarProductos() {
        return this.repository.listarProductos();
    }

    public List<Producto> buscarProducto(String busqueda) {
        if (busqueda == null || busqueda.trim().isEmpty()) {
            throw new IllegalArgumentException("El término de búsqueda no puede estar vacío");
        }

        List<Producto> encontrados = this.repository.buscarProducto(busqueda);

        if (encontrados.isEmpty()) {
            throw new ProductNotFoundException(busqueda);
        }

        return encontrados;
    }

    public Producto buscarPorId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo");
        }

        Producto encontrado = this.repository.buscarPorId(id);

        if (encontrado == null) {
            throw new ProductNotFoundException(id.toString());
        }

        return encontrado;
    }

    public Producto editarProducto(Long id, Double nuevoPrecio){
        if (nuevoPrecio == null || nuevoPrecio <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor a 0");
        }

        Producto encontrado = this.buscarPorId(id);
        encontrado.setPrecio(nuevoPrecio);

        return encontrado;
    }

    public Producto eliminarProducto(Long id) {
        Producto encontrado = this.buscarPorId(id);
        this.repository.eliminarProducto(encontrado);

        return encontrado;
    }
}