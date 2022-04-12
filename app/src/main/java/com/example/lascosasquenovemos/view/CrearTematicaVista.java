package com.example.lascosasquenovemos.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lascosasquenovemos.bll.QuizBll;
import com.example.lascosasquenovemos.bll.TematicaBll;
import com.example.lascosasquenovemos.model.Interfaces.TematicaListener;
import com.example.lascosasquenovemos.model.TematicaModelo;

import java.util.List;

public class CrearTematicaVista extends AppCompatActivity implements TematicaListener {

    Button botonCrear;
    EditText etNombreTematica, etDescripcion;
    TextView comprobracion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_tematica_vista);

        //Inicializamos componentes vista
        botonCrear = findViewById(R.id.buttonCrearTematica);
        etNombreTematica = findViewById(R.id.editTextCrearTematica);
        etDescripcion = findViewById(R.id.editTextCrearTematicaDesacripcion);
        comprobracion = findViewById(R.id.TextoComprobacionCrearTematica);

        botonCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearTematica();
            }
        });
    }

    private void crearTematica() {

        //Creamos un modelo de la temática en base a lo que se ha escrito por pantalla.
        TematicaModelo tematica = new TematicaModelo(etNombreTematica.getText().toString(), etDescripcion.getText().toString());

        //Comprobamos la sintaxis y si es correcta se da de alta la temática.
        if(TematicaBll.comprobarSintaxis(tematica)){
            TematicaBll.crearTematica(tematica, this);
        } else{
            comprobracion.setText("No puedes dejar el nombre de la tematica vacío.");
        }
    }

    @Override
    public void onTematicaReadSucced(TematicaModelo quiz) {

    }

    @Override
    public void onTematicaWriteSucced(Boolean bool) {
        //Comprobamos si el Listener devuelve fallo o correcto. En caso de fallo será porque ya existía el nombre.
        if(bool){
            comprobracion.setText("Tematica creada con éxito.");
        } else {
            comprobracion.setText("No se ha podido crear la temática. Nombre repetido.");
        }
    }

    @Override
    public void onTematicaReadAllSucced(List<String> tematicas) {

    }
}