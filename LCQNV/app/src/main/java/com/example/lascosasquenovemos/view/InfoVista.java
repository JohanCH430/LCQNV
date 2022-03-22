package com.example.lascosasquenovemos.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class InfoVista extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_vista);

        Button continuar = findViewById(R.id.btnContinuar);
        TextView texto = findViewById(R.id.txtViewTexto);
        //Se actualiza el textView con el valor obtenido en la lectura de la Base de Datos
        texto.setText(getTexto());

        continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO redirección a la siguiente vista
            }
        });
    }

    private String getTexto(){
        //TODO llamar a negocio para que devuelva la variable leida de la base de datos
        return "a";
    }

}