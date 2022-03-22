package com.example.lascosasquenovemos.bll;

import com.example.lascosasquenovemos.dal.QuizDAL;
import com.example.lascosasquenovemos.dal.TextoDAL;
import com.example.lascosasquenovemos.model.FirebaseListener;
import com.example.lascosasquenovemos.model.Interfaces.QuizListener;
import com.example.lascosasquenovemos.model.QuizModelo;

public class QuizBll {

    public static void crearQuiz(QuizModelo quiz, QuizListener qL) {
        QuizDAL.crearQuiz(quiz, qL);
    }

    public static void leerQuiz(String id, QuizListener qL) {
        QuizDAL.leerQuiz(id, qL);
    }
}
