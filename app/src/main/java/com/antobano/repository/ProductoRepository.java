package com.antobano.repository;

import com.antobano.model.Producto;
import java.util.ArrayList;
import java.util.List;

public class ProductoRepository {
    public List<Producto> obtenerCatalogo() {
        List<Producto> lista = new ArrayList<>();
        lista.add(new Producto(1, "Hamburguesa AntoBano Especial", "Doble carne artesanal, queso cheddar, tocineta y salsa de la casa", 18500));
        lista.add(new Producto(2, "Perro Caliente Supremo", "Salchicha americana, ripio de papa, queso fundido y piña caramelizada", 14000));
        lista.add(new Producto(3, "Salchipapa Mixta Familiar", "Papas francesas, salchicha ranchera, pollo desmechado y maíz tierno", 22000));
        return lista;
    }
}
