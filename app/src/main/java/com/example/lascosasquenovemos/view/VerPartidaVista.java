package com.example.lascosasquenovemos.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lascosasquenovemos.model.PantallaModelo;
import com.example.lascosasquenovemos.model.PartidaModelo;

public class VerPartidaVista extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_partida_vista);

        Button btnAnterior = findViewById(R.id.BtnAnteriorVerPtll);
        Button btnTerminar = findViewById(R.id.BtnTerminarVerPtll);
        Button btnContinuar = findViewById(R.id.BtnSiguienteVerPtll);

        TextView textInfo = findViewById(R.id.TextInfoVerPartida);
        TextView textPreg = findViewById(R.id.TextPregVerPartida);
        TextView textOpc1 = findViewById(R.id.TextOpc1VerPartida);
        TextView textOpc2 = findViewById(R.id.TextOpc2VerPartida);
        TextView textOpc3 = findViewById(R.id.TextOpc3VerPartida);
        TextView textOpc4 = findViewById(R.id.TextOpc4VerPartida);

        PartidaModelo partidaModelo = (PartidaModelo) getIntent().getSerializableExtra("PARTIDA");
        final int[] indice = {0};
        final int[] maxPant = {partidaModelo.getNumPantallas() - 1};
        PantallaModelo pantalla = (PantallaModelo) partidaModelo.getPantallasPartida().values().toArray()[indice[0]];
        textInfo.setText(pantalla.getTexto().getTexto());
        textPreg.setText(pantalla.getQuiz().getPregunta());
        textOpc1.setText(pantalla.getQuiz().getOpcionA());
        textOpc2.setText(pantalla.getQuiz().getOpcionB());
        textOpc3.setText(pantalla.getQuiz().getOpcionC());
        textOpc4.setText(pantalla.getQuiz().getOpcionD());

        btnAnterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (indice[0] > 0) {
                    indice[0]--;
                    PantallaModelo pantalla = (PantallaModelo) partidaModelo.getPantallasPartida().values().toArray()[indice[0]];
                    textInfo.setText(pantalla.getTexto().getTexto());
                    textPreg.setText(pantalla.getQuiz().getPregunta());
                    textOpc1.setText(pantalla.getQuiz().getOpcionA());
                    textOpc2.setText(pantalla.getQuiz().getOpcionB());
                    textOpc3.setText(pantalla.getQuiz().getOpcionC());
                    textOpc4.setText(pantalla.getQuiz().getOpcionD());
                }
            }
        });

        btnTerminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (indice[0] >= maxPant[0]) {
                    indice[0]++;
                    PantallaModelo pantalla = (PantallaModelo) partidaModelo.getPantallasPartida().values().toArray()[indice[0]];
                    textInfo.setText(pantalla.getTexto().getTexto());
                    textPreg.setText(pantalla.getQuiz().getPregunta());
                    textOpc1.setText(pantalla.getQuiz().getOpcionA());
                    textOpc2.setText(pantalla.getQuiz().getOpcionB());
                    textOpc3.setText(pantalla.getQuiz().getOpcionC());
                    textOpc4.setText(pantalla.getQuiz().getOpcionD());
                }
            }
        });
    }
}