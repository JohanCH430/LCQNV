package com.example.lascosasquenovemos.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lascosasquenovemos.view.BuildConfig;

public class MainVista extends AppCompatActivity {

    TextView txtVersion;
    Button btnAdminMode, btnJugar;
    Intent iAdminMode, iJugar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_vista);

        //Creo los Intents de las activities a las que tiene que redireccionar.
        //iAdminMode = new Intent(MainVista.this, AdminVista.class);
        //iJugar = new Intent(MainVista.this, );

        //Inicializado las variables con los diferentes elementos de la vista.
        txtVersion = findViewById(R.id.txtVersion);
        btnAdminMode = findViewById(R.id.btnModoAdmin);
        btnJugar = findViewById(R.id.btnJugar);

        //Creo los diferentes OnClick de los botones.
        btnAdminMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modoAdmin();
            }
        });

        btnJugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modoJugar();
            }
        });

        //Pongo la versión en base a la que tiene el build.
        txtVersion.setText("Versión" + BuildConfig.VERSION_NAME);
    }

    //Método que comienza la actividad con la vista de administrador.
    private void modoAdmin(){
        startActivity(iAdminMode);
    }

    //Método que comienza la actividad con la vista de jugar.
    private void modoJugar(){
        //startActivity(iJugar);
    }
}