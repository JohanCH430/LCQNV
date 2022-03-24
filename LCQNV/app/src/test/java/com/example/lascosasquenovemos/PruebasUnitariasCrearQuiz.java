package com.example.lascosasquenovemos;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.example.lascosasquenovemos.bll.QuizBll;
import com.example.lascosasquenovemos.model.QuizModelo;

import org.junit.Before;
import org.junit.Test;

// public QuizModelo(String pregunta, String opcionA, String opcionB, String opcionC, String opcionD, String solucion, String textId)

public class PruebasUnitariasCrearQuiz {

    QuizBll quizBll;

    //Se inicializa el negocio para poder hacer los test de unidad sobre la clase correspondiente
    @Before
    public void setUpTests(){
        quizBll = new QuizBll();
    }

    //Test de ejemplo de un Quiz correcto con la primera opción correcta
    @Test
    public void testSintaxisQuizCorrecto1(){

        String pregunta = "¿Pregunta ejemplo?";
        String opcion1 = "opcion1";
        String opcion2 = "opcion2";
        String opcion3 = "opcion3";
        String opcion4 = "opcion4";
        String solucion = "opcion1";
        String textoId = "id";

        QuizModelo quiz = new QuizModelo(pregunta, opcion1, opcion2, opcion3, opcion4, solucion, textoId);
        assertTrue(quizBll.comprobarSintaxis(quiz));
    }

    //Test de ejemplo de un Quiz correcto con la segunda opción correcta
    @Test
    public void testSintaxisQuizCorrecto2(){

        String pregunta = "¿Pregunta ejemplo?";
        String opcion1 = "opcion1";
        String opcion2 = "opcion2";
        String opcion3 = "opcion3";
        String opcion4 = "opcion4";
        String solucion = "opcion2";
        String textoId = "id";

        QuizModelo quiz = new QuizModelo(pregunta, opcion1, opcion2, opcion3, opcion4, solucion, textoId);
        assertTrue(quizBll.comprobarSintaxis(quiz));
    }

    //Test de ejemplo de un Quiz correcto con la tercera opción correcta
    @Test
    public void testSintaxisQuizCorrecto3(){

        String pregunta = "¿Pregunta ejemplo?";
        String opcion1 = "opcion1";
        String opcion2 = "opcion2";
        String opcion3 = "opcion3";
        String opcion4 = "opcion4";
        String solucion = "opcion3";
        String textoId = "id";

        QuizModelo quiz = new QuizModelo(pregunta, opcion1, opcion2, opcion3, opcion4, solucion, textoId);
        assertTrue(quizBll.comprobarSintaxis(quiz));
    }

    //Test de ejemplo de un Quiz correcto con la cuarta opción correcta
    @Test
    public void testSintaxisQuizCorrecto4(){

        String pregunta = "¿Pregunta ejemplo?";
        String opcion1 = "opcion1";
        String opcion2 = "opcion2";
        String opcion3 = "opcion3";
        String opcion4 = "opcion4";
        String solucion = "opcion4";
        String textoId = "id";

        QuizModelo quiz = new QuizModelo(pregunta, opcion1, opcion2, opcion3, opcion4, solucion, textoId);
        assertTrue(quizBll.comprobarSintaxis(quiz));
    }

    //Test que comprueba que si se usa una pregunta vacía devuelve false
    @Test
    public void testSintaxisPreguntaVacia(){

        String pregunta = "";
        String opcion1 = "opcion1";
        String opcion2 = "opcion2";
        String opcion3 = "opcion3";
        String opcion4 = "opcion4";
        String solucion = "opcion1";
        String textoId = "id";

        QuizModelo quiz = new QuizModelo(pregunta, opcion1, opcion2, opcion3, opcion4, solucion, textoId);
        assertFalse(quizBll.comprobarSintaxis(quiz));
    }

