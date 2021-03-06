package com.example.lascosasquenovemos.dal;

import android.util.Log;
import androidx.annotation.NonNull;
import com.example.lascosasquenovemos.model.Interfaces.QuizListener;
import com.example.lascosasquenovemos.model.QuizModelo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class QuizDAL extends FirebaseDAL {

    public static void leerQuiz(String id, QuizListener qL) {

        DatabaseReference refQuiz = FirebaseDAL.dataBase.child("Quiz");

        refQuiz.child(id).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                if (!task.isSuccessful()) {
                    Log.e("firebase error", "Error getting data", task.getException());
                } else {
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));

                    if (String.valueOf(task.getResult().getValue()) == "null") { //Cuando falla la busqueda en la BD, porque no existe ningún Quiz con esa id, te devuelve un null.
                        qL.onQuizReadSucced(null);
                    } else {

                        //Java interpreta lo recibido como un HashMap, unicamente hay que parsearlo por claves.
                        HashMap<String, Object> result = (HashMap<String, Object>) task.getResult().getValue();

                        String pregunta = (String) result.get("Pregunta");
                        String solucion = (String) result.get("Solucion");
                        String textId = (String) result.get("TextId");

                        HashMap<String, String> opciones = (HashMap<String, String>) result.get("Opciones");

                        String oA = opciones.get("a");
                        String oB = opciones.get("b");
                        String oC = opciones.get("c");
                        String oD = opciones.get("d");

                        qL.onQuizReadSucced(new QuizModelo(pregunta, oA, oB, oC, oD, solucion, textId));

                    }

                }
            }
        });

    }

    public static void crearQuiz(QuizModelo quiz, QuizListener qL){

        DatabaseReference refQuizID = FirebaseDAL.dataBase.child("IDQuiz");
        DatabaseReference refQuiz = FirebaseDAL.dataBase.child("Quiz");
        DatabaseReference refTextoQuiz = FirebaseDAL.dataBase.child("TextoPregunta");

        refQuizID.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                if (!task.isSuccessful()) {
                    Log.e("firebase error", "Error getting data", task.getException());
                } else {
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));

                    //Creo un nuevo ID que es el siguiente al que hay guardado.
                    String[] datos = String.valueOf(task.getResult().getValue()).split("-");
                    int aux = Integer.parseInt(datos[1 ]) + 1;
                    String nuevoID = datos[0] + "-" + String.valueOf(aux);

                    //Actualizado el valor con el nuevo ID.
                    refQuizID.setValue(nuevoID).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("firebase", e.getLocalizedMessage());
                        }
                    });

                    HashMap<String, Object> quizMap = new HashMap<String, Object>();
                    HashMap<String, String> opt = new HashMap<String, String>();

                    //Opciones
                    opt.put("a", quiz.getOpcionA());
                    opt.put("b", quiz.getOpcionB());
                    opt.put("c", quiz.getOpcionC());
                    opt.put("d", quiz.getOpcionD());

                    //Valores generales
                   quizMap.put("Opciones", opt);
                   quizMap.put("Pregunta", quiz.getPregunta());
                   quizMap.put("Solucion", quiz.getSolucion());
                   quizMap.put("TextId", quiz.getTextId());

                    //Añado el mapa a la bd
                    refQuiz.child(nuevoID).setValue(quizMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            qL.onQuizWriteSucced(task.isSuccessful());
                        }
                    });

                    //TODO Añadir relación texto-pregunta Preguntar Sujeto a cambio
                    //Añado la relación entre Texto y pregunta. (Un texto, muchas preguntas) (Tiene que existir la referencia en la BD, no te la autocrea)
                    refTextoQuiz.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DataSnapshot> task) {

                            if (!task.isSuccessful()) {
                                Log.e("firebase error", "Error getting data", task.getException());
                            } else{
                                Log.d("firebase", String.valueOf(task.getResult().getValue()));

                                HashMap<String, String> resultTextoPregunta = (HashMap<String, String>) task.getResult().getValue();

                                if(resultTextoPregunta.get(quiz.getTextId()) == null){
                                    refTextoQuiz.child(quiz.getTextId()).setValue(nuevoID);
                                }else{
                                    String suma = resultTextoPregunta.get(quiz.getTextId());
                                    suma += "," + nuevoID;
                                    refTextoQuiz.child(quiz.getTextId()).setValue(suma);
                                }
                            }
                        }
                    });

                }

            }
        });

    }

    public static void readAllQuizByText(String idTexto, QuizListener qL){

        DatabaseReference refQuiz = FirebaseDAL.dataBase.child("TextoPregunta");

        refQuiz.child(idTexto).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                if (!task.isSuccessful()) {
                    Log.e("firebase error", "Error getting data", task.getException());
                } else {
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));

                    if (String.valueOf(task.getResult().getValue()) == "null") { //Cuando falla la busqueda en la BD, porque no existe ningún Quiz con esa id, te devuelve un null.
                        qL.onQuizReadQuizByTextId(null);
                    } else {

                        //Java interpreta lo recibido como un HashMap, unicamente hay que parsearlo por claves.
                        String result = (String) task.getResult().getValue();

                        List<String> resultArray = Arrays.asList(result.split(","));

                        qL.onQuizReadQuizByTextId(resultArray);

                    }

                }
            }
        });

    }
}
