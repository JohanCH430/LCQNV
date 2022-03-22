package com.example.lascosasquenovemos.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lascosasquenovemos.bll.TextoBll;

public class InfoVista extends AppCompatActivity {
    private String idTexto;
    private TextoBll bd;
    private String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_vista);

        bd = new TextoBll();
        idTexto = "T-5";

        Button continuar = findViewById(R.id.btnContinuar);
        TextView texto = findViewById(R.id.txtViewTexto);
        //Se actualiza el textView con el valor obtenido en la lectura de la Base de Datos
        text = getTexto(idTexto);
        texto.setText(text);
        continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO redirección a la siguiente vista
            }
        });
    }

    private String getTexto(String id){
        return bd.leerTexto(id, getApplicationContext()).getTexto();
    }

}