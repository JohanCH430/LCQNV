package com.example.lascosasquenovemos.vistaTest;



import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.lascosasquenovemos.model.QuizModelo;
import com.example.lascosasquenovemos.view.MainVista;
import com.example.lascosasquenovemos.view.R;
import com.example.lascosasquenovemos.view.VerQuizVista;

import org.junit.Rule;
import org.junit.Test;

import java.time.temporal.ValueRange;


public class PruebasUnitariasVerQuizVista /*implements QuizListener*/ {

    @Rule
    public ActivityScenarioRule<MainVista> crearQuizRule = new ActivityScenarioRule<>(MainVista.class);
    public QuizModelo quiz = new QuizModelo("¿?", "op1", "op2", "op3", "op4","","");


    /*set text view in textView

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

    //Test de si los campos de TextView tienen el texto correspondiente
    @Test
    public void testEmptyTextView(){
        onView(withId(R.id.tVPregunta)).check(matches(withText("PREGUNTA")));
        onView(withId(R.id.ROpc1)).check(matches(withText("Opción 1")));
        onView(withId(R.id.ROpc2)).check(matches(withText("Opción 2")));
        onView(withId(R.id.ROpc3)).check(matches(withText("Opción 3")));
        onView(withId(R.id.ROpc4)).check(matches(withText("Opción 4")));
         }

    //Test de si se puede hacer click en todos los botones
    @Test
    public void testButtons(){
        onView(withId(R.id.btnComprobar)).check(matches(isClickable()));
        onView(withId(R.id.ROpc1)).check(matches(isClickable()));
        onView(withId(R.id.ROpc2)).check(matches(isClickable()));
        onView(withId(R.id.ROpc3)).check(matches(isClickable()));
        onView(withId(R.id.ROpc4)).check(matches(isClickable()));
    }

    @Test
    public void testCambioRadioButton(){
        onQuizReadSucced(quiz);
        onView(withId(R.id.ROpc1)).check(matches(withText(quiz.getOpcionA())));
        onView(withId(R.id.ROpc2)).check(matches(withText(quiz.getOpcionB())));
        onView(withId(R.id.ROpc3)).check(matches(withText(quiz.getOpcionC())));
        onView(withId(R.id.ROpc4)).check(matches(withText(quiz.getOpcionD())));
    }

    @Override
    public void onQuizReadSucced(QuizModelo quiz) {
        onView(withId(R.id.ROpc1)).perform(setTextInTextView(quiz.getOpcionA()));
        onView(withId(R.id.ROpc2)).perform(setTextInTextView(quiz.getOpcionB()));
        onView(withId(R.id.ROpc3)).perform(setTextInTextView(quiz.getOpcionC()));
        onView(withId(R.id.ROpc4)).perform(setTextInTextView(quiz.getOpcionD()));
    }

    @Override
    public void onQuizWriteSucced(Boolean bool) {
        //No se usa porque solo se hace una lectura
    }*/

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

