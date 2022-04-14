package com.example.lascosasquenovemos.sintaxisTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.lascosasquenovemos.bll.TematicaBll;
import com.example.lascosasquenovemos.model.TematicaModelo;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

public class PruebasUnitariasTematicaSintaxis {

    private static TematicaBll tematicaBll;
    //Se inicializa el negocio para poder hacer los test de unidad sobre la clase correspondiente
    @BeforeAll
    public static void setUpTests(){
        tematicaBll = new TematicaBll();
    }

    //Test del titulo no nulo y la descripcion nula
    @Test
    public void testSintaxisTematicaCorrecto1() {
        String titulo = "titulo";
        String desc ="";
        TematicaModelo tema = new TematicaModelo(titulo, desc);
        assertTrue(tematicaBll.comprobarSintaxis(tema));
    }

    //Test del titulo no nulo y la descripcion no nula
    @Test
    public void testSintaxisTematicaCorrecto2() {
        String titulo = "titulo";
        String desc ="desc";
        TematicaModelo tema = new TematicaModelo(titulo, desc);
        assertTrue(tematicaBll.comprobarSintaxis(tema));
    }

    //Test del titulo no nulo y la descripcion no nula y titulo menos de 50 car
    @Test
    public void testSintaxisTematicaCorrecto3() {
        String titulo = "titulo";
        String desc ="desc";
        TematicaModelo tema = new TematicaModelo(titulo, desc);
        assertTrue(tematicaBll.comprobarSintaxis(tema));
    }

    //Test del titulo no nulo y la descripcion no nula y titulo mas de 50 car
    @Test
    public void testSintaxisTematicaFalse1() {
        String titulo = "Esto es un titulo de caracteres mas de 50 caracteres tengo que rellenar hasta 50 caracteres";
        String desc ="desc";
        TematicaModelo tema = new TematicaModelo(titulo, desc);
        assertFalse(tematicaBll.comprobarSintaxis(tema));
    }

    //Test del titulo no nulo y la descripcion no nula y titulo mas de 50 car y desc mas 500
    @Test
    public void testSintaxisTematicaFalse2() {
        String titulo = "Esto es un titulo de caracteres mas de 50 caracteres tengo que rellenar hasta 50 caracteres";
        String desc ="Esto es un desc de caracteres mas de 500 caracteres tengo que rellenar hasta 500 caracteres\n" +
                "Descripción  Descripción  Descripción  Descripción  Descripción  Descripción  Descripción  Descripción  Descripción Descripción Descripción Descripción Descripción Descripción Descripción Descripción Descripción Descripción Descripción Descripción Descripción Descripción Descripción Descripción Descripción Descripción Descripción Descripción Descripción Descripción Descripción Descripción Descripción Descripción Descripción Descripción Descripción Descripción Descripción Descripción\n";
        TematicaModelo tema = new TematicaModelo(titulo, desc);
        assertFalse(tematicaBll.comprobarSintaxis(tema));
    }

    //Test del titulo no nulo y la descripcion no nula y titulo menos de 50 car y desc mas 500
    @Test
    public void testSintaxisTematicaFalse3() {
        String titulo = "titulo";
        String desc ="Esto es un desc de caracteres mas de 500 caracteres tengo que rellenar hasta 500 caracteres\n" +
                "Descripción  Descripción  Descripción  Descripción  Descripción  Descripción  Descripción  Descripción  Descripción Descripción Descripción Descripción Descripción Descripción Descripción Descripción Descripción Descripción Descripción Descripción Descripción Descripción Descripción Descripción Descripción Descripción Descripción Descripción Descripción Descripción Descripción Descripción Descripción Descripción Descripción Descripción Descripción Descripción Descripción Descripción\n";
        TematicaModelo tema = new TematicaModelo(titulo, desc);
        assertFalse(tematicaBll.comprobarSintaxis(tema));
    }

    //Test del titulo nulo y la descripcion no nula
    @Test
    public void testSintaxisTematicaFalse4() {
        String titulo = "";
        String desc = "desc";
        TematicaModelo tema = new TematicaModelo(titulo, desc);
        assertFalse(tematicaBll.comprobarSintaxis(tema));
    }


    //Test del titulo nulo y la descripcion nula
    @Test
    public void testSintaxisTematicaFalse5() {
        String titulo = "";
        String desc = "";
        TematicaModelo tema = new TematicaModelo(titulo, desc);
        assertFalse(tematicaBll.comprobarSintaxis(tema));
    }

}
