package com.example.lascosasquenovemos.vistaTest.integrationTest;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertEquals;
import androidx.lifecycle.Lifecycle;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import com.example.lascosasquenovemos.view.R;
import com.example.lascosasquenovemos.view.TextoVista;
import org.junit.Rule;
import org.junit.Test;

public class PruebaIntegracionCrearTexto {

    @Rule
    public ActivityScenarioRule<TextoVista> crearTextoRule = new ActivityScenarioRule<>(TextoVista.class);

    @Test
    public void testIntegracionCrearTextoFunciona(){

        //Simulamos en el EditText con identificador 'txtInpTitulo' que escribimos el texto "Titulo prueba"
        onView(withId(R.id.txtInpTitulo)).perform(clearText(), replaceText("Titulo prueba"));

        //Simulamos en el EditText con identificador 'txtTexto' que escribimos el texto "Titulo prueba"
        onView(withId(R.id.txtTexto)).perform(clearText(), replaceText("Texto prueba"));

        //Simulamos que seleccionamos la opcion 2 del Spinner con identificador 'list'.
        onView(withId(R.id.list)).perform(click());
        onData(anything()).atPosition(1).perform(click());  //(opcion 1 = posicion 0, opcion 2 = posicion 1, etc...)
        onView(withId(R.id.list)).check(matches(withSpinnerText(containsString("Visual"))));

        //Simulamos que damos click en el boton con identificador 'buttonCrear'
        onView(withId(R.id.buttonCrear)).perform(click());

        try{
            Thread.sleep(3000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        //Comparamos el texto que contiene el TextView con identificador 'testViewTestError'.
        onView(withId(R.id.testViewTestError)).check(matches(withText("Fallo")));

    }


    @Test
    public void testIntegracionCrearTextoNoFunciona(){

        //Simulamos en el EditText con identificador 'txtInpTitulo' que escribimos el texto "Titulo prueba"
        onView(withId(R.id.txtInpTitulo)).perform(clearText(), replaceText("Titulo prueba"));

        //Simulamos en el EditText con identificador 'txtTexto' que escribimos el texto "Titulo prueba"
        onView(withId(R.id.txtTexto)).perform(clearText(), replaceText("Texto prueba"));

        //Simulamos que damos click en el boton con identificador 'buttonCrear'
        onView(withId(R.id.buttonCrear)).perform(click());

        try{
            Thread.sleep(3000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        //No simulamos una seleccion en el spinner para que esto conlleve al fallo.

        //Comparamos el texto que contiene el TextView con identificador 'testViewTestError'.
        onView(withId(R.id.testViewTestError)).check(matches(withText("Fallo")));

    }

    @Test
    public void botonInicio(){

        //Simulamos que damos click en el boton con identificador 'buttonInicio' que tiene como función finalizar el Acitvity.
        onView(withId(R.id.buttonInicio)).perform(click());

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Guardamos el estado del escenario simulado en una variable tras simular el click anterior.
        Lifecycle.State pp = crearTextoRule.getScenario().getState();

        //Comparamos el estado guardaod en la variable con el estado DESTROYED para ver si el click ha realizado su función.
        assertEquals(Lifecycle.State.DESTROYED, pp);

    }

}
