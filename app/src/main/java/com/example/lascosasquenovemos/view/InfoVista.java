package com.example.lascosasquenovemos.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.lascosasquenovemos.bll.TextoBll;
import com.example.lascosasquenovemos.dal.FirebaseDAL;
import com.example.lascosasquenovemos.model.Interfaces.TextListener;
import com.example.lascosasquenovemos.model.TextoModelo;

import java.util.List;

public class InfoVista extends AppCompatActivity implements TextListener {
    private String idTexto;
    private TextoBll bd;
    private String text;
    TextView texto;
    Intent next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_vista);

        FirebaseDAL.getInstance(getApplicationContext());
        next = new Intent(InfoVista.this, VerQuizVista.class);
        bd = new TextoBll();

        //Se obtiene el idTexto de la función de creación de pantalla que se llama desde la vista anterior
        idTexto = getIntent().getStringExtra("idTexto");

        Button continuar = findViewById(R.id.btnContinuar);
        texto = findViewById(R.id.txtViewTexto);
        //Se actualiza el textView con el valor obtenido en la lectura de la Base de Datos
        bd.leerTexto(idTexto, this);
        continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                next.putExtra("idQuiz", getIntent().getStringExtra("idQuiz"));
                startActivity(next);
            }
        });
    }


    @Override
    public void onTextReadSucced(TextoModelo texto) {
        this.texto.setText(texto.getTexto());
    }

    @Override
    public void onTextWriteSucced(Boolean bool) {
        //No se implementa porque desde esta vista solo se lee
    }

    @Override
    public void onTextReadAllSucced(List<String> textos) {

    }
}