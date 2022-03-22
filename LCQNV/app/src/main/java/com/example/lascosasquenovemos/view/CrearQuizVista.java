package com.example.lascosasquenovemos.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lascosasquenovemos.bll.QuizBll;
import com.example.lascosasquenovemos.model.Interfaces.QuizListener;
import com.example.lascosasquenovemos.model.QuizModelo;

public class CrearQuizVista extends AppCompatActivity implements QuizListener {

    Boolean escrituraCorrecta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_quiz_vista);

        QuizBll.leerQuiz("Q-1", this);

        QuizModelo quizM = new QuizModelo("Johaasn", "Jsd", "Ofsd", "fH", "Asdf", "sdffsdN");
        QuizBll.crearQuiz(quizM, this);

    }


    //Metodos del Listener
    @Override
    public void onQuizReadSucced(QuizModelo quiz) {
        QuizModelo prueba = quiz;
    }

    @Override
    public void onQuizWriteSucced(Boolean bool) {
        escrituraCorrecta = bool;
    }

}