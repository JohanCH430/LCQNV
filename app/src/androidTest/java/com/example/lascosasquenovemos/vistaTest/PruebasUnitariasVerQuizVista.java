package com.example.lascosasquenovemos.vistaTest;



import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static java.util.EnumSet.allOf;

import android.view.View;
import android.widget.TextView;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.lascosasquenovemos.bll.QuizBll;
import com.example.lascosasquenovemos.dal.QuizDAL;
import com.example.lascosasquenovemos.model.Interfaces.QuizListener;
import com.example.lascosasquenovemos.model.QuizModelo;
import com.example.lascosasquenovemos.view.CrearQuizVista;
import com.example.lascosasquenovemos.view.R;
import com.example.lascosasquenovemos.view.VerQuizVista;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;


public class PruebasUnitariasVerQuizVista /*implements QuizListener*/ {

    /*@Rule
    public ActivityScenarioRule<VerQuizVista> crearQuizRule = new ActivityScenarioRule<>(VerQuizVista.class);
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
}