    //Test que comprueba que si se usa la primera opcion vacía devuelve false
    @Test
    public void testSintaxisOp1Vacia(){

        String pregunta = "¿Pregunta ejemplo?";
        String opcion1 = "";
        String opcion2 = "opcion2";
        String opcion3 = "opcion3";
        String opcion4 = "opcion4";
        String solucion = "opcion1";
        String textoId = "id";

        QuizModelo quiz = new QuizModelo(pregunta, opcion1, opcion2, opcion3, opcion4, solucion, textoId);
        assertFalse(quizBll.comprobarSintaxis(quiz));
    }

    //Test que comprueba que si la opción 1 tiene más de 50 caracteres devuelve false
    @Test
    public void testSintaxisOp1Larga(){

        String pregunta = "¿Pregunta ejemplo?";
        String opcion1 = "OpcionDemasiadoLargaComoParaSerConsideradaCorrectaPorLaFuncionDeComprobarSintaxis";
        String opcion2 = "opcion2";
        String opcion3 = "opcion3";
        String opcion4 = "opcion4";
        String solucion = "opcion1";
        String textoId = "id";

        QuizModelo quiz = new QuizModelo(pregunta, opcion1, opcion2, opcion3, opcion4, solucion, textoId);
        assertFalse(quizBll.comprobarSintaxis(quiz));
    }

    //Test que comprueba que si se usa la segunda opcion vacía devuelve false
    @Test
    public void testSintaxisOp2Vacia(){

        String pregunta = "¿Pregunta ejemplo?";
        String opcion1 = "opcion1";
        String opcion2 = "";
        String opcion3 = "opcion3";
        String opcion4 = "opcion4";
        String solucion = "opcion1";
        String textoId = "id";

        QuizModelo quiz = new QuizModelo(pregunta, opcion1, opcion2, opcion3, opcion4, solucion, textoId);
        assertFalse(quizBll.comprobarSintaxis(quiz));
    }

    //Test que comprueba que si la opción 2 tiene más de 50 caracteres devuelve false
    @Test
    public void testSintaxisOp2Larga(){

        String pregunta = "¿Pregunta ejemplo?";
        String opcion1 = "opcion1";
        String opcion2 = "OpcionDemasiadoLargaComoParaSerConsideradaCorrectaPorLaFuncionDeComprobarSintaxis";
        String opcion3 = "opcion3";
        String opcion4 = "opcion4";
        String solucion = "opcion1";
        String textoId = "id";

        QuizModelo quiz = new QuizModelo(pregunta, opcion1, opcion2, opcion3, opcion4, solucion, textoId);
        assertFalse(quizBll.comprobarSintaxis(quiz));
    }

    //Test que comprueba que si se usa la tercera opcion vacía devuelve false
    @Test
    public void testSintaxisOp3Vacia(){

        String pregunta = "¿Pregunta ejemplo?";
        String opcion1 = "opcion1";
        String opcion2 = "opcion2";
        String opcion3 = "";
        String opcion4 = "opcion4";
        String solucion = "opcion1";
        String textoId = "id";

        QuizModelo quiz = new QuizModelo(pregunta, opcion1, opcion2, opcion3, opcion4, solucion, textoId);
        assertFalse(quizBll.comprobarSintaxis(quiz));
    }

    //Test que comprueba que si la opción 3 tiene más de 50 caracteres devuelve false
    @Test
    public void testSintaxisOp3Larga(){

        String pregunta = "¿Pregunta ejemplo?";
        String opcion1 = "opcion1";
        String opcion2 = "opcion2";
        String opcion3 = "OpcionDemasiadoLargaComoParaSerConsideradaCorrectaPorLaFuncionDeComprobarSintaxis";
        String opcion4 = "opcion4";
        String solucion = "opcion1";
        String textoId = "id";

        QuizModelo quiz = new QuizModelo(pregunta, opcion1, opcion2, opcion3, opcion4, solucion, textoId);
        assertFalse(quizBll.comprobarSintaxis(quiz));
    }

