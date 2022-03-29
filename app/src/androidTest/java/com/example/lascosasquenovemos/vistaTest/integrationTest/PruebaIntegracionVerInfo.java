package com.example.lascosasquenovemos.vistaTest.integrationTest;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

import androidx.lifecycle.Lifecycle;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.lascosasquenovemos.view.CrearQuizVista;
import com.example.lascosasquenovemos.view.InfoVista;
import com.example.lascosasquenovemos.view.R;
import com.example.lascosasquenovemos.view.VerQuizVista;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;


public class PruebaIntegracionVerInfo {

    @Rule
    public ActivityScenarioRule<InfoVista> crearQuizRule =  new ActivityScenarioRule<>(InfoVista.class);
    String texto;

    @Before
    public void initTest(){
        texto  = "Este es un texto de prueba que reside en la BD, sirve como prueba de lectura hasta que se haga la funcionalidad de crear pantalla";
    }

    //TODO A dia de hoy no esta la funcionalidad de creacion de pantalla por lo que la pantalla que se prueba solo hace una llamada al texto de prueba
    //https://stackoverflow.com/questions/45597008/espresso-get-text-of-element
    @Test
    public void LecturaTest(){
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.txtViewTexto)).check(matches(withText(texto)));
    }

    @Test
    public void botonContinuar(){
        Intents.init();
        onView(withId(R.id.btnContinuar)).perform(click());
        intended(hasComponent(VerQuizVista.class.getName()));
    }


}
