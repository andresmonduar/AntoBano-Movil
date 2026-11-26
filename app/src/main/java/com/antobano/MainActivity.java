package com.antobano;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.antobano.service.PedidoService;
import com.antobano.ui.CatalogoActivity;

public class MainActivity extends AppCompatActivity {

    private final PedidoService pedidoService = new PedidoService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText etCorreo = findViewById(R.id.etCorreo);
        EditText etClave = findViewById(R.id.etClave);
        Button btnIngresar = findViewById(R.id.btnIngresar);

        btnIngresar.setOnClickListener(v -> {
            String correo = etCorreo.getText().toString();
            String clave = etClave.getText().toString();

            if (pedidoService.autenticarCliente(correo, clave)) {
                Intent intent = new Intent(MainActivity.this, CatalogoActivity.class);
                intent.putExtra("USUARIO_CORREO", correo);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Credenciales inválidas (mínimo 4 caracteres)", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
