package com.example.lascosasquenovemos.modeloTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.example.lascosasquenovemos.model.TextoModelo;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

public class PruebasUnitariasTextoTeoriaModelo {
    private static TextoModelo textoModelo;
    private static String IDTexto, título, texto, temática;

    @BeforeAll
    public static void setUpTests() {
        IDTexto = "idTextoEjemplo";
        título = "tituloEjemplo";
        texto = "textoEjemplo";
        temática = "tematicaEjemplo";
        textoModelo = new TextoModelo(IDTexto, título, texto, temática);
    }

    @Test
    public void operacionNotNull(){
        assertNotNull(textoModelo);
    }

    /////////////////////////////////////////////////// PRUEBAS DE GETTERS ///////////////////////////////////////////////////////

    @Test
    public void getIDTexto(){
        assertEquals(IDTexto, textoModelo.getIDTexto());
    }

    @Test
    public void getTítulo() {
        assertEquals(título, textoModelo.getTítulo());
    }

    @Test
    public void getTexto() {
        assertEquals(texto, textoModelo.getTexto());
    }

    @Test
    public void getTemática(){
        assertEquals(temática, textoModelo.getTemática());
    }

    /////////////////////////////////////////////////// PRUEBAS DE SETTERS ///////////////////////////////////////////////////////

    @Test
    public void setIDTexto() {
        IDTexto = "IDTextoActualizado";
        textoModelo.setIDTexto(IDTexto);
        assertEquals(IDTexto, textoModelo.getIDTexto());
    }

    @Test
    public void setTítulo(){
        título = "tituloNuevo";
        textoModelo.setTítulo(título);
        assertEquals(título, textoModelo.getTítulo());
    }

    @Test
    public void setTexto(){
        texto = "textoNuevo";
        textoModelo.setTexto(texto);
        assertEquals(texto, textoModelo.getTexto());
    }

    @Test
    public void setTemática() {
        temática = "tematicaNueva";
        textoModelo.setTemática(temática);
        assertEquals(temática, textoModelo.getTemática());
    }
}
