package com.example.lascosasquenovemos.vistaTest.integrationTest;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.lascosasquenovemos.view.EntrarEnPartidaVista;
import com.example.lascosasquenovemos.view.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class PruebaIntegracionEntrarEnPartida {

    @Rule
    public ActivityScenarioRule<EntrarEnPartidaVista> EntrarEnPartidaRule = new ActivityScenarioRule<>(EntrarEnPartidaVista.class);
    String codigo1, codigo2, codigo3, codigo4;

    @Before
    public void initTest() {
        codigo1 = "PartidaPrueba";
        codigo2 = "    ";
        codigo3 = "asdasdasdasdasd";
    }

    @Test
    public void testIntegracionEntrarEnPartidaFunciona() {
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

        //TODO aun falta el DAL.
        /*
        //Hago que en este test el código no exista en la BD.
        onView(withId(R.id.EditTextCodigo)).perform(clearText(), replaceText(codigo3));

        //Hago click en el botón para que comience la partida.
        onView(withId(R.id.botonJugar)).perform(click());

        //Compruebo que ha fallado y por tanto no va a comenzar la partida.
        //Falla porque el código no existe en la BD.
        onView(withId(R.id.TextViewFedBackIntroducirCodigo)).check(matches(withText("Error al leer la partida, el código introducido no existe")));
        */
    }


}
