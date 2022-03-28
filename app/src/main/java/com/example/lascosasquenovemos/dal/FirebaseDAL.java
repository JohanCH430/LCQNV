package com.example.lascosasquenovemos.dal;

import android.content.Context;
import com.example.lascosasquenovemos.view.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseDAL {

    protected static DatabaseReference dataBase = null;

    public FirebaseDAL(){
    }

    public FirebaseDAL(Context context){
        //Se obtiene la instancia de la base de datos con el link de firebase
        FirebaseDatabase DataBaseInstance = FirebaseDatabase.getInstance(context.getString(R.string.firebase_realtime_database_URL));
        DataBaseInstance.setPersistenceEnabled(true);

        dataBase = DataBaseInstance.getReference();
    }

    public static DatabaseReference getInstance(Context context){
        if (dataBase == null) {
           new FirebaseDAL(context);
        }
        return dataBase;
    }
}
