package com.example.lascosasquenovemos.dal;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.lascosasquenovemos.model.Interfaces.TematicaListener;
import com.example.lascosasquenovemos.model.TematicaModelo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class TematicaDAL {

    public static void crearTematica(TematicaModelo tematica, TematicaListener tL) {


        DatabaseReference refTematica = FirebaseDAL.dataBase.child("Tematica");

        refTematica.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                if (!task.isSuccessful()) {
                    Log.e("firebase error", "Error getting data", task.getException());
                } else {
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));

                    refTematica.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DataSnapshot> task) {

                            if (!task.isSuccessful()) {
                                Log.e("firebase error", "Error getting data", task.getException());
                            } else {
                                Log.d("firebase", String.valueOf(task.getResult().getValue()));

                                if (String.valueOf(task.getResult().getValue()) == "null") { //Cuando falla la busqueda en la BD, porque no existe ningún texto con esa id, te devuelve un null.
                                    tL.onTematicaReadSucced(null);
                                } else {
                                    List<String> listaTematicas = new ArrayList<>();

                                    //Java interpreta lo recibido como un HashMap, unicamente hay que parsearlo por claves.
                                    HashMap<String, String> result = (HashMap<String, String>) task.getResult().getValue();

                                    Boolean encontrado = false;

                                    for(String nombreTematicas : result.keySet()){
                                        if(nombreTematicas.equals(tematica.getIDTematica())){
                                            encontrado = true;
                                        }
                                    }

                                    //En el for compruebo que no existe ya el nombre de la temática.
                                    if(!encontrado){
                                        //Añado la temática a la bd
                                        refTematica.child(tematica.getIDTematica()).setValue(tematica.getDescripcion()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                tL.onTematicaWriteSucced(task.isSuccessful());
                                            }
                                        });
                                    } else{
                                        tL.onTematicaWriteSucced(false);
                                    }

                                }

                            }
                        }
                    });


                }

            }
        });
    }

    public static void leerTodoTematica(TematicaListener tL) {

        DatabaseReference refTexto = FirebaseDAL.dataBase.child("Tematica");

        refTexto.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                if (!task.isSuccessful()) {
                    Log.e("firebase error", "Error getting data", task.getException());
                } else {
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));

                    if (String.valueOf(task.getResult().getValue()) == "null") { //Cuando falla la busqueda en la BD, porque no existe ningún texto con esa id, te devuelve un null.
                        tL.onTematicaReadAllSucced(null);
                    } else {
                        String[] listaTematicas;
                        //Java interpreta lo recibido como un HashMap, unicamente hay que parsearlo por claves.
                        HashMap<String, String> result = (HashMap<String, String>) task.getResult().getValue();
                        listaTematicas = result.keySet().toArray(new String[result.size()]);
                        tL.onTematicaReadAllSucced(Arrays.asList(listaTematicas));
                    }

                }
            }
        });

    }
}
