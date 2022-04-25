package com.example.lascosasquenovemos.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lascosasquenovemos.model.PantallaModelo;
import com.example.lascosasquenovemos.model.PartidaModelo;

public class VerPartidaVista extends AppCompatActivity {

    private Button btnAnterior, btnTerminar, btnContinuar;
    private TextView textNumPantalla,textInfo,textPreg, textOpc1, textOpc2, textOpc3, textOpc4;
    PartidaModelo partidaModelo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_partida_vista);

        //Inicializar variables para poder añadir los listeenrs de los botones y cambiar los textos
        btnAnterior = findViewById(R.id.BtnAnteriorVerPtll);
        btnTerminar = findViewById(R.id.BtnTerminarVerPtll);
        btnContinuar = findViewById(R.id.BtnSiguienteVerPtll);

        textNumPantalla = findViewById(R.id.InfoNumPantallas);
        textInfo = findViewById(R.id.TextInfoVerPartida);
        textPreg = findViewById(R.id.TextPregVerPartida);
        textOpc1 = findViewById(R.id.TextOpc1VerPartida);
        textOpc2 = findViewById(R.id.TextOpc2VerPartida);
        textOpc3 = findViewById(R.id.TextOpc3VerPartida);
        textOpc4 = findViewById(R.id.TextOpc4VerPartida);

        //Se obtiene la partida que ha llegado de la pantalla anterior
        partidaModelo = (PartidaModelo) getIntent().getSerializableExtra("PARTIDA");

        final int[] indice = {0};
        final int[] maxPant = {partidaModelo.getNumPantallas() - 1};
        PantallaModelo pantalla = (PantallaModelo) partidaModelo.getPantallasPartida().values().toArray()[indice[0]];

        changeText(0, maxPant[0]);

        btnAnterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (indice[0] > 0) {
                    //En caso de que no sea la primera pantalla se resta uno al indice y se muestra la siguiente pantalla
                    indice[0]--;
                    changeText(indice[0], maxPant[0]);
                }
                else {
                    //En caso contrario la pantalla anterior seria la ultima pantalla
                    indice[0] = maxPant[0];
                    changeText(indice[0], maxPant[0]);
                }
            }
        });

        btnTerminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Cuando se termine de previsualizar se termina este activity y se vuelve a la pantalla anterior
                finish();
            }
        });

        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (indice[0] < maxPant[0]) {
                    //Si no es la ultima pantalla se suma uno al indice y se muestra la siguiente pantalla
                    indice[0]++;
                    changeText(indice[0], maxPant[0]);
                }
                else {
                    //En caso contrario la pantalla seria la primera
                    indice[0] = 0;
                    changeText(indice[0], maxPant[0]);
                }
            }
        });
    }

    private void changeText(int indice, int maxPant){
        int aux1 = indice +1;
        int aux2 = maxPant+1;
        //Texto para mostrar el numero de la pantalla en la que te encuentras
        textNumPantalla.setText("Previsualización pantalla " + aux1 + "/" + aux2);

        //Se obtiene la partida actual a partir del indice que se proporciona
        PantallaModelo pantalla = (PantallaModelo) partidaModelo.getPantallasPartida().values().toArray()[indice];

        //Se actualizan los parametros correspondientes
        textInfo.setText(pantalla.getTexto().getTexto());
        textPreg.setText(pantalla.getQuiz().getPregunta());
        textOpc1.setText(pantalla.getQuiz().getOpcionA());
        textOpc2.setText(pantalla.getQuiz().getOpcionB());
        textOpc3.setText(pantalla.getQuiz().getOpcionC());
        textOpc4.setText(pantalla.getQuiz().getOpcionD());

        //Se actualizan las opciones para mostrar cual es la correcta
        if (pantalla.getQuiz().getOpcionA().equals(pantalla.getQuiz().getSolucion())){
            textOpc1.setText(pantalla.getQuiz().getOpcionA() + " (CORRECTA)");
        }
        else if (pantalla.getQuiz().getOpcionB().equals(pantalla.getQuiz().getSolucion())){
            textOpc2.setText(pantalla.getQuiz().getOpcionB() + " (CORRECTA)");
        }
        else if (pantalla.getQuiz().getOpcionC().equals(pantalla.getQuiz().getSolucion())){
            textOpc3.setText(pantalla.getQuiz().getOpcionC() + " (CORRECTA)");
        }
        else {
            textOpc4.setText(pantalla.getQuiz().getOpcionD() + " (CORRECTA)");
        }
    }
}