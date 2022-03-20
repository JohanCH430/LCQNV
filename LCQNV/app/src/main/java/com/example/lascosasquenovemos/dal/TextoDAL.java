package com.example.lascosasquenovemos.dal;

import android.content.Context;

import com.example.lascosasquenovemos.view.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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

        //TODO cambiar acceso a BD
        /*
        DataBase.child("Texto").setValue("hola").addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("firebase", e.getLocalizedMessage());
                hasFailed[0] = true;
            }
        });*/

        if (hasFailed[0] == true) return -1; //Ha fallado el método al acceder a la base de datos
        else return 0; //El método ha tenido éxito
    }
}
