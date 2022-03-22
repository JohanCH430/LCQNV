package com.example.lascosasquenovemos.dal;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.lascosasquenovemos.model.FirebaseListener;
import com.example.lascosasquenovemos.model.TextoModelo;
import com.example.lascosasquenovemos.view.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class TextoDAL implements FirebaseListener {

    private DatabaseReference DataBase;
    private DatabaseReference refTextos;
    private DatabaseReference refTematicaTexto;
    private Context appContext;
    private TextoModelo texto;

    //Constructor con un parámetro para que se pase el contexto de la aplicación que es necesario para usar la base de datos
    public TextoDAL(TextoModelo texto, Context application_context){
        //Se inicializa el contexto
        appContext = application_context;
        //Se obtiene la instancia de la base de datos con el link de firebase
        FirebaseDatabase DataBaseInstance = FirebaseDatabase.getInstance(appContext.getString(R.string.firebase_realtime_database_URL));
        //Se pone la persistencia a "true" para que los cambios se guarden
        DataBaseInstance.setPersistenceEnabled(true);
        DataBase = DataBaseInstance.getReference();
        refTextos = DataBase.child("Texto");
        refTematicaTexto = DataBase.child("TematicaTexto");
        this.texto = texto;
    }

    //Método utilizado para crear un nuevo texto en la base de datos
    public int crearTexto(String id){
        //Ha habido un error al generar el id y por tanto no se puede acceder a la base de datos
        if (id == "") return -1;
        texto.setIDTexto(id);

        //Este hijo es el que se va a usar en firebase para la creacion de textos ya que el conteindo cuelga del id
        DatabaseReference refId = refTextos.child(texto.getIDTexto());

        //Solo se hace la segunda escritura si la primera no ha dado fallos
        //Acceso a la base de datos para escribir el valor en la tabla de Texto
        refId.child("Contenido").setValue(texto.getTexto()).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("firebase", e.getLocalizedMessage());
                }
        });
        refId.child("Titulo").setValue(texto.getTítulo()).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("firebase", e.getLocalizedMessage());
                }
        });

        //Llamada a la funcion encargada de la generacion de los datos en la tabla TematicaTexto
        obtainList();

        //Si ha fallado en las siguientes inserciones e borra todo lo hecho anteriormente en la base de datos y se devuelve -1 indicando que ha habido un error
        return 0; //El método ha tenido éxito
    }



    private String crearArrayTemáticaTexto(String idTematica, String idTitulo){
        List listaTextos = new ArrayList();
        //Se añade el nuevo texto a la lista de temáticas asociadas
        listaTextos.add(idTitulo);
        return listaTextos.toString();
    }

    public void obtainId(){

        //Lectura del ultimo id usado para los textos
        DataBase.child("IDTexto").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase error", "Error getting data", task.getException());
                }
                else {
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));

                    if(String.valueOf(task.getResult().getValue()) == "null"){

                    }

                    else{
                        //Si la tarea se ha hecho con exito se llama a onSucced para que redirija el código
                        afterReadID(task);
                    }

                }
            }
        });

    }

    public void obtainList(){

        //Lectura del ultimo id usado para los textos
        refTematicaTexto.child(texto.getTemática()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase error", "Error getting data", task.getException());
                }
                else {
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));

                    if(String.valueOf(task.getResult().getValue()) == "null"){

                    }

                    else{
                        //Si la tarea se ha hecho con exito se llama a onSucced para que redirija el código
                        afterReadList(task);
                    }

                }
            }
        });

    }

    @Override
    public void onSucced(Task<DataSnapshot> task) {

    }

    @Override
    public void afterReadID(Task<DataSnapshot> task) {
        //Se crea un nuevo id añadiendole 1 al valor original
        String[] datos = String.valueOf(task.getResult().getValue()).split("-");
        int aux = Integer.parseInt(datos[1 ]) + 1;
        String nuevoID = datos[0] + "-" + String.valueOf(aux);

        //String nuevoID = "aaaa";
        //Se escribe el nuevo valor en la BD
        DataBase.child("IDTexto").setValue(nuevoID).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("firebase", e.getLocalizedMessage());
            }
        });

        crearTexto(nuevoID);

    }

    @Override
    public void afterReadList(Task<DataSnapshot> task) {
        String[] ArrayTematicaTexto = String.valueOf(task.getResult().getValue()).split("]");
        //Se crea el array con el nuevo texto
        String listaCompleta = ArrayTematicaTexto[0] + "," + texto.getIDTexto() + "]";
        //Acceso a la base de datos para escribir el valor en la tabla de Tematicatexto
        refTematicaTexto.child(texto.getTemática()).setValue(listaCompleta).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("firebase", e.getLocalizedMessage());
            }
        });
    }

    @Override
    public void onFailure(Exception e) {

    }

}
