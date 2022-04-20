package com.example.lascosasquenovemos.modeloTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.example.lascosasquenovemos.model.PantallaModelo;
import com.example.lascosasquenovemos.model.PartidaModelo;
import com.example.lascosasquenovemos.model.QuizModelo;
import com.example.lascosasquenovemos.model.TextoModelo;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class PruebasUnitariasPartidaModelo {
    private static PartidaModelo partida;
    private static String idPartida;
    private static HashMap<Integer, PantallaModelo> pantallasPartida;
    private static TextoModelo texto;
    private static QuizModelo quiz;

    @BeforeAll
    public static void setUpTests(){
        idPartida = "idPartida";
        pantallasPartida = new HashMap<>();
        texto = new TextoModelo("idtexto","titulo", "texto","tematica");
        quiz = new QuizModelo("pregunta","opcionA","opcionB", "opcionC","opcionC","opcionA","idtexto");
        pantallasPartida.put(0, new PantallaModelo(texto, quiz));
        partida= new PartidaModelo(idPartida, pantallasPartida);
    }

    @Test
    public void operacionNotNull(){
        assertNotNull(partida);
    }

    @Test
    public void getidPartida(){
        assertEquals(idPartida, partida.getIdPartida());
    }

    @Test
    public void getPantallaPartida(){
        assertEquals(pantallasPartida, partida.getPantallasPartida());
    }

    @Test
    public void setidPartida(){
        idPartida = "idPartidaNuevo";
        partida.setIdPartida(idPartida);
        assertEquals(idPartida, partida.getIdPartida());
    }


    @Test
    public void setPantallaPartida(){
        pantallasPartida = new HashMap<>();
        texto = new TextoModelo("idtextoNuevo","tituloNuevo", "textoNuevo","tematicaNuevo");
        quiz = new QuizModelo("preguntaNuevo","opcionANuevo","opcionBNuevo", "opcionCNuevo","opcionCNuevo","opcionANuevo","idtextoNuevo");
        pantallasPartida.put(2, new PantallaModelo(texto,quiz));
        partida.setPantallasPartida(pantallasPartida);
        assertEquals(pantallasPartida, partida.getPantallasPartida());
    }

    @Test
    public void addPantalla(){
        TextoModelo textoNuevo = new TextoModelo("idtextoNuevo","tituloNuevo", "textoNuevo","tematicaNuevo");
        QuizModelo quizNuevo = new QuizModelo("preguntaNuevo","opcionANuevo","opcionBNuevo", "opcionCNuevo","opcionCNuevo","opcionANuevo","idtextoNuevo");
        PantallaModelo pantallasPartidaNuevo = new PantallaModelo(textoNuevo, quizNuevo);
        partida.addPantalla(pantallasPartidaNuevo);
        pantallasPartida.put(pantallasPartida.size(),pantallasPartidaNuevo);
        assertEquals(pantallasPartida, partida.getPantallasPartida());
    }

}
