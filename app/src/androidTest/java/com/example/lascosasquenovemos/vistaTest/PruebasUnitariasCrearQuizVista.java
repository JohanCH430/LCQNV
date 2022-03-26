package com.example.lascosasquenovemos.vistaTest;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import android.view.View;
import android.widget.TextView;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import com.example.lascosasquenovemos.model.Interfaces.QuizListener;
import com.example.lascosasquenovemos.model.QuizModelo;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import com.example.lascosasquenovemos.view.CrearQuizVista;
import com.example.lascosasquenovemos.view.R;

public class PruebasUnitariasCrearQuizVista implements QuizListener {

    @Rule
    public ActivityScenarioRule<CrearQuizVista> crearQuizRule = new ActivityScenarioRule<>(CrearQuizVista.class);

    /*set text view in textView */

    public static ViewAction setTextInTextView(final String value){
        return new ViewAction() {
            @SuppressWarnings("unchecked")
            @Override
            public Matcher<View> getConstraints() {
                return CoreMatchers.allOf(isDisplayed(), isAssignableFrom(TextView.class));
                //
                // To check that the found view is TextView or it's subclass like EditText
                // so it will work for TextView and it's descendants
            }

            @Override
            public void perform(UiController uiController, View view) {
                ((TextView) view).setText(value);
            }

            @Override
            public String getDescription() {
                return "replace text";
            }
        };
    }

    //Test de si los campos de EditText están vacíos
    @Test
    public void testEmptyEditText(){
        onView(withId(R.id.InpPreguntaQuiz)).check(matches(withText("")));
        onView(withId(R.id.InpOpc1Quiz)).check(matches(withText("")));
        onView(withId(R.id.InpOpc2Quiz)).check(matches(withText("")));
        onView(withId(R.id.InpOpc3Quiz)).check(matches(withText("")));
        onView(withId(R.id.InpOpc4Quiz)).check(matches(withText("")));
        //onView(withId(R.id.InpTextoQuiz)).check(matches(withText("")));
    }

    //Test de si los campos de TextView tienen el texto correspondiente
    @Test
    public void testEmptyTextView(){
        onView(withId(R.id.Pregunta_quiz)).check(matches(withText("Pregunta:")));
        onView(withId(R.id.Opcion1_quiz)).check(matches(withText("Opción 1:")));
        onView(withId(R.id.Opcion2_quiz)).check(matches(withText("Opción 2:")));
        onView(withId(R.id.Opcion3_quiz)).check(matches(withText("Opción 3:")));
        onView(withId(R.id.Opcion4_quiz)).check(matches(withText("Opción 4:")));
        onView(withId(R.id.Texto_quiz)).check(matches(withText("Nombre del texto asociado:")));
        //onView(withId(R.id.ErrorQuiz)).check(matches(withText("")));
    }

    //Test de si se puede hacer click en todos los botones
    @Test
    public void testButtons(){
        onView(withId(R.id.buttonInicioQuiz)).check(matches(isClickable()));
        onView(withId(R.id.buttonCrearQuiz)).check(matches(isClickable()));
        onView(withId(R.id.Radio_opc1)).check(matches(isClickable()));
        onView(withId(R.id.Radio_opc2)).check(matches(isClickable()));
        onView(withId(R.id.Radio_opc3)).check(matches(isClickable()));
        onView(withId(R.id.Radio_opc4)).check(matches(isClickable()));
    }

    @Test
    public void testCambioTextoError(){
        onQuizWriteSucced(true);
        //onView(withId(R.id.ErrorQuiz)).check(matches(withText("CREADO CON ÉXITO")));
        onQuizWriteSucced(false);
        //onView(withId(R.id.ErrorQuiz)).check(matches(withText("ERROR AL CREAR")));
    }

    @Override
    public void onQuizReadSucced(QuizModelo quiz) {
        //No se usa porque solo se hace una escritura
    }

    @Override
    public void onQuizWriteSucced(Boolean bool) {
        /*
        if (bool == true)
            onView(withId(R.id.ErrorQuiz)).perform(setTextInTextView("CREADO CON ÉXITO"));
        else
            onView(withId(R.id.ErrorQuiz)).perform(setTextInTextView("ERROR AL CREAR"));
        */
    }
}
