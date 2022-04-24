package com.example.lascosasquenovemos.view;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.example.lascosasquenovemos.bll.QuizBll;
import com.example.lascosasquenovemos.dal.FirebaseDAL;
import com.example.lascosasquenovemos.model.Interfaces.QuizListener;
import com.example.lascosasquenovemos.model.PantallaModelo;
import com.example.lascosasquenovemos.model.PartidaModelo;
import com.example.lascosasquenovemos.model.QuizModelo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VerQuizVista extends AppCompatActivity{

    TextView tVPregunta, comprobacion;
    RadioButton rd1, rd2, rd3, rd4, rdSeleccionado;
    RadioGroup rG;
    Button btnComprobar;
    Intent intentActual, iComprobacion;

    String solucion = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_quiz_vista);
        
        FirebaseDAL.getInstance(getApplicationContext());
        //Obtengo el extra que me han enviado, que es el Id del Quiz que tenog que buscar.
        intentActual = getIntent();
        String idQuiz = intentActual.getStringExtra("idQuiz");


        //Inicializo vistas.
        tVPregunta = findViewById(R.id.tVPregunta);
        rG = findViewById(R.id.QuizOpcionSeleccionada);
        rd1 = findViewById(R.id.ROpc1);
        rd2 = findViewById(R.id.ROpc2);
        rd3 = findViewById(R.id.ROpc3);
        rd4 = findViewById(R.id.ROpc4);
        btnComprobar = findViewById(R.id.btnComprobar);
        iComprobacion=new Intent(VerQuizVista.this, ComprobarResultadoVista.class);

        //Se obtiene la partida
        PartidaModelo partidaModelo = (PartidaModelo) getIntent().getSerializableExtra("PARTIDA");
        //TODO gestionar indices para que se pueda jugar la partida completa
        int indice = getIntent().getIntExtra("INDICE", 0);

        //Se obtiene la pantalla que corresponde a esto
        PantallaModelo pantalla = (PantallaModelo) partidaModelo.getPantallasPartida().values().toArray()[indice];

        QuizModelo quiz = pantalla.getQuiz();

        //Hago que la vista tenga los datos del Quiza dado.
        tVPregunta.setText(quiz.getPregunta());

        //Creo un array y desordeno las opciones para colocarlas aleatoriamente.
        ArrayList<String> arrayOpciones = new ArrayList<String>();

        arrayOpciones.add(quiz.getOpcionA());
        arrayOpciones.add(quiz.getOpcionB());
        arrayOpciones.add(quiz.getOpcionC());
        arrayOpciones.add(quiz.getOpcionD());

        Collections.shuffle(arrayOpciones);

        rd1.setText(arrayOpciones.get(0));
        rd2.setText(arrayOpciones.get(1));
        rd3.setText(arrayOpciones.get(2));
        rd4.setText(arrayOpciones.get(3));

        //Me guardo la solución en la clase, para poder comprobar.
        solucion = quiz.getSolucion();


        //TODO Borrar
        comprobacion = findViewById(R.id.TextoComprobacionPorAhora);

        btnComprobar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(solucion != null){

                    String opcionEscogida = null;
                    int id = rG.getCheckedRadioButtonId();

                    //Según el ID que obtenga del RB seleccionado, cojo la opción seleccionada.
                    switch (id){
                        case R.id.ROpc1: opcionEscogida = (String) rd1.getText(); break;
                        case R.id.ROpc2: opcionEscogida = (String) rd2.getText(); break;
                        case R.id.ROpc3: opcionEscogida = (String) rd3.getText(); break;
                        case R.id.ROpc4: opcionEscogida = (String) rd4.getText(); break;
                        default: opcionEscogida = ""; break;
                    }
                    if (opcionEscogida == "") {
                        comprobacion.setText("Se debe seleccionar una respuesta");
                    }
                    else if(QuizBll.respuestaCorrecta(opcionEscogida, solucion)) {
                        iComprobacion.putExtra("Comprobacion", "correcto");
                        iComprobacion.putExtra("PARTIDA", partidaModelo);
                        iComprobacion.putExtra("INDICE", indice);
                        startActivity(iComprobacion);

                    }
                    else {
                        iComprobacion.putExtra("Comprobacion", "incorrecto");
                        iComprobacion.putExtra("PARTIDA", partidaModelo);
                        iComprobacion.putExtra("INDICE", indice);
                        startActivity(iComprobacion);
                    }
                }
            }
        });
    }

    /*@Override
    public void onQuizReadSucced(QuizModelo quiz) { //Cuando el Listener escuche la info, cambio las vistas.

        //Hago que la vista tenga los datos del Quiza dado.
        tVPregunta.setText(quiz.getPregunta());

        //Creo un array y desordeno las opciones para colocarlas aleatoriamente.
        ArrayList<String> arrayOpciones = new ArrayList<String>();

        arrayOpciones.add(quiz.getOpcionA());
        arrayOpciones.add(quiz.getOpcionB());
        arrayOpciones.add(quiz.getOpcionC());
        arrayOpciones.add(quiz.getOpcionD());

        Collections.shuffle(arrayOpciones);

        rd1.setText(arrayOpciones.get(0));
        rd2.setText(arrayOpciones.get(1));
        rd3.setText(arrayOpciones.get(2));
        rd4.setText(arrayOpciones.get(3));

        //Me guardo la solución en la clase, para poder comprobar.
        solucion = quiz.getSolucion();
    }

    @Override
    public void onQuizWriteSucced(Boolean bool) {

    }

    @Override
    public void onQuizReadQuizByTextId(List<String> quizs) {

    }*/
}