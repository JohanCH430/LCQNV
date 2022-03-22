package com.example.lascosasquenovemos.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class AdminVista extends AppCompatActivity {
    Intent iTexto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_vista);

        //Inicializar elementos de la vista
        Button botonCrearTexto = findViewById(R.id.btnCrearTexto);
        Button botonCrearQuiz = findViewById(R.id.btnCrearQuiz);
        Button botonInicio = findViewById(R.id.btnInicio);

        //Funcion para las acciones que hace el botón de crear texto
        botonCrearTexto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iTexto = new Intent(AdminVista.this, TextoVista.class);
                startActivity(iTexto);
            }
        });

        //Funcion para las acciones que hace el botón de crear quiz
        botonCrearQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO Redirección a la vista CrearQuiz
            }
        });

        //Funcion para las acciones que hace el botón de ir al inicio
        botonInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Finish termina esta actividad y lleva a la actividad anterior que debería ser el mainVista
                finish();
            }
        });

    }
}