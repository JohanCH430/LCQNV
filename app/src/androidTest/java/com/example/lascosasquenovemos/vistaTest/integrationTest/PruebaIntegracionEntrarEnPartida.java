package com.example.lascosasquenovemos.vistaTest.integrationTest;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.lascosasquenovemos.view.EntrarEnPartidaVista;
import com.example.lascosasquenovemos.view.InfoVista;
import com.example.lascosasquenovemos.view.R;
import com.example.lascosasquenovemos.view.VerQuizVista;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class PruebaIntegracionEntrarEnPartida {

    @Rule
    public ActivityScenarioRule<EntrarEnPartidaVista> EntrarEnPartidaRule = new ActivityScenarioRule<>(EntrarEnPartidaVista.class);
    String codigo1, codigo2, codigo3, codigo4;

    @Before
    public void initTest() {
        codigo1 = "P-1";
        codigo2 = "    ";
        codigo3 = "asdasdasdasdasd";
    }

    @Test
    public void testIntegracionEntrarEnPartidaFunciona() {

        //Hago que en este test el código esté vacio/tenga espacios.
        onView(withId(R.id.EditTextCodigo)).perform(clearText(), replaceText(codigo1));

        Intents.init();
        //Hago click en el botón para que comience la partida.
        onView(withId(R.id.botonJugar)).perform(click());

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Compruebo que la siguiente pantalla es un InfoVista y que por tanto la partida se ha leido correctamente.
        intended(hasComponent(InfoVista.class.getName()));
    }

    @Test
    public void testIntegracionEntrarEnPartidaNoFunciona() {

        //Hago que en este test el código esté vacio/tenga espacios.
        onView(withId(R.id.EditTextCodigo)).perform(clearText(), replaceText(codigo2));

        //Hago click en el botón para que comience la partida.
        onView(withId(R.id.botonJugar)).perform(click());

        //Compruebo que ha fallado y por tanto no va a comenzar la partida.
        //Falla porque el código no es válido.
        onView(withId(R.id.TextViewFedBackIntroducirCodigo)).check(matches(withText("Error, el código introducido es vacío")));
    }

    @Test
    public void testIntegracionEntrarEnPartidaNoFunciona2() {

        //Hago que en este test el código sea null.
        //Para eso no escribo código.

        //Hago click en el botón para que comience la partida.
        onView(withId(R.id.botonJugar)).perform(click());

        //Compruebo que ha fallado y por tanto no va a comenzar la partida.
        //Falla porque el código no es válido.
        onView(withId(R.id.TextViewFedBackIntroducirCodigo)).check(matches(withText("Error, el código introducido es vacío")));
    }

    @Test
    public void testIntegracionEntrarEnPartidaNoFunciona3() {

        //Hago que en este test el código no exista en la BD.
        onView(withId(R.id.EditTextCodigo)).perform(clearText(), replaceText(codigo3));

        //Hago click en el botón para que comience la partida.
        onView(withId(R.id.botonJugar)).perform(click());

        //Compruebo que ha fallado y por tanto no va a comenzar la partida.
        //Falla porque el código no existe en la BD.
        onView(withId(R.id.TextViewFedBackIntroducirCodigo)).check(matches(withText("Error al leer la partida, el código introducido no existe")));

    }

}
