package com.example.lascosasquenovemos.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoginUsuarioVista extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_usuario_vista);

        Button btnLogin = findViewById(R.id.BtnLoginUsuario);
        Button btnAtras = findViewById(R.id.BtnAtrasLoginUsuario);

        TextView txtError = findViewById(R.id.TextErrorLoginUsuario);
        TextView txtNombre = findViewById(R.id.Nombre);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtNombre.getText().toString().trim().equals("")){
                    Toast.makeText(getApplicationContext(), "ERROR: El nombre de usuario no puede ser nulo", Toast.LENGTH_LONG).show();
                    txtError.setText("ERROR: El nombre de usuario no puede ser nulo");
                }
                else {
                    Intent next = new Intent(LoginUsuarioVista.this, UsuarioVista.class);
                    next.putExtra("NOMBRE", txtNombre.getText().toString());
                    startActivity(next);
                }
            }
        });

        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}