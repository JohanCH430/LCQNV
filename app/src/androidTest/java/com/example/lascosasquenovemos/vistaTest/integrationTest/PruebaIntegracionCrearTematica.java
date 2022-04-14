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

import com.example.lascosasquenovemos.view.CrearTematicaVista;
import com.example.lascosasquenovemos.view.R;

import org.junit.Rule;
import org.junit.Test;

public class PruebaIntegracionCrearTematica {

    @Rule
    public ActivityScenarioRule<CrearTematicaVista> crearTematicaRule = new ActivityScenarioRule<>(CrearTematicaVista.class);

    @Test
    public void testIntegracionCrearTematicaFunciona(){

        //Escribimos "Tematica prueba" en el Edit Text con id editTextCrearTematica
        onView(withId(R.id.editTextCrearTematica)).perform(clearText(), replaceText("Tematica prueba"));

        //Hacemos click en el botón de Crear
        onView(withId(R.id.buttonCrearTematica)).perform(click());

        //Esperamos a que se complete la escritura en BD
        try{
            Thread.sleep(3000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        //Comparamos el texto que contiene el TextView con identificador 'testViewTestError'.
        onView(withId(R.id.testViewTestError)).check(matches(withText("No Fallo")));

    }

    @Test
    public void testIntegracionCrearTematicaNoFunciona(){

        //Dejamos el campo obligatorio vacío
        onView(withId(R.id.editTextCrearTematica)).perform(clearText(), replaceText(" "));

        //Hacemos click en el botón de Crear
        onView(withId(R.id.buttonCrearTematica)).perform(click());

        //Esperamos a que se complete la escritura en BD
        try{
            Thread.sleep(3000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        //Como el campo obligatorio está vacío, no se escribe en l BD
        onView(withId(R.id.testViewTestError)).check(matches(withText("Fallo")));

    }

    @Test
    public void botonInicio(){
        //Simulamos que damos click en el boton con identificador 'buttonInicio' que tiene como función finalizar el Acitvity.
        onView(withId(R.id.buttonReturnTematica)).perform(click());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Guardamos el estado del escenario simulado en una variable tras simular el click anterior.
        Lifecycle.State pp = crearTematicaRule.getScenario().getState();

        //Comparamos el estado guardado en la variable con el estado DESTROYED para ver si el click ha realizado su función.
        assertEquals(Lifecycle.State.DESTROYED, pp);
    }

}
