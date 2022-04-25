package com.example.lascosasquenovemos.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.lascosasquenovemos.dal.FirebaseDAL;

public class UsuarioVista extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_vista);
        FirebaseDAL.getInstance(getApplicationContext());

        Button btnCrear = findViewById(R.id.btnCrearPartida);
        Button btnJugar = findViewById(R.id.btnJugarPartida);
        Button btnAtras = findViewById(R.id.btnAtrasUsuario);
        Button btnInicio = findViewById(R.id.btnInicioUsuario);

        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(UsuarioVista.this, CrearPartidaVista.class);
                startActivity(i);
            }
        });

        btnJugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent (UsuarioVista.this, EntrarEnPartidaVista.class);
                startActivity(i);
            }
        });

        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            //Se vuelve al anteriro activity
            public void onClick(View view) {
                finish();
            }
        });

        btnInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(UsuarioVista.this, MainVista.class);
                //Se añaden estas flags para que se limpie la pila de pantallas y se vuelta a la pantalla principal sin problemas
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });
    }

}