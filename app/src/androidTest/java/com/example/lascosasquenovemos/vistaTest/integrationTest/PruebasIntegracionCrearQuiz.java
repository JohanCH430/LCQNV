package com.example.lascosasquenovemos.vistaTest.integrationTest;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.junit.Assert.assertEquals;

import androidx.lifecycle.Lifecycle;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.lascosasquenovemos.view.CrearQuizVista;
import com.example.lascosasquenovemos.view.R;

import org.junit.Rule;
import org.junit.Test;

public class PruebasIntegracionCrearQuiz {

    @Rule
    public ActivityScenarioRule<CrearQuizVista> crearQuizRule = new ActivityScenarioRule<>(CrearQuizVista.class);

    @Test
    public void testIntegracionCrearQuizFunciona(){

        //Hago que se ecriba "Pregunta Prueba Integración" en el EditText con id 'InpPreguntaQuiz'.
        onView(withId(R.id.InpPreguntaQuiz)).perform(clearText(), replaceText("Pregunta Prueba Integración"));

        //Hago que se ecriba diferentes opciones en cada uno de los EditText que estan en el activity para que se escriban las opciones.
        onView(withId(R.id.InpOpc1Quiz)).perform(clearText(), replaceText("Opcion A"));
        onView(withId(R.id.InpOpc2Quiz)).perform(clearText(), replaceText("Opcion B"));
        onView(withId(R.id.InpOpc3Quiz)).perform(clearText(), replaceText("Opcion C"));
        onView(withId(R.id.InpOpc4Quiz)).perform(clearText(), replaceText("Opcion D"));

        //Hago un click en el boton de Crear Quiz para que comience el proceso de la base de datos.
        onView(withId(R.id.buttonCrearQuiz)).perform(click());

        try{
            Thread.sleep(4000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        //Compruebo que no ha fallado y por tanto se ha guardado el Quiz en al BD.
        onView(withId(R.id.testViewTestError)).check(matches(withText("No Fallo")));

        try{
            Thread.sleep(4000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

    }

    @Test
    public void testIntegracionCrearQuizNoFunciona(){

        //Hago que se ecriba "Pregunta Prueba Integración" en el EditText con id 'InpPreguntaQuiz'.
        onView(withId(R.id.InpPreguntaQuiz)).perform(clearText(), replaceText("Pregunta Prueba Integración"));

        //Hago que se ecriba diferentes opciones en cada uno de los EditText que estan en el activity para que se escriban las opciones.
        //En este caso solo en dos de ellos
        onView(withId(R.id.InpOpc1Quiz)).perform(clearText(), replaceText("Opcion A"));
        onView(withId(R.id.InpOpc2Quiz)).perform(clearText(), replaceText("Opcion B"));

        //Hago un click en el boton de Crear Quiz para que comience el proceso de la base de datos.
        onView(withId(R.id.buttonCrearQuiz)).perform(click());

        try{
            Thread.sleep(4000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        //Compruebo que ha fallado y por tanto no se ha guardado el Quiz en al BD.
        //Falla porque no se han completado todos los campos.
        onView(withId(R.id.testViewTestError)).check(matches(withText("Fallo")));

    }

    @Test
    public void botonInicio(){
        //Simulamos que damos click en el boton con identificador 'buttonInicio' que tiene como función finalizar el Acitvity.
        onView(withId(R.id.buttonInicioQuiz)).perform(click());

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Guardamos el estado del escenario simulado en una variable tras simular el click anterior.
        Lifecycle.State pp = crearQuizRule.getScenario().getState();

        //Comparamos el estado guardaod en la variable con el estado DESTROYED para ver si el click ha realizado su función.
        assertEquals(Lifecycle.State.DESTROYED, pp);
    }

}
