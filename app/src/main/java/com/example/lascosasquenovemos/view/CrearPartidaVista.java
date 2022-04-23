package com.example.lascosasquenovemos.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lascosasquenovemos.bll.PartidaBll;
import com.example.lascosasquenovemos.bll.QuizBll;
import com.example.lascosasquenovemos.bll.TextoBll;
import com.example.lascosasquenovemos.dal.FirebaseDAL;
import com.example.lascosasquenovemos.model.Interfaces.PartidaListener;
import com.example.lascosasquenovemos.model.Interfaces.QuizListener;
import com.example.lascosasquenovemos.model.Interfaces.TextListener;
import com.example.lascosasquenovemos.model.PantallaModelo;
import com.example.lascosasquenovemos.model.PartidaModelo;
import com.example.lascosasquenovemos.model.QuizModelo;
import com.example.lascosasquenovemos.model.TextoModelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CrearPartidaVista extends AppCompatActivity implements TextListener, QuizListener, PartidaListener {
    Button btnCrear, btnReturn, btnVer, btnFinalizar;
    TextView numPantallas, numpantallas, tVcodigo;
    Intent Preview;
    int N;
    HashMap<String, TextoModelo> textos = new HashMap<>();
    PartidaModelo partida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_partida_vista);

        FirebaseDAL.getInstance(getApplicationContext());
        //inicializamos componentes de la vista
        btnFinalizar = findViewById(R.id.BtnFinalizarCrearPtll);
        btnCrear = findViewById(R.id.BtnCrearPtll);
        btnVer = findViewById(R.id.BtnVerPtll);
        btnVer.setEnabled(false);
        btnReturn = findViewById(R.id.BtnAtrasCrearPtll);
        numpantallas = findViewById(R.id.MuestraNumP);

        tVcodigo = findViewById(R.id.MuestraCodP);

        numPantallas = findViewById(R.id.InputNumPtlls);
        //Inicializamos partida vacía
        partida = new PartidaModelo("", new HashMap<Integer, PantallaModelo>());

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
                Preview.putExtra("PARTIDA", partida);
                startActivity(Preview);

            }
        });

        //btnCrear crea una partida con el número de pantallas seleccionadas
        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                N = Integer.parseInt(numPantallas.getText().toString());
                TextoBll.leerTextoTematica(CrearPartidaVista.this);
            }
        });

        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PartidaBll.crearPartida(partida, CrearPartidaVista.this);
            }
        });
    }

    @Override
    public void onTextReadSucced(TextoModelo texto) {
        if(texto != null){
            textos.put(texto.getIDTexto(), texto);
            QuizBll.leerQuizsPorTexto(texto.getIDTexto(), this);
        }


    }

    @Override
    public void onTextWriteSucced(Boolean bool) {

    }

    @Override
    public void onTextReadAllSucced(List<String> textos) {

    }

    @Override
    public void onTextosTematicasReadAllSucceed(HashMap<String, List<String>> textos) {
        int n = 0;
        for (Map.Entry<String, List<String>> e : textos.entrySet()) {
            Collections.shuffle(e.getValue());
            textos.put(e.getKey(), e.getValue());
        }
        while (n < N) {
            for (String k : textos.keySet()) {
                List l = textos.get(k);
                List<String> aux = new ArrayList<String>(l);
                if (n++>=N) break;
                TextoBll.leerTexto((String) l.get(0), this);
                //IDTextos.add(l.get(0));
                aux.remove(0);
                textos.put(k, aux);
            }
        }

    }

    @Override
    public void onQuizReadSucced(QuizModelo quiz) {
        partida.addPantalla(new PantallaModelo(textos.get(quiz.getTextId()), quiz));
        if (partida.getNumPantallas() == N) {
            btnVer.setEnabled(true);
            numpantallas.setText(Integer.toString(partida.getNumPantallas()));

        }
    }

    @Override
    public void onQuizWriteSucced(Boolean bool) {

    }

    @Override
    public void onQuizReadQuizByTextId(List<String> quizs) {
        if(quizs != null){
            Collections.shuffle(quizs);
            QuizBll.leerQuiz(quizs.get(0), this);
        }

    }


    @Override
    public void onPartidaReadSuccess(PartidaModelo pM) {

    }

    @Override
    public void onPartidaWriteSuccess(Boolean correct) {
        //TODO devolver el codigo de partida para mostrarselo al usuario
        String codigo = "CODIGOPRUEBA";
        //Una vez finalizada la partida no se puede volver a dar al boton de creacion ni de finalizacion
        btnCrear.setEnabled(false);
        btnFinalizar.setEnabled(false);
        tVcodigo.setText("Partida generada con código: " + codigo);
    }
}