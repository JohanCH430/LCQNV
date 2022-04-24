package com.example.lascosasquenovemos.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lascosasquenovemos.bll.PartidaBll;
import com.example.lascosasquenovemos.model.Interfaces.PartidaListener;
import com.example.lascosasquenovemos.model.PartidaModelo;

public class EntrarEnPartidaVista extends AppCompatActivity implements PartidaListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrar_en_partida_vista);

        Button bContinuar = findViewById(R.id.botonJugar);
        EditText editText = findViewById(R.id.EditTextCodigo);

        bContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String codigo = editText.getText().toString();
                if (codigo.equals(null) || codigo.trim().isEmpty()){
                    Toast.makeText(getApplicationContext(), "El código introducido es vacío", Toast.LENGTH_LONG).show();
                }

                else PartidaBll.leerPartida(codigo, EntrarEnPartidaVista.this);
            }
        });
    }

    @Override
    public void onPartidaReadSuccess(PartidaModelo pM) {
        if (pM != null){
            //TODO añadir parametros al intent
            Intent iInfoVista = new Intent(EntrarEnPartidaVista.this, InfoVista.class);
            startActivity(iInfoVista);
        }
        else {
            Toast.makeText(getApplicationContext(), "Error al leer la partida, no se ha encontrado ninguna partida con el código introducido", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onPartidaWriteSuccess(String codigo, Boolean correct) {

    }
}