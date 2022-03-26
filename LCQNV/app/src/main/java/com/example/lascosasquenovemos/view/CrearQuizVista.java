package com.example.lascosasquenovemos.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lascosasquenovemos.bll.QuizBll;
import com.example.lascosasquenovemos.model.Interfaces.QuizListener;
import com.example.lascosasquenovemos.model.QuizModelo;

public class CrearQuizVista extends AppCompatActivity implements QuizListener {

    TextView txtPreg, txtOp1, txtOp2, txtOp3, txtOp4, teoria;
    RadioButton btnSolucion;
    Button btnInicio, btnCrear;
    RadioGroup opciones;


    //Boolean que sabe si se ha podido añadir o no.
    Boolean escrituraCorrecta;
    //TODO QuizBll


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_quiz_vista);

        txtPreg = findViewById(R.id.InpPreguntaQuiz);txtPreg.setText("");
        opciones = findViewById(R.id.Opc_correcta_quiz);
        txtOp1 = findViewById(R.id.InpOpc1Quiz); txtOp1.setText("");
        txtOp2 = findViewById(R.id.InpOpc2Quiz);txtOp2.setText("");
        txtOp3 = findViewById(R.id.InpOpc3Quiz);txtOp3.setText("");
        txtOp4 = findViewById(R.id.InpOpc4Quiz);txtOp4.setText("");
        teoria = findViewById(R.id.InpTextoQuiz);teoria.setText("");
        btnInicio = findViewById(R.id.buttonInicioQuiz);
        btnCrear = findViewById(R.id.buttonCrearQuiz);

        btnInicio.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                String msg; //Mensaje a mostrar
                btnSolucion = (RadioButton) findViewById(opciones.getCheckedRadioButtonId());//Solución de entre las posibles opciones
                QuizModelo quiz = new QuizModelo(txtPreg.getText().toString(), txtOp1.getText().toString(), txtOp2.getText().toString(), txtOp3.getText().toString(),
                txtOp4.getText().toString(),btnSolucion.getText().toString(), teoria.getText().toString());
                if(QuizBll.comprobarSintaxis(quiz)) {
                    QuizBll.crearQuiz(quiz, CrearQuizVista.this);
                    if(escrituraCorrecta) {
                        msg = "Quiz creado con éxito";
                        txtPreg.setText("");
                        txtOp1.setText("");
                        txtOp2.setText("");
                        txtOp3.setText("");
                        txtOp4.setText("");
                        teoria.setText("");
                    }
                    else
                        msg = "Ha habido algún fallo a la hora de crear el quiz";
                }
                else
                    msg = "Todos los campos deben ser rellenados y tener menos de 50 caracteres";

                Toast.makeText(CrearQuizVista.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*
        Lectura:
        QuizBll.leerQuiz("Q-1", this);

        Ejemplo Escritura:
        QuizModelo quizM = new QuizModelo("Johaasn", "Jsd", "Ofsd", "fH", "Asdf", "sdffsdN");
        QuizBll.crearQuiz(quizM, this);
    * */


    //Metodos del Listener
    @Override
    public void onQuizReadSucced(QuizModelo quiz) {
        QuizModelo prueba = quiz;
    }

    @Override
    public void onQuizWriteSucced(Boolean bool) {
        escrituraCorrecta = bool;
    }
}