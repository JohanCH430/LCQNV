package com.example.lascosasquenovemos.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lascosasquenovemos.bll.TematicaBll;
import com.example.lascosasquenovemos.bll.TextoBll;
import com.example.lascosasquenovemos.dal.FirebaseDAL;
import com.example.lascosasquenovemos.model.Interfaces.TematicaListener;
import com.example.lascosasquenovemos.model.Interfaces.TextListener;
import com.example.lascosasquenovemos.model.TematicaModelo;
import com.example.lascosasquenovemos.model.TextoModelo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TextoVista extends AppCompatActivity implements AdapterView.OnItemSelectedListener, TextListener, TematicaListener {

    TextView txtTitulo, txttexto;
    Button btnInicio, btnCrear;
    Spinner list;
    private String[] lista;
    private String tema;
    TextView Error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_texto_vista);
        FirebaseDAL.getInstance(getApplicationContext());

        //Se llama a un método de Temática Bll que se encargará de leer las temáticas que haya en la BD.
        TematicaBll.leerTematica(this);

        txtTitulo = findViewById(R.id.txtInpTitulo);
        txttexto = findViewById(R.id.txtTexto);
        btnCrear = findViewById(R.id.buttonCrear);
        btnInicio = findViewById(R.id.buttonInicio);
        list = findViewById(R.id.list);
        Error = findViewById(R.id.Error);

        btnInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    finish();
                }
        });

        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Falta introducir en el base de datos
                //Introducir en el firebase los texto
                TextoModelo texto = new TextoModelo("0",txtTitulo.getText().toString(), txttexto.getText().toString(), tema);

                //Compruebo que el texto sea correcto, si no lo es doy mensaje de error.
                int error = TextoBll.comprobarSintaxis(texto);
                if(error == 0){
                    TextoBll.crearTexto(texto, TextoVista.this);
                } else{
                    switch(error){
                        case -1:  Error.setText("Error al crear texto, o esta vacio o supera los 2000 caracteres"); break;
                        case -2:  Error.setText("Error al crear texo, no se ha introducido un título"); break;
                        case -3:  Error.setText("Error al crear texto, tienes que asociar el texto a una temática"); break;
                    }
                }

            }
        });


    }

    //LISTENER DE LA BASE DE DATOS
    @Override
    public void onTextReadSucced(TextoModelo texto) {
        //No se implementa ya que esta clase no hace una lectura
    }

    @Override
    public void onTextWriteSucced(Boolean bool) {
        if(bool){
            Error.setText("Texto creado con éxito");
            //Si no ha habido ningún fallo en la base de datos se limpian el titulo y texyo introducidos
            txtTitulo.setText("");
            txttexto.setText("");
            list.setSelection(0);
        }
        else{
            Error.setText("Error al crear texto, ha habido algún problema con la Base de Datos");
        }

    }

    @Override
    public void onTextReadAllSucced(List<String> textos) {

    }

    @Override
    public void onTextosTematicasReadAllSucceed(HashMap<String, List<String>> textos) {

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (adapterView.getId())
        {
            case R.id.list:
                tema = lista[i].toString();
                break;

            default:
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onTematicaReadSucced(TematicaModelo tematica) {
        //No se va a leer una única temática
    }

    @Override
    public void onTematicaWriteSucced(Boolean bool) {
        //No se escriben tematicas en la BBDD
    }

    @Override
    public void onTematicaReadAllSucced(String[] tematicas) {

        lista = tematicas;

        list.setOnItemSelectedListener(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lista);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        list.setAdapter(adapter);
    }
}