package com.example.lascosasquenovemos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PruebaEscribirBD extends AppCompatActivity {

    private Intent intent;
    private TextView feedback;
    private DBAccess accesoBD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prueba_escribir_bd);
        accesoBD = new DBAccess(this);
        EditText tNombre = findViewById(R.id.texto_nombre);
        EditText tDescripcion = findViewById(R.id.texto_descripcion);
        Button bContinuar = findViewById(R.id.boton_continuar);
        Button validar = findViewById(R.id.boton_validar);
        TextView feedback = findViewById(R.id.texto_feedback);

        validar.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        int res = accesoBD.escribirBD(String.valueOf(tNombre.getText()), String.valueOf(tDescripcion.getText()));
                        if(res == 0){
                        feedback.setText("Nombre y descripción añadidas correctamente");
                        } else{
                            feedback.setText("ERROR: Introduce un nombre y una descripción");
                        }
                    }
                });

        bContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(PruebaEscribirBD.this, PruebaLeerBD.class);
                tNombre.setText("");
                tDescripcion.setText("");
                feedback.setText("");
                startActivity(intent);
            }
        });

    }

}