package com.example.techlab.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Repository;
import com.example.techlab.entity.Producto;

@Repository
public class ProductRepository {
    private final ArrayList<Producto> productos;
    private final AtomicLong idGenerator;

    public ProductRepository() {
        this.productos = new ArrayList<>();
        this.idGenerator = new AtomicLong(0);
        this.agregarProductosDeEjemplo();
    }

    public String agregarProducto(Producto producto){
        producto.setId(idGenerator.incrementAndGet());
        productos.add(producto);

        return "☣ Producto cargado exitosamente! ☣ \n ID del Producto: " + producto.getId();
    }

    public List<Producto> listarProductos() {
        return new ArrayList<>(this.productos);
    }

    public ArrayList<Producto> buscarProducto(String busqueda) {
        ArrayList<Producto> productosEncontrados = new ArrayList<>();

        for (Producto producto : productos){
            if (producto.contieneNombre(busqueda)){
                productosEncontrados.add(producto);
            }
        }

        return productosEncontrados;
    }

    public Producto buscarPorId(Long id) {
        for (Producto producto : productos){
            if (producto.getId().equals(id)){
                return producto;
            }
        }
        return null;
    }

    public Producto eliminarProducto(Producto producto) {
        this.productos.remove(producto);
        return producto;
    }

    private void agregarProductosDeEjemplo() {
        Producto[] productosEjemplo = {
                new Producto("Monitor", 1000, 10),
                new Producto("Micrófono", 2000, 10),
                new Producto("Teclado mecánico", 1500, 15),
                new Producto("Mouse gamer", 1200, 20),
                new Producto("Laptop", 15000, 5),
                new Producto("Tablet", 8000, 7),
                new Producto("Disco duro externo", 2500, 12),
                new Producto("Memoria USB 64GB", 500, 25),
                new Producto("Router Wi-Fi", 1800, 10),
                new Producto("Smartphone", 12000, 8),
                new Producto("Audífonos Bluetooth", 2200, 18),
                new Producto("Cámara Web HD", 1300, 10),
                new Producto("Impresora", 4000, 6),
                new Producto("Proyector", 9000, 4),
                new Producto("Reproductor multimedia", 3000, 9),
                new Producto("Smartwatch", 3500, 11),
                new Producto("Lector de tarjetas", 800, 14),
                new Producto("Estabilizador de voltaje", 1100, 10),
                new Producto("Cable HDMI", 300, 30),
                new Producto("Panel táctil USB", 2000, 5)
        };

        for (Producto producto : productosEjemplo) {
            producto.setId(idGenerator.incrementAndGet());
            productos.add(producto);
        }
    }
}