package com.example.lascosasquenovemos.dal;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.lascosasquenovemos.view.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TextoDAL {

    private DatabaseReference DataBase;
    private Context appContext;

    //Constructor con un parámetro para que se pase el contexto de la aplicación que es necesario para usar la base de datos
    public TextoDAL(Context application_context){
        //Se inicializa el contexto
        appContext = application_context;
        //Se obtiene la instancia de la base de datos con el link de firebase
        FirebaseDatabase DataBaseInstance = FirebaseDatabase.getInstance(appContext.getString(R.string.firebase_realtime_database_URL));
        //Se pone la persistencia a "true" para que los cambios se guarden
        DataBaseInstance.setPersistenceEnabled(true);
        DataBase = DataBaseInstance.getReference();
    }

    //Método utilizado para crear un nuevo texto en la base de datos
    public int crearTexto(){
        //Atributo local que se pondrá a true en caso de que haya algún error al insertar en la base de datos
        final boolean[] hasFailed = {false};
        //TODO cambiar acceso a BD (FALTAN FUNCIONES DEL TRANSFER)
        //TODO hacer lectura a la BD para extraer el ultimo id usado (?)
        String JSONTexto = crearJSONTexto("1", "1", "1");
        String JSONTematicaTexto = crearJSONTemáticaTexto("1", "1", new ArrayList<String>());

        //Acceso a la base de datos para escribir el valor en la tabla de Tematicatexto
        DataBase.child("TematicaTexto").setValue(JSONTematicaTexto).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("firebase", e.getLocalizedMessage());
                hasFailed[0] = true;
            }
        });

        //Solo se hace la segunda escritura si la primera no ha dado fallos
        if (hasFailed[0] == false) {
            //Acceso a la base de datos para escribir el valor en la tabla de Texto
            DataBase.child("Texto").setValue(JSONTexto).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d("firebase", e.getLocalizedMessage());
                    hasFailed[0] = true;
                }
            });
        }

        if (hasFailed[0] == true) return -1; //Ha fallado el método al acceder a la base de datos
        else return 0; //El método ha tenido éxito
    }

    private String crearJSONTexto(String idTexto, String contenido, String titulo){
        //Objeto JSON con el id y anidado con otro JSON con los datos
        JSONObject object1 = new JSONObject();
        //Objeto JSON con los datos del texto
        JSONObject object2 = new JSONObject();

        try {
            //Se añade al JSON de datos los valores de titulo y contenido
            object2.put("Titulo", titulo);
            object2.put("Contenido", contenido);
            //Se añade al JSON final el JSON de los datos junto con el id del texto
            object1.put(idTexto,object2);
        }
        catch (Exception exception){
            //Se añade al log el error que ha podido dar la creación del JSON
            Log.d("CreacionJSONTexto", exception.getLocalizedMessage());
        }

        return object1.toString();
    }

    private String crearJSONTemáticaTexto(String idTematica, String idTitulo, List<String> listaTextos){
        JSONObject object = new JSONObject();

        //Se añade el nuevo texto a la lista de temáticas asociadas
        listaTextos.add(idTitulo);
        try {
            object.put(idTematica, listaTextos);
        }
        catch (Exception exception){
            //Se añade al log el error que ha podido dar la creación del JSON
            Log.d("CreacionJSONTematTexto", exception.getLocalizedMessage());
        }

        return object.toString();
    }


}
