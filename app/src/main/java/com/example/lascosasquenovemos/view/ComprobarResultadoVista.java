package com.example.lascosasquenovemos.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lascosasquenovemos.dal.FirebaseDAL;
import com.example.lascosasquenovemos.model.PartidaModelo;

public class ComprobarResultadoVista extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comprobar_resultado_vista);

        FirebaseDAL.getInstance(getApplicationContext());
        Intent intentActual = getIntent();
        String respuesta = intentActual.getStringExtra("Comprobacion");

        //Se obtiene la partida
        PartidaModelo partidaModelo = (PartidaModelo) getIntent().getSerializableExtra("PARTIDA");
        //TODO gestionar indices para que se pueda jugar la partida completa
        int indice = getIntent().getIntExtra("INDICE", 0);
        indice++;

        TextView comprobacion = findViewById(R.id.tVComprobacion);
        Button btnReintento = findViewById(R.id.btnReintentar);
        Button btnTerminar = findViewById(R.id.btnTerminar);
        Button btnContinuar = findViewById(R.id.btnSiguiente);

        //Los dos botones se inicializan para que no se pueda interactuar con ellos y, dependiendo de la respuesta, se podria interactuar con uno o con otro
        btnReintento.setEnabled(false);
        btnTerminar.setEnabled(false);

        if (respuesta.equals("correcto")) {
            comprobacion.setText("¡CORRECTO!");
            btnTerminar.setEnabled(true);
        }

        else {
            comprobacion.setText("INCORRECTO");
            //No se si se debería bloquear el terminar hasta que tenga la respuesta correcta pues puede generar frustración al usuario
            btnTerminar.setEnabled(true);
            btnReintento.setEnabled(true);
        }

        btnTerminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(ComprobarResultadoVista.this, MainVista.class);
                //Se añaden estas flags para que se limpie la pila de pantallas y se vuelta a la pantalla principal sin problemas
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });

        btnReintento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        int finalIndice = indice;
        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(ComprobarResultadoVista.this, InfoVista.class);
                //Se añaden estas flags para que se limpie la pila de pantallas y se vuelta a la pantalla principal sin problemas
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.putExtra("PARTIDA", partidaModelo);
                i.putExtra("INDICE", finalIndice);
                startActivity(i);
            }
        });
    }
}