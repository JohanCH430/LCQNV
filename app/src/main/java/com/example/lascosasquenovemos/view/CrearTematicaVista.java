package com.example.lascosasquenovemos.view;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lascosasquenovemos.bll.TematicaBll;
import com.example.lascosasquenovemos.dal.FirebaseDAL;
import com.example.lascosasquenovemos.model.Interfaces.TematicaListener;
import com.example.lascosasquenovemos.model.TematicaModelo;

import java.util.List;

public class CrearTematicaVista extends AppCompatActivity implements TematicaListener {

    Button botonCrear, buttonReturn;
    EditText etNombreTematica, etDescripcion;
    TextView comprobracion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_tematica_vista);
        FirebaseDAL.getInstance(getApplicationContext());

        //Inicializamos componentes vista
        botonCrear = findViewById(R.id.buttonCrearTematica);
        etNombreTematica = findViewById(R.id.editTextCrearTematica);
        etDescripcion = findViewById(R.id.editTextCrearTematicaDesacripcion);
        comprobracion = findViewById(R.id.TextoComprobacionCrearTematica);
        buttonReturn = findViewById(R.id.buttonReturnTematica);

        buttonReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        botonCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearTematica();
                cerrarTeclado();
            }
        });
    }

    private void cerrarTeclado() {
        View view =  this.getCurrentFocus();

        if(view != null){
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void crearTematica() {

        //Creamos un modelo de la temática en base a lo que se ha escrito por pantalla.
        TematicaModelo tematica = new TematicaModelo(etNombreTematica.getText().toString(), etDescripcion.getText().toString());

        //Comprobamos la sintaxis y si es correcta se da de alta la temática.
        if(TematicaBll.comprobarSintaxis(tematica)){
            TematicaBll.crearTematica(tematica, this);
        } else{
            comprobracion.setText("No puedes dejar el nombre de la tematica vacío.");
            Toast.makeText(getApplicationContext(), "No puedes dejar el nombre de la temática vacío.", Toast.LENGTH_LONG).show();
        }
    }

    private void vaciarElementosView() {
        etNombreTematica.setText("");
        etDescripcion.setText("");
    }

    @Override
    public void onTematicaReadSucced(TematicaModelo quiz) {

    }

    @Override
    public void onTematicaWriteSucced(Boolean bool) {
        //Comprobamos si el Listener devuelve fallo o correcto. En caso de fallo será porque ya existía el nombre.
        if(bool){
            ((TextView)findViewById(R.id.testViewTestError)).setText("No Fallo");
            comprobracion.setText("Tematica creada con éxito.");
            Toast.makeText(getApplicationContext(), "Temática creada con éxito.", Toast.LENGTH_LONG).show();
            vaciarElementosView();
        } else {
            ((TextView)findViewById(R.id.testViewTestError)).setText("Fallo");
            comprobracion.setText("No se ha podido crear la temática. Nombre repetido.");
            Toast.makeText(getApplicationContext(), "No se ha podido crear la temática. Nombre repetido.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onTematicaReadAllSucced(String[] tematicas) {

    }
}