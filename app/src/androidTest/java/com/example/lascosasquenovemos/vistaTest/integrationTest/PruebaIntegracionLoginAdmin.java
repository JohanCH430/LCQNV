package com.example.lascosasquenovemos.vistaTest.integrationTest;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.junit.Assert.assertEquals;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.lascosasquenovemos.bll.AdminLoginBll;
import com.example.lascosasquenovemos.model.Interfaces.AdminLoginListener;
import com.example.lascosasquenovemos.view.AdminLoginVista;
import com.example.lascosasquenovemos.view.InfoVista;
import com.example.lascosasquenovemos.view.MainVista;
import com.example.lascosasquenovemos.view.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class PruebaIntegracionLoginAdmin implements AdminLoginListener {

    @Rule
    public ActivityScenarioRule<AdminLoginVista> crearQuizRule =  new ActivityScenarioRule<>(AdminLoginVista.class);
    String password, lectura;

    @Before
    public void initTest(){
        password  = "pw";
    }

    @Test
    public void comprobarLecturaContrasenya(){
        //LLama al Bll para realizar la lectura de la contraseña
        AdminLoginBll.LoginAdmin("pw",this);
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Tras haber esperado que se haga la lectura
        assertEquals(password,lectura);
    }

    @Test
    public void comprobarError1(){
        onView(withId(R.id.Contraseña)).perform(clearText(), replaceText(""));
        onView(withId(R.id.Contraseña)).perform(closeSoftKeyboard());
        onView(withId(R.id.BtnLogin)).perform(click());
        onView(withId(R.id.TextErrorLogin)).check(matches(withText("ERROR: La contraseña es nula o mayor de 25 caracteres")));
    }

    @Test
    public void comprobarError2(){
        onView(withId(R.id.Contraseña)).perform(clearText(), replaceText("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
        onView(withId(R.id.Contraseña)).perform(closeSoftKeyboard());
        onView(withId(R.id.BtnLogin)).perform(click());
        onView(withId(R.id.TextErrorLogin)).check(matches(withText("ERROR: La contraseña es nula o mayor de 25 caracteres")));
    }

    @Override
    public void onAdminLoginReadSuccess(String pw) {
        lectura = pw;
    }
}
