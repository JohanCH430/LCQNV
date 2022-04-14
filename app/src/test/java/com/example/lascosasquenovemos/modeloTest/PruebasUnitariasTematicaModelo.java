package com.example.lascosasquenovemos.modeloTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.example.lascosasquenovemos.model.TematicaModelo;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class PruebasUnitariasTematicaModelo {
    private static TematicaModelo tematicaModelo;
    private static String titulo, desc;
    @BeforeAll
    public static void setUpTests() {
        titulo = "IDTematicaejemplo";
        desc = "desc";
        tematicaModelo = new TematicaModelo(titulo, desc);
    }

    @Test
    public void operacionNotNull(){
        assertNotNull(tematicaModelo);
    }
    @Test
    public void gettitulo(){
        assertEquals(titulo, tematicaModelo.getIDTematica());
    }
    @Test
    public void getDesc(){
        assertEquals(desc, tematicaModelo.getDescripcion());
    }
    @Test
    public void settitulo(){
        titulo = "tituloActualizada";
        tematicaModelo.setIDTematica(titulo);;
        assertEquals(titulo, tematicaModelo.getIDTematica());
    }
    @Test
    public void setDesc(){
        desc = "descActualizada";
        tematicaModelo.setDescripcion(desc);;
        assertEquals(desc, tematicaModelo.getDescripcion());
    }
}
