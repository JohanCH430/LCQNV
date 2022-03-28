package com.example.lascosasquenovemos.vistaTest.integrationTest;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.lascosasquenovemos.view.MainVista;
import com.example.lascosasquenovemos.view.R;

import org.junit.Rule;
import org.junit.Test;

public class PruebasIntergrationTest {
    @Rule
    public ActivityScenarioRule<MainVista> crearQuizRule = new ActivityScenarioRule<>(MainVista.class);
    @Test //Test de comprobar si pasa a siguiente pantalla
    public void TestWithOpc(){
        onView(withId(R.id.btnJugar)).perform(click());
        onView(withId(R.id.btnContinuar)).perform(click());
        onView(withId(R.id.ROpc4)).perform(click());
        onView(withId(R.id.btnComprobar)).perform(click());
        onView(withId(R.id.btnReintentar)).check(matches(isDisplayed()));
    }
    @Test//Test sin dar una opcion queda en la pantalla adecuada
    public void TestWithoutOpc(){
        onView(withId(R.id.btnJugar)).perform(click());
        onView(withId(R.id.btnContinuar)).perform(click());
        onView(withId(R.id.btnComprobar)).perform(click());
        onView(withId(R.id.btnComprobar)).check(matches(isDisplayed()));
    }

    @Test//Test dar mas de una opcion queda en la pantalla adecuada
    public void TestWithMoreOpc(){
        onView(withId(R.id.btnJugar)).perform(click());
        onView(withId(R.id.btnContinuar)).perform(click());
        onView(withId(R.id.ROpc4)).perform(click());
        onView(withId(R.id.ROpc1)).perform(click());
        onView(withId(R.id.btnComprobar)).perform(click());
        onView(withId(R.id.btnReintentar)).check(matches(isDisplayed()));
    }

    @Test//Test probar las funciones de btones
    public void TestDisplayBtn(){
        onView(withId(R.id.btnJugar)).perform(click());
        onView(withId(R.id.btnContinuar)).perform(click());
        onView(withId(R.id.ROpc1)).check(matches(isDisplayed()));
        onView(withId(R.id.ROpc2)).check(matches(isDisplayed()));
        onView(withId(R.id.ROpc3)).check(matches(isDisplayed()));
        onView(withId(R.id.ROpc4)).check(matches(isDisplayed()));
        onView(withId(R.id.btnComprobar)).check(matches(isDisplayed()));
    }
}
