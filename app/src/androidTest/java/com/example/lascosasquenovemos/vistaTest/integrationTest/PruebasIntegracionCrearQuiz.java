package com.example.lascosasquenovemos.vistaTest.integrationTest;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.lascosasquenovemos.view.CrearQuizVista;
import com.example.lascosasquenovemos.view.R;

import org.junit.Rule;
import org.junit.Test;

public class PruebasIntegracionCrearQuiz {

    @Rule
    public ActivityScenarioRule<CrearQuizVista> crearTextoRule = new ActivityScenarioRule<>(CrearQuizVista.class);

    @Test
    public void testIntegracionCrearQuizFunciona(){

        onView(withId(R.id.InpPreguntaQuiz)).perform(clearText(), replaceText("Pregunta Prueba Integración"));

        onView(withId(R.id.InpOpc1Quiz)).perform(clearText(), replaceText("Opcion A"));
        onView(withId(R.id.InpOpc2Quiz)).perform(clearText(), replaceText("Opcion B"));
        onView(withId(R.id.InpOpc3Quiz)).perform(clearText(), replaceText("Opcion C"));
        onView(withId(R.id.InpOpc4Quiz)).perform(clearText(), replaceText("Opcion D"));

        onView(withId(R.id.buttonCrearQuiz)).perform(click());

        try{
            Thread.sleep(4000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        onView(withId(R.id.testViewTestError)).check(matches(withText("No Fallo")));

        try{
            Thread.sleep(4000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

    }

    @Test
    public void testIntegracionCrearQuizNoFunciona(){

        onView(withId(R.id.InpPreguntaQuiz)).perform(clearText(), replaceText("Pregunta Prueba Integración"));

        onView(withId(R.id.InpOpc1Quiz)).perform(clearText(), replaceText("Opcion A"));
        onView(withId(R.id.InpOpc2Quiz)).perform(clearText(), replaceText("Opcion B"));

        onView(withId(R.id.buttonCrearQuiz)).perform(click());

        try{
            Thread.sleep(4000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        onView(withId(R.id.testViewTestError)).check(matches(withText("Fallo")));

    }


}
