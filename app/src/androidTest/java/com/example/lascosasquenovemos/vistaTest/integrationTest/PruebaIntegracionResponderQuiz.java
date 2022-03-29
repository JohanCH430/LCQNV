package com.example.lascosasquenovemos.vistaTest.integrationTest;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.view.View;
import android.widget.TextView;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.lascosasquenovemos.view.ComprobarResultadoVista;
import com.example.lascosasquenovemos.view.InfoVista;
import com.example.lascosasquenovemos.view.R;
import com.example.lascosasquenovemos.view.VerQuizVista;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.regex.Matcher;

public class PruebaIntegracionResponderQuiz {



    @Rule
    public ActivityScenarioRule<InfoVista> crearQuizRule =  new ActivityScenarioRule<>(InfoVista.class);

    //Se pasa de inicio a la siguiente pantalla ya que neceistamos el argumento de la anterior
    @Before
    public void init(){
        onView(withId(R.id.btnContinuar)).perform(click());
    }

   @Test
    public void checkNoRespuesta(){
       onView(withId(R.id.btnComprobar)).perform(click());
       try {
           Thread.sleep(1000);
       } catch (InterruptedException e) {
           e.printStackTrace();
       }
       onView(withId(R.id.TextoComprobacionPorAhora)).check(matches(withText("Se debe seleccionar una respuesta")));
   }
    @Test
    public void checkRespuesta1(){
        Intents.init();
        onView(withId(R.id.ROpc1)).perform(click());
        onView(withId(R.id.btnComprobar)).perform(click());
        intended(hasComponent(ComprobarResultadoVista.class.getName()));
        Intents.release();

    }

    @Test
    public void checkRespuesta2(){
        Intents.init();
        onView(withId(R.id.ROpc2)).perform(click());
        onView(withId(R.id.btnComprobar)).perform(click());
        intended(hasComponent(ComprobarResultadoVista.class.getName()));
        Intents.release();
    }

    @Test
    public void checkRespuesta3(){
        Intents.init();
        onView(withId(R.id.ROpc3)).perform(click());
        onView(withId(R.id.btnComprobar)).perform(click());
        intended(hasComponent(ComprobarResultadoVista.class.getName()));
        Intents.release();
    }

    @Test
    public void checkRespuesta4(){
        Intents.init();
        onView(withId(R.id.ROpc4)).perform(click());
        onView(withId(R.id.btnComprobar)).perform(click());
        intended(hasComponent(ComprobarResultadoVista.class.getName()));
        Intents.release();

    }

}
