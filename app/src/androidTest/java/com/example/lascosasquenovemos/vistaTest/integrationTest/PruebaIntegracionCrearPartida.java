package com.example.lascosasquenovemos.vistaTest.integrationTest;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.ext.junit.rules.ActivityScenarioRule;


import com.example.lascosasquenovemos.view.CrearPartidaVista;
import com.example.lascosasquenovemos.view.R;

import org.junit.Rule;
import org.junit.Test;

public class PruebaIntegracionCrearPartida {
    @Rule
    public ActivityScenarioRule<CrearPartidaVista> crearPartidaRule = new ActivityScenarioRule<>(CrearPartidaVista.class);

    @Test
    public void testIntegracionCrearPantallaFunciona(){

        //Introducimos el numero de pantalla a crear
        onView(withId(R.id.InputNumPtlls)).perform(clearText(), replaceText("1"));

        //Hacemos click en el botón de Crear
        onView(withId(R.id.BtnCrearPtll)).perform(click());

        //Esperamos a que se complete la escritura en BD
        try{
            Thread.sleep(8000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        //Comparamos si se ha terminado correctamente
        onView(withId(R.id.testViewTestError)).check(matches(withText("Partida creada correctamente, pulse finalizar para guardarla.")));

    }

    @Test
    public void testIntegracionCrearPantallaFalla(){

        //Introducimos el numero de pantalla a crear
        onView(withId(R.id.InputNumPtlls)).perform(clearText(), replaceText(""));

        //Hacemos click en el botón de Crear
        onView(withId(R.id.BtnCrearPtll)).perform(click());

        //Esperamos a que se complete la escritura en BD
        try{
            Thread.sleep(8000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        //Comparamos no se ha terminado correctamente
        onView(withId(R.id.TextoErrorCrearPtll)).check(matches(withText("Debes introducir un número")));

    }


    @Test
    public void testIntegracionCrearPantallaAtras(){
        //Hacemos click en el botón de Atras
        onView(withId(R.id.BtnAtrasCrearPtll)).perform(click());
        assert(true);
        //Falta completar que retorna a Main Crear
        //onView(withId()).check(matches(isDisplayed()));

    }

    @Test
    public void testIntegracionVerPantallaInicio(){

        //Introducimos el numero de pantalla a crear
        onView(withId(R.id.InputNumPtlls)).perform(clearText(), replaceText("2"));

        //Hacemos click en el botón de Crear
        onView(withId(R.id.BtnCrearPtll)).perform(click());

        //Esperamos a que se complete la escritura en BD
        try{
            Thread.sleep(8000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        onView(withId(R.id.BtnVerPtll)).perform(click());
        onView(withId(R.id.InfoNumPantallas)).check(matches(withText("Previsualización pantalla 1/2")));

    }

    @Test
    public void testIntegracionVerPantallaSig1(){

        //Introducimos el numero de pantalla a crear
        onView(withId(R.id.InputNumPtlls)).perform(clearText(), replaceText("2"));

        //Hacemos click en el botón de Crear
        onView(withId(R.id.BtnCrearPtll)).perform(click());

        //Esperamos a que se complete la escritura en BD
        try{
            Thread.sleep(8000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        onView(withId(R.id.BtnVerPtll)).perform(click());
        onView(withId(R.id.BtnSiguienteVerPtll)).perform(click());
        onView(withId(R.id.InfoNumPantallas)).check(matches(withText("Previsualización pantalla 2/2")));

    }

    @Test
    public void testIntegracionVerPantallaSig2(){

        //Introducimos el numero de pantalla a crear
        onView(withId(R.id.InputNumPtlls)).perform(clearText(), replaceText("2"));

        //Hacemos click en el botón de Crear
        onView(withId(R.id.BtnCrearPtll)).perform(click());

        //Esperamos a que se complete la escritura en BD
        try{
            Thread.sleep(8000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        onView(withId(R.id.BtnVerPtll)).perform(click());
        onView(withId(R.id.BtnSiguienteVerPtll)).perform(click());
        onView(withId(R.id.BtnSiguienteVerPtll)).perform(click());
        onView(withId(R.id.InfoNumPantallas)).check(matches(withText("Previsualización pantalla 1/2")));

    }

    @Test
    public void testIntegracionVerPantallaAnt1(){

        //Introducimos el numero de pantalla a crear
        onView(withId(R.id.InputNumPtlls)).perform(clearText(), replaceText("2"));

        //Hacemos click en el botón de Crear
        onView(withId(R.id.BtnCrearPtll)).perform(click());

        //Esperamos a que se complete la escritura en BD
        try{
            Thread.sleep(8000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        onView(withId(R.id.BtnVerPtll)).perform(click());
        onView(withId(R.id.BtnAnteriorVerPtll)).perform(click());
        onView(withId(R.id.InfoNumPantallas)).check(matches(withText("Previsualización pantalla 2/2")));

    }

    @Test
    public void testIntegracionVerPantallaAnt2(){

        //Introducimos el numero de pantalla a crear
        onView(withId(R.id.InputNumPtlls)).perform(clearText(), replaceText("2"));

        //Hacemos click en el botón de Crear
        onView(withId(R.id.BtnCrearPtll)).perform(click());

        //Esperamos a que se complete la escritura en BD
        try{
            Thread.sleep(8000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        onView(withId(R.id.BtnVerPtll)).perform(click());
        onView(withId(R.id.BtnSiguienteVerPtll)).perform(click());
        onView(withId(R.id.BtnSiguienteVerPtll)).perform(click());
        onView(withId(R.id.InfoNumPantallas)).check(matches(withText("Previsualización pantalla 1/2")));

    }

}
