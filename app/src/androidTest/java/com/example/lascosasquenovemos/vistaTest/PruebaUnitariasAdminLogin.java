package com.example.lascosasquenovemos.vistaTest;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.lascosasquenovemos.model.Interfaces.AdminLoginListener;
import com.example.lascosasquenovemos.view.AdminLoginVista;
import com.example.lascosasquenovemos.view.R;

import org.junit.Rule;
import org.junit.Test;

public class PruebaUnitariasAdminLogin implements AdminLoginListener {
    @Rule
    public ActivityScenarioRule<AdminLoginVista> crearPartidaRule = new ActivityScenarioRule<>(AdminLoginVista.class);

    //Test de si los campos de TextView tienen el texto correspondiente
    @Test
    public void testEmptyEditText(){
        onView(withId(R.id.Contraseña)).check(matches(withText("")));
        onView(withId(R.id.TextErrorLogin)).check(matches(withText("")));
    }
    //Test de si se puede hacer click en todos los botones
    @Test
    public void testButtons(){
        onView(withId(R.id.BtnAtrasLogin)).check(matches(isClickable()));
        onView(withId(R.id.BtnLogin)).check(matches(isClickable()));
    }

    @Override
    public void onAdminLoginReadSuccess(String pw) {

    }
}
