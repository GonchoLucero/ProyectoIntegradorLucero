package com.example.techlab.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.example.techlab.entity.Producto;
import com.example.techlab.exception.ProductNotFoundException;
import com.example.techlab.repository.ProductoRepository;

@Service
public class ProductService {

    private final ProductoRepository repositoryJpa;

    public ProductService(ProductoRepository repositoryJpa) {
        this.repositoryJpa = repositoryJpa;
    }

    public Producto agregarProducto(Producto producto){
        // Validaciones de negocio antes de guardar
        if (producto.getNombre() == null || producto.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto no puede estar vacío");
        }

        if (producto.getPrecio() <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor a 0");
        }

        if (producto.getStock() < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo");
        }

        return this.repositoryJpa.save(producto);
    }

    public List<Producto> listarProductos() {
        return this.repositoryJpa.findAll();
    }

    public List<Producto> buscarProducto(String busqueda) {
        if (busqueda == null || busqueda.trim().isEmpty()) {
            throw new IllegalArgumentException("El término de búsqueda no puede estar vacío");
        }

        List<Producto> todosLosProductos = this.repositoryJpa.findAll();
        List<Producto> encontrados = todosLosProductos.stream()
                .filter(producto -> producto.contieneNombre(busqueda))
                .toList();

        if (encontrados.isEmpty()) {
            throw new ProductNotFoundException(busqueda);
        }

        return encontrados;
    }

    public Producto buscarPorId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo");
        }

        Optional<Producto> encontrado = this.repositoryJpa.findById(id);

        if (encontrado.isEmpty()) {
            throw new ProductNotFoundException(id.toString());
        }

        return encontrado.get();
    }

    // Versión alternativa más concisa usando orElseThrow
    public Producto buscarPorIdv2(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo");
        }

        return this.repositoryJpa.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id.toString()));
    }

    public Producto editarProducto(Long id, Double nuevoPrecio){
        if (nuevoPrecio == null || nuevoPrecio <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor a 0");
        }

        Producto encontrado = this.buscarPorId(id);
        encontrado.setPrecio(nuevoPrecio);

        return this.repositoryJpa.save(encontrado);
    }

    public Producto eliminarProducto(Long id) {
        Producto encontrado = this.buscarPorId(id);
        this.repositoryJpa.delete(encontrado);

        return encontrado;
    }
}