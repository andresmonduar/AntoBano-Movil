package com.antobano.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.antobano.R;
import com.antobano.model.Producto;
import com.antobano.repository.ProductoRepository;
import com.antobano.service.PedidoService;
import java.util.List;

public class CatalogoActivity extends AppCompatActivity {

    private final ProductoRepository repository = new ProductoRepository();
    private final PedidoService pedidoService = new PedidoService();
    private TextView tvResumenPedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogo);

        String correo = getIntent().getStringExtra("USUARIO_CORREO");
        TextView tvBienvenida = findViewById(R.id.tvBienvenidaUsuario);
        tvBienvenida.setText("Bienvenido a AntoBano: " + (correo != null ? correo : "Cliente"));

        List<Producto> productos = repository.obtenerCatalogo();
        TextView tvProd1 = findViewById(R.id.tvProducto1);
        TextView tvProd2 = findViewById(R.id.tvProducto2);
        tvResumenPedido = findViewById(R.id.tvResumenPedido);

        Producto p1 = productos.get(0);
        Producto p2 = productos.get(1);
        tvProd1.setText(p1.getNombre() + " - " + p1.getDescripcion());
        tvProd2.setText(p2.getNombre() + " - " + p2.getDescripcion());

        Button btnAgregar1 = findViewById(R.id.btnAgregar1);
        Button btnAgregar2 = findViewById(R.id.btnAgregar2);
        Button btnConfirmar = findViewById(R.id.btnConfirmarPedido);

        btnAgregar1.setOnClickListener(v -> {
            pedidoService.agregarProducto(p1);
            actualizarResumen();
        });

        btnAgregar2.setOnClickListener(v -> {
            pedidoService.agregarProducto(p2);
            actualizarResumen();
        });

        btnConfirmar.setOnClickListener(v -> {
            if (pedidoService.obtenerCantidadItems() == 0) {
                Toast.makeText(this, "Agrega al menos un producto al pedido", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Pedido enviado por $" + (int) pedidoService.calcularTotal(), Toast.LENGTH_LONG).show();
                pedidoService.limpiarPedido();
                actualizarResumen();
            }
        });
    }

    private void actualizarResumen() {
        tvResumenPedido.setText("Pedido actual: " + pedidoService.obtenerCantidadItems()
                + " productos | Total: $" + (int) pedidoService.calcularTotal());
    }
}
