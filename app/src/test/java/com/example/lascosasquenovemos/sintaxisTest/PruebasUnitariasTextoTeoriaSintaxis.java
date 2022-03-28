package com.example.lascosasquenovemos.sintaxisTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.lascosasquenovemos.bll.TextoBll;
import com.example.lascosasquenovemos.model.TextoModelo;

import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

public class PruebasUnitariasTextoTeoriaSintaxis {
    private static TextoBll textoBll;

    //Se inicializa el negocio para poder hacer los test de unidad sobre la clase correspondiente
    @BeforeAll
    public static void setUpTests(){
        textoBll = new TextoBll();
    }

    //Test de ejemplo de un Texto correcto
    @Test
    public void testSintaxisTextoCorrecto(){
        String IDTexto = "idTexto";
        String título = "titulo";
        String texto = "texto";
        String temática = "tematica";

        TextoModelo textoModelo = new TextoModelo(IDTexto, título, texto, temática);
        //assertTrue(textoBll.comprobarSintaxis(textoModelo));
        assertEquals(true, textoBll.comprobarSintaxis(textoModelo));
    }

    //Test que comprueba si se usa un texto vacía devuelve false
    @Test
    public void testSintaxisTextoVacio1(){
        String IDTexto = "";
        String título = "titulo";
        String texto = "texto";
        String temática = "tematica";

        TextoModelo textoModelo = new TextoModelo(IDTexto, título, texto, temática);
       // assertFalse(textoBll.comprobarSintaxis(textoModelo));
        assertEquals(false, textoBll.comprobarSintaxis(textoModelo));
    }

    //Test que comprueba si se usa un título vacía devuelve false
    @Test
    public void testSintaxisTextoVacio2(){
        String IDTexto = "idTexto";
        String título = "";
        String texto = "texto";
        String temática = "tematica";

        TextoModelo textoModelo = new TextoModelo(IDTexto, título, texto, temática);
        //assertFalse(textoBll.comprobarSintaxis(textoModelo));
        assertEquals(false, textoBll.comprobarSintaxis(textoModelo));
    }

    //Test que comprueba si se usa un texto vacía devuelve false
    @Test
    public void testSintaxisTextoVacio3(){
        String IDTexto = "idTexto";
        String título = "titulo";
        String texto = "";
        String temática = "tematica";

        TextoModelo textoModelo = new TextoModelo(IDTexto, título, texto, temática);
       // assertFalse(textoBll.comprobarSintaxis(textoModelo));
        assertEquals(false, textoBll.comprobarSintaxis(textoModelo));
    }

    //Test que comprueba si se usa un temática vacía devuelve false
    @Test
    public void testSintaxisTextoVacio4(){
        String IDTexto = "idTexto";
        String título = "titulo";
        String texto = "texto";
        String temática = "";

        TextoModelo textoModelo = new TextoModelo(IDTexto, título, texto, temática);
        //assertFalse(textoBll.comprobarSintaxis(textoModelo));
        assertEquals(false, textoBll.comprobarSintaxis(textoModelo));
    }

    //Test que comprueba si el texto tiene más de 2000 caracteres devuelve false
    @Test
    public void testSintaxisTextoLimite(){
        String IDTexto = "idTexto";
        String título = "titulo";
        String texto = "abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i <= 100; i++) {
            texto += texto;
        }
        String temática = "tematica";

        TextoModelo textoModelo = new TextoModelo(IDTexto, título, texto, temática);
       // assertFalse(textoBll.comprobarSintaxis(textoModelo));
        assertEquals(false, textoBll.comprobarSintaxis(textoModelo));
    }
}