package com.example.lascosasquenovemos.dal;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.lascosasquenovemos.model.Interfaces.PartidaListener;
import com.example.lascosasquenovemos.model.PantallaModelo;
import com.example.lascosasquenovemos.model.PartidaModelo;
import com.example.lascosasquenovemos.model.QuizModelo;
import com.example.lascosasquenovemos.model.TextoModelo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

import java.util.HashMap;

public class PartidaDAL {

    public static void crearPartida(PartidaModelo partida, PartidaListener pL) {

        DatabaseReference refPartida = FirebaseDAL.dataBase.child("Partida");
        DatabaseReference refiDPartida = FirebaseDAL.dataBase.child("IDPartida");


        refiDPartida.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase error", "Error getting data", task.getException());
                } else {
                    //Creo un nuevo ID que es el siguiente al que hay guardado.
                    String[] datos = String.valueOf(task.getResult().getValue()).split("-");
                    int aux = Integer.parseInt(datos[1]) + 1;
                    String nuevoID = datos[0] + "-" + String.valueOf(aux);

                    DatabaseReference refPartidaActual = refPartida.child(nuevoID);

                    //Se guarda el nuevo Id en la BD
                    refiDPartida.setValue(nuevoID).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("firebase", e.getLocalizedMessage());
                        }
                    });

                    //Declaración de mapas
                    HashMap<String, Object> partidaMap = new HashMap<String, Object>();
                    HashMap<String, Object> pantallaMap = new HashMap<String, Object>();
                    HashMap<String, Object> textoMap = new HashMap<String, Object>();
                    HashMap<String, Object> preguntaMap = new HashMap<String, Object>();
                    HashMap<String, String> opt = new HashMap<String, String>();

                    //Crear mapa de texto
                    textoMap.put("Contenido", partida.getPantallasPartida().get(0).getTexto().getTexto());
                    textoMap.put("Titulo", partida.getPantallasPartida().get(0).getTexto().getTítulo());
                    textoMap.put("Tematica", partida.getPantallasPartida().get(0).getTexto().getTemática());

                    //Crear mapa de pregunta
                    //Opciones de pregunta
                    opt.put("a", partida.getPantallasPartida().get(0).getQuiz().getOpcionA());
                    opt.put("b", partida.getPantallasPartida().get(0).getQuiz().getOpcionB());
                    opt.put("c", partida.getPantallasPartida().get(0).getQuiz().getOpcionC());
                    opt.put("d", partida.getPantallasPartida().get(0).getQuiz().getOpcionD());

                    //Valores generales de pregunta
                    preguntaMap.put("Opciones", opt);
                    preguntaMap.put("Pregunta", partida.getPantallasPartida().get(0).getQuiz().getPregunta());
                    preguntaMap.put("Solucion", partida.getPantallasPartida().get(0).getQuiz().getSolucion());
                    preguntaMap.put("TextId", partida.getPantallasPartida().get(0).getQuiz().getTextId());

                    //Guardamos texto y pregunta dentro de pantalla
                    pantallaMap.put("Texto", textoMap);
                    pantallaMap.put("Pregunta", preguntaMap);

                    //Guardamos pantalla dentro de partida
                    partidaMap.put("P0", pantallaMap);

                    refPartida.child(nuevoID).setValue(partidaMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (!task.isSuccessful()) {
                                Log.e("firebase error", "Error getting data", task.getException());
                                pL.onPartidaWriteSuccess(null, false);
                            } else {
                                int i=1;

                                while(i<partida.getPantallasPartida().size()){
                                    //Crear mapa de texto
                                    textoMap.put("Contenido", partida.getPantallasPartida().get(i).getTexto().getTexto());
                                    textoMap.put("Titulo", partida.getPantallasPartida().get(i).getTexto().getTítulo());
                                    textoMap.put("Tematica", partida.getPantallasPartida().get(i).getTexto().getTemática());

                                    //Crear mapa de pregunta
                                    //Opciones de pregunta
                                    opt.put("a", partida.getPantallasPartida().get(i).getQuiz().getOpcionA());
                                    opt.put("b", partida.getPantallasPartida().get(i).getQuiz().getOpcionB());
                                    opt.put("c", partida.getPantallasPartida().get(i).getQuiz().getOpcionC());
                                    opt.put("d", partida.getPantallasPartida().get(i).getQuiz().getOpcionD());

                                    //Valores generales de pregunta
                                    preguntaMap.put("Opciones", opt);
                                    preguntaMap.put("Pregunta", partida.getPantallasPartida().get(i).getQuiz().getPregunta());
                                    preguntaMap.put("Solucion", partida.getPantallasPartida().get(i).getQuiz().getSolucion());
                                    preguntaMap.put("TextId", partida.getPantallasPartida().get(i).getQuiz().getTextId());

                                    //Guardamos texto y pregunta dentro de pantalla
                                    pantallaMap.put("Texto", textoMap);
                                    pantallaMap.put("Pregunta", preguntaMap);

                                    refPartidaActual.child("P" + i).setValue(pantallaMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (!task.isSuccessful()) {
                                                Log.e("firebase error", "Error getting data", task.getException());
                                                pL.onPartidaWriteSuccess(null, false);
                                            }
                                        }
                                    });

                                    i++;
                                }

                                pL.onPartidaWriteSuccess(nuevoID, true);
                            }
                        }
                    });
                }
            }
        });




    }

    public static void leerPartida(String idPartida, PartidaListener pL) {
        DatabaseReference refPartida = FirebaseDAL.dataBase.child("Partida");

        refPartida.child(idPartida).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase error", "Error getting data", task.getException());
                } else {
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));

                    if (String.valueOf(task.getResult().getValue()) == "null") {
                        pL.onPartidaReadSuccess(null);
                    } else {

                        int i = 0;

                        HashMap<String, Object> result = (HashMap<String, Object>) task.getResult().getValue();
                        HashMap<Integer, PantallaModelo> pantallas = new HashMap<Integer, PantallaModelo>();

                        for (String pantalla : result.keySet()) {
                            HashMap<String, Object> pantallaAux = (HashMap<String, Object>) result.get(pantalla);
                            HashMap<String, Object> textoAux = (HashMap<String, Object>) pantallaAux.get("Texto");
                            HashMap<String, Object> preguntaAux = (HashMap<String, Object>) pantallaAux.get("Pregunta");

                            //Crear pregunta
                            String pregunta = (String) preguntaAux.get("Pregunta");
                            String solucion = (String) preguntaAux.get("Solucion");
                            String textId = (String) preguntaAux.get("TextId");

                            HashMap<String, String> opciones = (HashMap<String, String>) preguntaAux.get("Opciones");

                            String oA = opciones.get("a");
                            String oB = opciones.get("b");
                            String oC = opciones.get("c");
                            String oD = opciones.get("d");

                            QuizModelo qmAux = new QuizModelo(pregunta, oA, oB, oC, oD, solucion, textId);

                            //Crear texto
                            String titulo = (String) textoAux.get("Titulo");
                            String contenido = (String) textoAux.get("Contenido");

                            TextoModelo tmAux = new TextoModelo(textId, titulo, contenido, "");

                            //Añade texto modelo y pregunta modelo al mapa de pantallas auxiliar
                            PantallaModelo pm = new PantallaModelo(tmAux, qmAux);

                            pantallas.put(i, pm);

                            i++;
                        }
                        pL.onPartidaReadSuccess(new PartidaModelo(idPartida, pantallas));
                    }
                }
            }
        });
    }
}