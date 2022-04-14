package com.example.lascosasquenovemos.vistaTest;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.lascosasquenovemos.model.Interfaces.TematicaListener;
import com.example.lascosasquenovemos.model.TematicaModelo;
import com.example.lascosasquenovemos.view.CrearTematicaVista;
import com.example.lascosasquenovemos.view.R;

import org.junit.Rule;
import org.junit.Test;

import java.util.List;

public class PruebaUnitariasCrearTematica implements TematicaListener {

    @Rule
    public ActivityScenarioRule<CrearTematicaVista> crearTematicaRule = new ActivityScenarioRule<>(CrearTematicaVista.class);


    //Test de si los campos de TextView tienen el texto correspondiente
    @Test
    public void testEmptyEditText(){
        onView(withId(R.id.editTextCrearTematica)).check(matches(withText("")));
        onView(withId(R.id.editTextCrearTematicaDesacripcion)).check(matches(withText("")));
        onView(withId(R.id.TextoComprobacionCrearTematica)).check(matches(withText("")));
    }

    //Test de si se puede hacer click en todos los botones
    @Test
    public void testButtons(){
        onView(withId(R.id.buttonCrearTematica)).check(matches(isClickable()));
    }

    @Test
    public void testCambioTematicaError(){
        onTematicaWriteSucced(true);
        onTematicaWriteSucced(false);
    }


    @Override
    public void onTematicaReadSucced(TematicaModelo quiz) {

    }

    @Override
    public void onTematicaWriteSucced(Boolean bool) {

    }

    @Override
    public void onTematicaReadAllSucced(List<String> tematicas) {

    }
}
