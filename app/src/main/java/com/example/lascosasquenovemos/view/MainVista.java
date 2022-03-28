package com.example.lascosasquenovemos.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.lascosasquenovemos.dal.FirebaseDAL;

public class MainVista extends AppCompatActivity {

    TextView txtVersion;
    Button btnAdminMode, btnJugar;
    Intent iAdminMode, iJugar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_vista);

        //Inicializar la referencia a la base da datos através de la variable static dataBase, en caso de que ya exista no la vuelvo a crear.
        FirebaseDAL.getInstance(getApplicationContext());

        //PRUEBAS
        //startActivity(new Intent(MainVista.this, CrearQuizVista.class));

        //Creo los Intents de las activities a las que tiene que redireccionar.
        iAdminMode = new Intent(MainVista.this, AdminVista.class);
        iJugar = new Intent(MainVista.this, InfoVista.class);

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
        txtVersion.setText("Versión " + 1.0);
    }

    //Método que comienza la actividad con la vista de administrador.
    private void modoAdmin(){
        startActivity(iAdminMode);
    }

    //Método que comienza la actividad con la vista de jugar.
    private void modoJugar(){startActivity(iJugar);}
}