package com.example.lascosasquenovemos.sintaxisTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.lascosasquenovemos.bll.PantallaBll;
import com.example.lascosasquenovemos.bll.QuizBll;
import com.example.lascosasquenovemos.model.PantallaModelo;
import com.example.lascosasquenovemos.model.QuizModelo;
import com.example.lascosasquenovemos.model.TextoModelo;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

public class PruebasUnitariasQuizPantalla {

    //Estos test solo comprueban la comprobacion de ser nulo en el comprobar sintaxis de pantalla ya que las funcionalidades de comprobar sintaxis texto y quiz ya tienen sus propios tests

    private static PantallaBll pantallaBll;
    TextoModelo textoM;
    QuizModelo quizM;

    @Before
    public void initialize(){
        String IDTexto = "idTexto";
        String título = "titulo";
        String texto = "texto";
        String temática = "tematica";
        String pregunta = "¿Pregunta ejemplo?";
        String opcion1 = "opcion1";
        String opcion2 = "opcion2";
        String opcion3 = "opcion3";
        String opcion4 = "opcion4";
        String solucion = "opcion1";
        String textoId = "idTexto";

        textoM = new TextoModelo(IDTexto, título, texto, temática);
        quizM = new QuizModelo(pregunta, opcion1, opcion2, opcion3, opcion4, solucion, textoId);
        pantallaBll = new PantallaBll();
    }
    @Test
    public void testCorrecto(){
        PantallaModelo pM = new PantallaModelo(textoM, quizM);
        assertTrue(pantallaBll.comprobarSintaxis(pM));
    }

    @Test
    public void testQuizNulo(){
        PantallaModelo pM = new PantallaModelo(textoM, null);
        assertFalse(pantallaBll.comprobarSintaxis(pM));
    }

    @Test
    public void testTextoNulo(){
        PantallaModelo pM = new PantallaModelo(null, quizM);
        assertFalse(pantallaBll.comprobarSintaxis(pM));
    }

    @Test
    public void testTextoYQuizNulos(){
        PantallaModelo pM = new PantallaModelo(null, null);
        assertFalse(pantallaBll.comprobarSintaxis(pM));
    }
}
