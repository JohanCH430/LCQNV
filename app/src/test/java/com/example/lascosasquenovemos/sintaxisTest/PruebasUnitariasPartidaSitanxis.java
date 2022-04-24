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

public class PruebasUnitariasPartidaSitanxis {

    private static PartidaBll partidaBll;

    @BeforeAll
    public static void setUpTests(){
        partidaBll = new PartidaBll();
    }

    //Test Correcto
    @Test
    public void testSintaxisPartidaCorrecto1(){

        String idPartida ="idPartida";
        HashMap<Integer, PantallaModelo>pantallasPartida = new HashMap<>();
        TextoModelo texto = new TextoModelo("idtextoNuevo","tituloNuevo", "textoNuevo","tematicaNuevo");
        QuizModelo quiz = new QuizModelo("preguntaNuevo","opcionANuevo","opcionBNuevo", "opcionCNuevo","opcionCNuevo","opcionANuevo","idtextoNuevo");
        pantallasPartida.put(0, new PantallaModelo(texto,quiz));
        PartidaModelo pm = new PartidaModelo(idPartida, pantallasPartida);
        assertTrue(partidaBll.comprobarSintaxisPartida(pm));
    }

    //Test idPartida null
    @Test
    public void testSintaxisPartidaFalso(){

        String idPartida ="";
        HashMap<Integer, PantallaModelo>pantallasPartida = new HashMap<>();
        TextoModelo texto = new TextoModelo("idtextoNuevo","tituloNuevo", "textoNuevo","tematicaNuevo");
        QuizModelo quiz = new QuizModelo("preguntaNuevo","opcionANuevo","opcionBNuevo", "opcionCNuevo","opcionCNuevo","opcionANuevo","idtextoNuevo");
        pantallasPartida.put(0, new PantallaModelo(texto,quiz));
        PartidaModelo pm = new PartidaModelo(idPartida, pantallasPartida);
        assertFalse(partidaBll.comprobarSintaxisPartida(pm));
    }

    //Test HaspMap null
    @Test
    public void testSintaxisPartidaFalso2(){

        String idPartida ="idPartida";
        HashMap<Integer, PantallaModelo>pantallasPartida = new HashMap<>();
        PartidaModelo pm = new PartidaModelo(idPartida, pantallasPartida);
        assertFalse(partidaBll.comprobarSintaxisPartida(pm));
    }

    //Test HaspMap null idPartida null
    @Test
    public void testSintaxisPartidaFalso3(){

        String idPartida ="";
        HashMap<Integer, PantallaModelo>pantallasPartida = new HashMap<>();
        PartidaModelo pm = new PartidaModelo(idPartida, pantallasPartida);
        assertFalse(partidaBll.comprobarSintaxisPartida(pm));
    }


}
