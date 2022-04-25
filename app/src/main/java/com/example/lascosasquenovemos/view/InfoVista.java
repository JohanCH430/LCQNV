package com.example.lascosasquenovemos.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.lascosasquenovemos.bll.TextoBll;
import com.example.lascosasquenovemos.dal.FirebaseDAL;
import com.example.lascosasquenovemos.model.Interfaces.TextListener;
import com.example.lascosasquenovemos.model.PantallaModelo;
import com.example.lascosasquenovemos.model.PartidaModelo;
import com.example.lascosasquenovemos.model.TextoModelo;

import java.util.HashMap;
import java.util.List;

public class InfoVista extends AppCompatActivity{
    private PartidaModelo partidaModelo;
    private int indice;
    private PantallaModelo pantalla;
    TextView texto;
    Intent next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_vista);

        FirebaseDAL.getInstance(getApplicationContext());
        next = new Intent(InfoVista.this, VerQuizVista.class);
        //bd = new TextoBll();

        //Se obtiene la partida
        partidaModelo = (PartidaModelo) getIntent().getSerializableExtra("PARTIDA");
        //TODO gestionar indices para que se pueda jugar la partida completa
        //indice = getIntent().getIntExtra("INDICE", 0);

        //Se obtiene la pantalla que corresponde a esto
        pantalla = (PantallaModelo) partidaModelo.getPantallasPartida().values().toArray()[indice];

        Button continuar = findViewById(R.id.btnContinuar);
        texto = findViewById(R.id.txtViewTexto);

        texto.setText(pantalla.getTexto().getTexto());
        continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                next.putExtra("PARTIDA", partidaModelo);
                next.putExtra("INDICE", indice);
                startActivity(next);
            }
        });
    }


    /*@Override
    public void onTextReadSucced(TextoModelo texto) {
        this.texto.setText(texto.getTexto());
    }

    @Override
    public void onTextWriteSucced(Boolean bool) {
        //No se implementa porque desde esta vista solo se lee
    }

    @Override
    public void onTextReadAllSucced(List<String> textos) {

    }

    @Override
    public void onTextosTematicasReadAllSucceed(HashMap<String, List<String>> textos) {

    }*/
}