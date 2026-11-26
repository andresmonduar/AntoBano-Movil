package com.antobano.service;

import com.antobano.model.Producto;
import java.util.ArrayList;
import java.util.List;

public class PedidoService {
    private final List<Producto> carrito = new ArrayList<>();

    public boolean autenticarCliente(String correo, String clave) {
        if (correo == null || clave == null) return false;
        return correo.trim().contains("@") && clave.trim().length() >= 4;
    }

    public void agregarProducto(Producto producto) {
        if (producto != null) {
            carrito.add(producto);
        }
    }

    public double calcularTotal() {
        double total = 0;
        for (Producto p : carrito) {
            total += p.getPrecio();
        }
        return total;
    }

    public int obtenerCantidadItems() {
        return carrito.size();
    }

    public void limpiarPedido() {
        carrito.clear();
    }
}
