package com.example.lascosasquenovemos.vistaTest;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.lascosasquenovemos.model.Interfaces.PartidaListener;
import com.example.lascosasquenovemos.model.PartidaModelo;
import com.example.lascosasquenovemos.view.CrearPartidaVista;
import com.example.lascosasquenovemos.view.R;

import org.junit.Rule;
import org.junit.Test;

public class PruebaUnitariasCrearPartida implements PartidaListener {
    @Rule
    public ActivityScenarioRule<CrearPartidaVista> crearPartidaRule = new ActivityScenarioRule<>(CrearPartidaVista.class);

    //Test de si los campos de TextView tienen el texto correspondiente
    @Test
    public void testEmptyEditText(){
        onView(withId(R.id.InputNumPtlls)).check(matches(withText("")));
        onView(withId(R.id.MuestraNumP)).check(matches(withText("")));
    }

    //Test de si se puede hacer click en todos los botones
    @Test
    public void testButtons(){
        onView(withId(R.id.BtnCrearPtll)).check(matches(isClickable()));
        onView(withId(R.id.BtnVerPtll)).check(matches(isClickable()));
        onView(withId(R.id.BtnAtrasCrearPtll)).check(matches(isClickable()));
        onView(withId(R.id.BtnFinalizarCrearPtll)).check(matches(isClickable()));
    }

    @Test
    public void testCrearError(){
        onPartidaWriteSuccess("prueba",true);
        onPartidaWriteSuccess("prueba", false);
    }

    @Override
    public void onPartidaReadSuccess(PartidaModelo pM) {

    }

    @Override
    public void onPartidaWriteSuccess(String codigo, Boolean correct) {

    }
}
