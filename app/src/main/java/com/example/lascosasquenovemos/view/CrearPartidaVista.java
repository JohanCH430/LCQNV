package com.example.lascosasquenovemos.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lascosasquenovemos.dal.FirebaseDAL;

public class CrearPartidaVista extends AppCompatActivity {
    Button btnCrear, btnReturn, btnVer;
    TextView numPantallas;
    Intent Preview; //TODO Pantalla Preview
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_partida_vista);

        FirebaseDAL.getInstance(getApplicationContext());
        //inicializamos componentes de la vista
        btnCrear = findViewById(R.id.BtnCrearPtll);
        btnVer = findViewById(R.id.BtnVerPtll);
        btnVer.setEnabled(false);
        btnReturn = findViewById(R.id.BtnAtrasCrearPtll);

        numPantallas = findViewById(R.id.InputNumPtlls);

        //btnReturn vuelve a mainUsuario
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //btnVer lleva a la pantalla de verPartida
        btnVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Preview = new Intent(CrearPartidaVista.this, VerPartidaVista.class);
                startActivity(Preview);

            }
        });

        //btnCrear crea una partida con el número de pantallas seleccionadas
        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int n = Integer.parseInt(numPantallas.getText().toString());
                //TODO no acabo de tener claro cómo funciona esto xD
            }
        });


    }
}