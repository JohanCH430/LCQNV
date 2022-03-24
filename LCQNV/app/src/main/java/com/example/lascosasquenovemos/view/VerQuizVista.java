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
import com.example.lascosasquenovemos.model.Interfaces.QuizListener;
import com.example.lascosasquenovemos.model.QuizModelo;

import java.util.ArrayList;
import java.util.Collections;

public class VerQuizVista extends AppCompatActivity implements QuizListener {

    TextView tVPregunta;
    RadioButton rd1, rd2, rd3, rd4, rdSeleccionado;
    RadioGroup rG;
    Button btnComprobar;
    Intent intentActual, iComprobacion;

    String solucion = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_quiz_vista);

        //Obtengo el extra que me han enviado, que es el Id del Quiz que tenog que buscar.
        intentActual = getIntent();
        String idQuiz = intentActual.getStringExtra("idQuiz");
        QuizBll.leerQuiz(idQuiz, this);

        //Inicializo vistas.
        tVPregunta = findViewById(R.id.tVPregunta);
        rG = findViewById(R.id.QuizOpcionSeleccionada);
        rd1 = findViewById(R.id.ROpc1);
        rd2 = findViewById(R.id.ROpc2);
        rd3 = findViewById(R.id.ROpc3);
        rd4 = findViewById(R.id.ROpc4);
        btnComprobar = findViewById(R.id.btnComprobar);

        btnComprobar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(solucion != null){

                    //TODO no puedo terminarlo aún
                    //Le añado al Intent el resultado para que la activity pueda cambiar según sea o no correcto.
                   /* iComprobacion=new Intent(this, .class);
                    iComprobacion.putExtra("resul", resultado);
                    startActivity(iComprobacion);*/
                }
            }
        });
    }

    @Override
    public void onQuizReadSucced(QuizModelo quiz) { //Cuando el Listener escuche la info, cambio las vistas.

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
}