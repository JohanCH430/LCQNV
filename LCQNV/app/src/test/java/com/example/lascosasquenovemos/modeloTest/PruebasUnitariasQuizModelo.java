package com.example.lascosasquenovemos.modeloTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.example.lascosasquenovemos.model.QuizModelo;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class PruebasUnitariasQuizModelo {

    private static QuizModelo quiz;
    private static String pregunta, opcionA, opcionB, opcionC, opcionD, solucion, textId;

    @BeforeAll
    public static void setUpTests(){
        pregunta = "preguntaEjemplo";
        opcionA = "opcionAEjemplo";
        opcionB = "opcionBEjemplo";
        opcionC = "opcionCEjemplo";
        opcionD = "opcionDEjemplo";
        solucion = "opcionAEjemplo";
        textId = "idEjemplo";
        quiz = new QuizModelo(pregunta, opcionA, opcionB, opcionC, opcionD, solucion, textId);
    }

    @Test
    public void operacionNotNull(){
        assertNotNull(quiz);
    }

    /////////////////////////////////////////////////// PRUEBAS DE GETTERS ///////////////////////////////////////////////////////

    @Test
    public void getPregunta(){
        assertEquals(pregunta, quiz.getPregunta());
    }

    @Test
    public void getOpcionA(){
        assertEquals(opcionA, quiz.getOpcionA());
    }

    @Test
    public void getOpcionB(){
        assertEquals(opcionB, quiz.getOpcionB());
    }

    @Test
    public void getOpcionC(){
        assertEquals(opcionC, quiz.getOpcionC());
    }

    @Test
    public void getOpcionD(){
        assertEquals(opcionD, quiz.getOpcionD());
    }

    @Test
    public void getSolucion(){
        assertEquals(solucion, quiz.getSolucion());
    }

    @Test
    public void getTextId(){
        assertEquals(textId, quiz.getTextId());
    }

    /////////////////////////////////////////////////// PRUEBAS DE SETTERS ///////////////////////////////////////////////////////

    @Test
    public void setPregunta(){
        pregunta = "preguntaActualizada";
        quiz.setPregunta(pregunta);
        assertEquals(pregunta, quiz.getPregunta());
    }

    @Test
    public void setOpcionA(){
        opcionA = "opANueva";
        quiz.setOpcionA(opcionA);
        assertEquals(opcionA, quiz.getOpcionA());
    }

    @Test
    public void setOpcionB(){
        opcionB = "opBNueva";
        quiz.setOpcionB(opcionB);
        assertEquals(opcionB, quiz.getOpcionB());
    }

    @Test
    public void setOpcionC(){
        opcionC = "opCNueva";
        quiz.setOpcionC(opcionC);
        assertEquals(opcionC, quiz.getOpcionC());
    }

    @Test
    public void setOpcionD(){
        opcionD = "opDNueva";
        quiz.setOpcionD(opcionD);
        assertEquals(opcionD, quiz.getOpcionD());
    }

    @Test
    public void setSolucion(){
        solucion = "solucionActualizada";
        quiz.setSolucion(solucion);
        assertEquals(solucion, quiz.getSolucion());
    }

    @Test
    public void setTextId(){
        textId = "idActualizado";
        quiz.setTextId(textId);
        assertEquals(textId, quiz.getTextId());
    }
}
