package com.example.lascosasquenovemos.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.lascosasquenovemos.model.QuizModelo;

public class CrearQuizVista extends AppCompatActivity{
    TextView txtPreg, txtOp1, txtOp2, txtOp3, txtOp4, teoria, Error;
    RadioButton rd1, rd2, rd3, rd4;
    Button btnInicio, btnCrear;
    Intent iInicio, iCrear;
    //TODO QuizBll


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_quiz_vista);

        txtPreg = findViewById(R.id.InpPreguntaQuiz);
        txtOp1 = findViewById(R.id.InpOpc1Quiz);
        txtOp2 = findViewById(R.id.InpOpc2Quiz);
        txtOp3 = findViewById(R.id.InpOpc3Quiz);
        txtOp4 = findViewById(R.id.InpOpc4Quiz);
        teoria = findViewById(R.id.InpTextoQuiz);
        rd1 = findViewById(R.id.Radio_opc1);
        rd2 = findViewById(R.id.Radio_opc2);
        rd3 = findViewById(R.id.Radio_opc3);
        rd4 = findViewById(R.id.Radio_opc4);
        btnInicio = findViewById(R.id.buttonInicioQuiz);
        btnCrear = findViewById(R.id.buttonCrearQuiz);
        Error = findViewById(R.id.ErrorQuiz);

        btnInicio.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                startActivity(iInicio);
            }
        });

        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO Habrá que cambiarlo (idtexto)
                //QuizModelo quiz = new QuizModelo("0","0",);
                //TODO Bll.crearQuiz()

            }
        });
    }

}