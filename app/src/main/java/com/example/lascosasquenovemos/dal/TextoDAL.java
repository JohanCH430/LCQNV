package com.example.lascosasquenovemos.dal;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.lascosasquenovemos.model.Interfaces.TextListener;
import com.example.lascosasquenovemos.model.TextoModelo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class TextoDAL extends FirebaseDAL {

    private DatabaseReference DataBase;
    private DatabaseReference refTextos;
    private DatabaseReference refTematicaTexto;
    private Context appContext;
    private TextoModelo texto;


///////////////////////////////////////////////////////FUNCIONES PARA ESCRITURA DE UN NUEVO TEXTO EN BD////////////////////////////////////////////////////////////

    public static void creartexto(TextoModelo texto, TextListener tL){

        DatabaseReference refTextoID = FirebaseDAL.dataBase.child("IDTexto");
        DatabaseReference refTexto = FirebaseDAL.dataBase.child("Texto");
        DatabaseReference refTematicaTexto = FirebaseDAL.dataBase.child("TematicaTexto");

        refTextoID.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                if (!task.isSuccessful()) {
                    Log.e("firebase error", "Error getting data", task.getException());
                } else {
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));

                    //Creo un nuevo ID que es el siguiente al que hay guardado.
                    String[] datos = String.valueOf(task.getResult().getValue()).split("-");
                    int aux = Integer.parseInt(datos[1]) + 1;
                    String nuevoID = datos[0] + "-" + String.valueOf(aux);

                    //Actualizado el valor con el nuevo ID.
                    refTextoID.setValue(nuevoID).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("firebase", e.getLocalizedMessage());
                        }
                    });

                    HashMap<String, Object> textoMap = new HashMap<String, Object>();
                    HashMap<String, String> opt = new HashMap<String, String>();

                    textoMap.put("Contenido", texto.getTexto());
                    textoMap.put("Titulo", texto.getT??tulo());
                    textoMap.put("Tematica", texto.getTem??tica());

                    //A??ado el mapa a la bd
                    refTexto.child(nuevoID).setValue(textoMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            tL.onTextWriteSucced(task.isSuccessful());
                        }
                    });


                    refTematicaTexto.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DataSnapshot> task) {

                            if (!task.isSuccessful()) {
                                Log.e("firebase error", "Error getting data", task.getException());
                            } else{
                                Log.d("firebase", String.valueOf(task.getResult().getValue()));

                                HashMap<String, String> resultTextoPregunta = (HashMap<String, String>) task.getResult().getValue();

                                if(resultTextoPregunta.get(texto.getTem??tica()) == null){
                                    refTematicaTexto.child(texto.getTem??tica()).setValue(nuevoID);
                                }else{
                                    String suma = resultTextoPregunta.get(texto.getTem??tica());
                                    suma += "," + nuevoID;
                                    refTematicaTexto.child(texto.getTem??tica()).setValue(suma);
                                }
                            }
                        }
                    });

                }

            }
        });

    }



/////////////////////////////////////////////////////////////////////// FIN DE ESCRITURA EN BD /////////////////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////////// LECTURA DE UN TEXTO EN BD /////////////////////////////////////////////////////////////////////

    public static void leerTexto(String id, TextListener tL) {

        DatabaseReference refTexto = FirebaseDAL.dataBase.child("Texto");

        refTexto.child(id).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                if (!task.isSuccessful()) {
                    Log.e("firebase error", "Error getting data", task.getException());
                } else {
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));

                    if (String.valueOf(task.getResult().getValue()) == "null") { //Cuando falla la busqueda en la BD, porque no existe ning??n texto con esa id, te devuelve un null.
                        tL.onTextReadSucced(null);
                    } else {

                        //Java interpreta lo recibido como un HashMap, unicamente hay que parsearlo por claves.
                        HashMap<String, Object> result = (HashMap<String, Object>) task.getResult().getValue();

                        String titulo = (String) result.get("Titulo");
                        String contenido = (String) result.get("Contenido");
                        //TODO poner tem??tica en la tabla de firebase??
                        tL.onTextReadSucced(new TextoModelo(id, titulo, contenido, ""));

                    }

                }
            }
        });

    }
    public static void leerTodoTexto(TextListener tL) {

        DatabaseReference refTexto = FirebaseDAL.dataBase.child("Texto");

        FirebaseDAL.dataBase.child("Texto").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                if (!task.isSuccessful()) {
                    Log.e("firebase error", "Error getting data", task.getException());
                } else {
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));

                    if (String.valueOf(task.getResult().getValue()) == "null") { //Cuando falla la busqueda en la BD, porque no existe ning??n texto con esa id, te devuelve un null.
                        tL.onTextReadSucced(null);
                    } else {
                        List<String> listaTextos = new ArrayList<>();
                        //Java interpreta lo recibido como un HashMap, unicamente hay que parsearlo por claves.
                        HashMap<String, Object> result = (HashMap<String, Object>) task.getResult().getValue();

                        for (String key: result.keySet()){
                            HashMap<String, Object> resultadoInterior = (HashMap<String, Object>) result.get(key);
                            listaTextos.add( key + ": " + (String) resultadoInterior.get("Titulo"));
                        }

                        tL.onTextReadAllSucced(listaTextos);

                    }

                }
            }
        });

    }

    public static void leerTodoTextoTematica(TextListener tL) {

        DatabaseReference refTexto = FirebaseDAL.dataBase.child("TematicaTexto");

        FirebaseDAL.dataBase.child("TematicaTexto").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                if (!task.isSuccessful()) {
                    Log.e("firebase error", "Error getting data", task.getException());
                } else {
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));

                    if (String.valueOf(task.getResult().getValue()) == "null") { //Cuando falla la busqueda en la BD, porque no existe ning??n texto con esa id, te devuelve un null.
                        tL.onTextReadSucced(null);
                    } else {
                        HashMap<String, List<String>> listaTextos = new HashMap<>();
                        //Java interpreta lo recibido como un HashMap, unicamente hay que parsearlo por claves.
                        HashMap<String, Object> result = (HashMap<String, Object>) task.getResult().getValue();

                        for (String key: result.keySet()){
                            String textos = (String) result.get(key);
                            listaTextos.put(key,Arrays.asList(textos.split(",")));
                        }

                        tL.onTextosTematicasReadAllSucceed(listaTextos);

                    }

                }
            }
        });

    }

/////////////////////////////////////////////////////////////////////// FIN LECTURA EN BD //////////////////////////////////////////////////////////////////////////////


}
