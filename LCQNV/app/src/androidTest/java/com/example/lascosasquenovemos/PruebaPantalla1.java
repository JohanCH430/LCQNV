package com.example.lascosasquenovemos;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class PruebaPantalla1 {

    @Rule
    public ActivityTestRule<PruebaEscribirBD> mActivityRule = new ActivityTestRule(PruebaEscribirBD.class);


    @Test
    public void myTest() {
        assert(true);
    }

    @Test
    public void changeText_sameActivity() {
        // Type text and then press the button.
        onView(withId(R.id.texto_nombre))
                .perform(typeText("jjohan"), closeSoftKeyboard());
        onView(withId(R.id.texto_descripcion))
                .perform(typeText("descriptionn"), closeSoftKeyboard());

        onView(withId(R.id.boton_validar)).perform(click());

        // Check that the text was changed.
        onView(withId(R.id.texto_feedback)).check(matches(withText("Nombre y descripción añadidas correctamente")));
    }

}