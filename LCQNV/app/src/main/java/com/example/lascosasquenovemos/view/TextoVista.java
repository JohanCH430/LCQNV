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

import com.example.lascosasquenovemos.bll.TextoBll;
import com.example.lascosasquenovemos.model.Interfaces.TextListener;
import com.example.lascosasquenovemos.model.TextoModelo;

import java.util.ArrayList;

public class TextoVista extends AppCompatActivity implements AdapterView.OnItemSelectedListener, TextListener {


    TextView txtTitulo, txttexto;
    Button btnInicio, btnCrear;
    Intent iInicio, iCrear;
    Spinner list;
    private ArrayList<String> lista;
    private TextoBll txtBll;
    private String tema;
    TextView Error;
    private Boolean escrituraCorrecta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_texto_vista);
        txtTitulo = findViewById(R.id.txtInpTitulo);
        txttexto = findViewById(R.id.txtTexto);
        btnCrear = findViewById(R.id.buttonCrear);
        btnInicio = findViewById(R.id.buttonInicio);
        list = findViewById(R.id.list);
        Error = findViewById(R.id.Error);
        lista = new ArrayList<String>();
        lista.add("Tipo");
        lista.add("Visual");
        lista.add("Auditiva");
        lista.add("Fisica");
        list.setOnItemSelectedListener(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lista);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        list.setAdapter(adapter);

        btnInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(iInicio);//Falta iniciar intent
                }
        });

        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Falta introducir en el base de datos
                //Introducir en el firebase los texto
                TextoModelo texto = new TextoModelo("0",txtTitulo.toString(), txttexto.toString(), tema);
                //txtBll.crearTexto(texto, this);
                if(escrituraCorrecta){
                    Error.setText("Texto creado con éxito");
                    //startActivity(iCrear);//Falta iniciar intent
                }
                else{
                    Error.setText("Error al crear texto");
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
        this.escrituraCorrecta = bool;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (adapterView.getId())
        {
            case R.id.list:
                Toast.makeText(this,adapterView.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
                tema = lista.get(i).toString();
                break;

            default:
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}