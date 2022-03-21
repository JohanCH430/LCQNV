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

import java.util.ArrayList;

public class TextoVista extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    TextView txtTitulo, txttexto;
    Button btnInicio, btnCrear;
    Intent iInicio, iCrear;
    Spinner list;
    private ArrayList<String> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_texto_vista);
        txtTitulo = findViewById(R.id.txtInpTitulo);
        txttexto = findViewById(R.id.txtTexto);
        btnCrear = findViewById(R.id.buttonCrear);
        btnInicio = findViewById(R.id.buttonInicio);
        list = findViewById(R.id.list);
        lista = new ArrayList<String>();
        lista.add("Tipo");
        //lista = addType();
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
                startActivity(iCrear);//Falta iniciar intent
            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (adapterView.getId())
        {
            case R.id.list:
                Toast.makeText(this,adapterView.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
                break;

            default:
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}