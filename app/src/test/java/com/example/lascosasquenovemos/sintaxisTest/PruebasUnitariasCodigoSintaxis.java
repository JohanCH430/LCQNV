package com.example.lascosasquenovemos.sintaxisTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.lascosasquenovemos.bll.PartidaBll;
import com.example.lascosasquenovemos.model.PantallaModelo;
import com.example.lascosasquenovemos.model.PartidaModelo;
import com.example.lascosasquenovemos.model.QuizModelo;
import com.example.lascosasquenovemos.model.TextoModelo;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import java.util.HashMap;

public class PruebasUnitariasCodigoSintaxis {

    private static PartidaBll partidaBll;

    @BeforeAll
    public static void setUpTests(){
        partidaBll = new PartidaBll();
    }

    //Test Correcto
    @Test
    public void testSintaxisCodigoPartidaCorrecto(){
        String codigo ="P1";
        assertTrue(PartidaBll.comprobarSintaxisCodigoParitda(codigo));
    }

    //Test codigo null, tiene que dar false.
    @Test
    public void testSintaxisCodigoPartidaNull(){
        String codigo = null;
        assertFalse(PartidaBll.comprobarSintaxisCodigoParitda(codigo));
    }

    //Test codigo vacio, tiene que dar false.
    @Test
    public void testSintaxisCodigoPartidaVacio(){
        String codigo = "";
        assertFalse(PartidaBll.comprobarSintaxisCodigoParitda(codigo));
    }
}
