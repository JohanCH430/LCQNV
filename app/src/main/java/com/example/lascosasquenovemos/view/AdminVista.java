package com.example.lascosasquenovemos.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lascosasquenovemos.dal.FirebaseDAL;

public class AdminVista extends AppCompatActivity {
    Intent iTexto;
    Intent iQuiz;
    Intent iTematica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_vista);

        FirebaseDAL.getInstance(getApplicationContext());

        //Inicializar elementos de la vista
        Button botonCrearTexto = findViewById(R.id.btnCrearTexto);
        Button botonCrearQuiz = findViewById(R.id.btnCrearQuiz);
        Button botonCrearTematica = findViewById(R.id.btnCrearTematica);
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
                iQuiz = new Intent(AdminVista.this, CrearQuizVista.class);
                startActivity(iQuiz);
            }
        });

        //Funcion para las acciones que hace el botón de ir a crear temática
        botonCrearTematica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iTematica = new Intent(AdminVista.this, CrearTematicaVista.class);
                startActivity(iTematica);
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