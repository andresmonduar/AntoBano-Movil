package com.antobano;

import com.antobano.model.Producto;
import com.antobano.service.PedidoService;
import org.junit.Test;
import static org.junit.Assert.*;

public class PedidoServiceTest {

    @Test
    public void testAutenticacionValidaEInvalida() {
        PedidoService service = new PedidoService();
        assertTrue(service.autenticarCliente("andres@antobano.com", "1234"));
        assertFalse(service.autenticarCliente("correo_sin_arroba", "1234"));
        assertFalse(service.autenticarCliente("andres@antobano.com", "12"));
    }

    @Test
    public void testCalculoTotalPedido() {
        PedidoService service = new PedidoService();
        service.agregarProducto(new Producto(1, "Hamburguesa", "Especial", 18500));
        service.agregarProducto(new Producto(2, "Perro", "Supremo", 14000));
        assertEquals(2, service.obtenerCantidadItems());
        assertEquals(32500.0, service.calcularTotal(), 0.01);
    }
}
