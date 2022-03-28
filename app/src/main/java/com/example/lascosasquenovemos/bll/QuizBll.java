package com.example.lascosasquenovemos.bll;

import com.example.lascosasquenovemos.dal.QuizDAL;
import com.example.lascosasquenovemos.model.Interfaces.QuizListener;
import com.example.lascosasquenovemos.model.QuizModelo;

public class QuizBll {

    public static void crearQuiz(QuizModelo quiz, QuizListener qL) {
        QuizDAL.crearQuiz(quiz, qL);
    }

    public static void leerQuiz(String id, QuizListener qL) {
        QuizDAL.leerQuiz(id, qL);
    }

    public static boolean comprobarSintaxis(QuizModelo modelo) {
        if(modelo.getPregunta().equals("") || modelo.getOpcionA().length() > 50) {
            return false;
        }
        if (modelo.getOpcionA().equals("") || modelo.getOpcionA().length() > 50) {
            return false;
        }
        if (modelo.getOpcionB().equals("")  || modelo.getOpcionB().length() > 50) {
            return false;
        }
        if (modelo.getOpcionC().equals("") || modelo.getOpcionC().length() > 50) {
            return false;
        }
        if (modelo.getOpcionD().equals("") || modelo.getOpcionD().length() > 50) {
            return false;
        }
        if (modelo.getSolucion().equals("") || modelo.getSolucion().length() > 50) {
            return false;
        }
        if (modelo.getTextId().isEmpty()) {
            return false;
        }
        if(modelo.getPregunta().trim().isEmpty() || modelo.getOpcionA().trim().isEmpty() || modelo.getOpcionB().trim().isEmpty() || modelo.getOpcionC().trim().isEmpty() ||
        modelo.getOpcionD().trim().isEmpty())
            return false;
        if (!modelo.getSolucion().equals(modelo.getOpcionA()) && !modelo.getSolucion().equals(modelo.getOpcionB()) && !modelo.getSolucion().equals(modelo.getOpcionC()) && !modelo.getSolucion().equals(modelo.getOpcionD())){
            return false;
        }
        return true;
    }
    public static boolean respuestaCorrecta(String opcionSeleccionada, String opcionCorrecta){
        return opcionSeleccionada.equals(opcionCorrecta);
    }

}
