package com.example.lascosasquenovemos.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lascosasquenovemos.bll.PantallaBll;
import com.example.lascosasquenovemos.bll.QuizBll;
import com.example.lascosasquenovemos.dal.FirebaseDAL;
import com.example.lascosasquenovemos.model.Interfaces.PantallaListener;
import com.example.lascosasquenovemos.model.QuizModelo;
import com.example.lascosasquenovemos.model.TextoModelo;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class MainVista extends AppCompatActivity implements PantallaListener {

    TextView txtVersion;
    Button btnAdminMode, btnJugar;
    Intent iAdminMode, iJugar, iUsuario;
    Boolean pantallaCreada = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_vista);

        //Inicializar la referencia a la base da datos através de la variable static dataBase, en caso de que ya exista no la vuelvo a crear.
        FirebaseDAL.getInstance(getApplicationContext());

        //PRUEBAS
        //startActivity(new Intent(MainVista.this, CrearQuizVista.class));

        //Creo los Intents de las activities a las que tiene que redireccionar.
        iAdminMode = new Intent(MainVista.this, AdminLoginVista.class);
        iJugar = new Intent(MainVista.this, InfoVista.class);
        iUsuario = new Intent(MainVista.this, LoginUsuarioVista.class);

        //Inicializado las variables con los diferentes elementos de la vista.
        txtVersion = findViewById(R.id.txtVersion);
        btnAdminMode = findViewById(R.id.btnModoAdmin);
        btnJugar = findViewById(R.id.btnJugar);

        //Inizializamos la creación de una pantalla
        PantallaBll.crearPantalla(this);

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
                if(pantallaCreada){
                    modoJugar();
                }
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
    private void modoJugar(){
        //Si aun no se ha terminado de crear la pantalla no puedes continuar
        //TODO se ha comentado este trozo para que llame al main de usuario
        /*if(pantallaCreada){
            startActivity(iJugar);
        }*/
        //TODO cuando se tenga el login de usuario habra que redirigir ahi 
        startActivity(iUsuario);

    }

    @Override
    public void onQuizReadSucced(QuizModelo quiz) {

    }

    @Override
    public void onQuizWriteSucced(Boolean bool) {

    }

    @Override
    public void onQuizReadQuizByTextId(List<String> quizs) {
        //Comprobamos que no devuelva null, porque eso es un texto sin preguntas, si devuelve null se repite el proceso.
        if(quizs != null){
            Collections.shuffle(quizs);
            iJugar.putExtra("idQuiz", quizs.get(0));
            pantallaCreada = true;
        } else {
            PantallaBll.crearPantalla(this);
        }
    }



    @Override
    public void onTextReadSucced(TextoModelo texto) {

    }

    @Override
    public void onTextWriteSucced(Boolean bool) {

    }

    @Override
    public void onTextReadAllSucced(List<String> textos) {

        //Descolocamos los textos y se coge el primero siempre.
        Collections.shuffle(textos);
        QuizBll.leerQuizsPorTexto(textos.get(0).split(":")[0], this);
        iJugar.putExtra("idTexto", textos.get(0).split(":")[0]);
    }

    @Override
    public void onTextosTematicasReadAllSucceed(HashMap<String, List<String>> textos) {

    }
}