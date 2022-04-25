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

import com.example.lascosasquenovemos.view.LoginUsuarioVista;
import com.example.lascosasquenovemos.view.R;
import com.example.lascosasquenovemos.view.UsuarioVista;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class PruebaIntegracionIdentificarseConNombre {

    @Rule
    public ActivityScenarioRule<LoginUsuarioVista> IdentificarseConNombreRule = new ActivityScenarioRule<>(LoginUsuarioVista.class);
    String nombre1, nombre2;

    @Before
    public void initTest() {
        nombre1 = "NombreVálido";
        nombre2 = "    ";
    }
    //Nombre de usuario válido
    @Test
    public void testIntegracionIdentificarseConNombreFunciona(){

        //Introducimos un nombre válido
        onView(withId(R.id.Nombre)).perform(clearText(), replaceText(nombre1));

        Intents.init();

        //Hacemos click en el botón de Login
        onView(withId(R.id.BtnLoginUsuario)).perform(click());

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Compruebo que la siguiente pantalla es un InfoVista y que por tanto la partida se ha leido correctamente.
        intended(hasComponent(UsuarioVista.class.getName()));
    }

    //Nombre de usuario vacio/nulo
    @Test
    public void testIntegracionIdentificarseConNombreNoFunciona(){

        //Introducimos un nombre vacio/nulo
        onView(withId(R.id.Nombre)).perform(clearText(), replaceText(nombre2));

        //Hacemos click en el botón de Login
        onView(withId(R.id.BtnLoginUsuario)).perform(click());

        //Comprobamos el texto de error para ver si es válido o no el nombre introducido
        onView(withId(R.id.TextErrorLoginUsuario)).check(matches(withText("ERROR: El nombre de usuario no puede ser nulo")));

    }
}