    //Test que comprueba que si se usa la cuarta opcion vacía devuelve false
    @Test
    public void testSintaxisOp4Vacia(){

        String pregunta = "¿Pregunta ejemplo?";
        String opcion1 = "opcion1";
        String opcion2 = "opcion2";
        String opcion3 = "opcion3";
        String opcion4 = "";
        String solucion = "opcion1";
        String textoId = "id";

        QuizModelo quiz = new QuizModelo(pregunta, opcion1, opcion2, opcion3, opcion4, solucion, textoId);
        assertFalse(quizBll.comprobarSintaxis(quiz));
    }

    //Test que comprueba que si la opción 4 tiene más de 50 caracteres devuelve false
    @Test
    public void testSintaxisOp4Larga(){

        String pregunta = "¿Pregunta ejemplo?";
        String opcion1 = "opcion1";
        String opcion2 = "opcion2";
        String opcion3 = "opcion3";
        String opcion4 = "OpcionDemasiadoLargaComoParaSerConsideradaCorrectaPorLaFuncionDeComprobarSintaxis";
        String solucion = "opcion1";
        String textoId = "id";

        QuizModelo quiz = new QuizModelo(pregunta, opcion1, opcion2, opcion3, opcion4, solucion, textoId);
        assertFalse(quizBll.comprobarSintaxis(quiz));
    }

    //Test que comprueba que si se usa la solución vacía devuelve false
    @Test
    public void testSintaxisSolucionVacia(){

        String pregunta = "¿Pregunta ejemplo?";
        String opcion1 = "opcion1";
        String opcion2 = "opcion2";
        String opcion3 = "opcion3";
        String opcion4 = "opcion4";
        String solucion = "";
        String textoId = "id";

        QuizModelo quiz = new QuizModelo(pregunta, opcion1, opcion2, opcion3, opcion4, solucion, textoId);
        assertFalse(quizBll.comprobarSintaxis(quiz));
    }

    //Test que comprueba que si la solucion tiene más de 50 caracteres devuelve false
    @Test
    public void testSintaxisSolucionLarga(){

        String pregunta = "¿Pregunta ejemplo?";
        String opcion1 = "opcion1";
        String opcion2 = "opcion2";
        String opcion3 = "opcion3";
        String opcion4 = "opcion4";
        String solucion = "OpcionDemasiadoLargaComoParaSerConsideradaCorrectaPorLaFuncionDeComprobarSintaxis";
        String textoId = "id";

        QuizModelo quiz = new QuizModelo(pregunta, opcion1, opcion2, opcion3, opcion4, solucion, textoId);
        assertFalse(quizBll.comprobarSintaxis(quiz));
    }

    //Test que comprueba que si la solucion no coincide con ninguna de las opciones devuelve false
    @Test
    public void testSintaxisSolucionNoCoincide(){

        String pregunta = "¿Pregunta ejemplo?";
        String opcion1 = "opcion1";
        String opcion2 = "opcion2";
        String opcion3 = "opcion3";
        String opcion4 = "opcion4";
        String solucion = "opcion5";
        String textoId = "id";

        QuizModelo quiz = new QuizModelo(pregunta, opcion1, opcion2, opcion3, opcion4, solucion, textoId);
        assertFalse(quizBll.comprobarSintaxis(quiz));
    }

    //Test que comprueba que si se usa el id vacío devuelve false
    @Test
    public void testSintaxisIdVacio(){

        String pregunta = "¿Pregunta ejemplo?";
        String opcion1 = "opcion1";
        String opcion2 = "opcion2";
        String opcion3 = "opcion3";
        String opcion4 = "opcion4";
        String solucion = "opcion5";
        String textoId = "";

        QuizModelo quiz = new QuizModelo(pregunta, opcion1, opcion2, opcion3, opcion4, solucion, textoId);
        assertFalse(quizBll.comprobarSintaxis(quiz));
    }
}
