package com.example.lascosasquenovemos.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lascosasquenovemos.bll.PartidaBll;
import com.example.lascosasquenovemos.dal.FirebaseDAL;
import com.example.lascosasquenovemos.model.Interfaces.PartidaListener;
import com.example.lascosasquenovemos.model.PartidaModelo;

public class EntrarEnPartidaVista extends AppCompatActivity implements PartidaListener {

    TextView fedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrar_en_partida_vista);

        FirebaseDAL.getInstance(getApplicationContext());

        Button bContinuar = findViewById(R.id.botonJugar);
        Button bAtras = findViewById(R.id.btnAtrasEntrarEnPartida);
        EditText editText = findViewById(R.id.EditTextCodigo);
        fedback = findViewById(R.id.TextViewFedBackIntroducirCodigo);

        bContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String codigo = editText.getText().toString();
                if (!PartidaBll.comprobarSintaxisCodigoParitda(codigo)){
                    fedback.setText("Error, el código introducido es vacío");
                }
                else PartidaBll.leerPartida(codigo, EntrarEnPartidaVista.this);
            }
        });

        bAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void onPartidaReadSuccess(PartidaModelo pM) {
        if (pM != null){
            Intent iInfoVista = new Intent(EntrarEnPartidaVista.this, InfoVista.class);
            iInfoVista.putExtra("PARTIDA", pM);
            iInfoVista.putExtra("INDICE", 0);
            startActivity(iInfoVista);
        }
        else {
            fedback.setText("Error al leer la partida, el código introducido no existe");
        }
    }

    @Override
    public void onPartidaWriteSuccess(String codigo, Boolean correct) {

    }
}