package com.example.lascosasquenovemos.dal;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.lascosasquenovemos.model.Interfaces.AdminLoginListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;


public class AdminLoginDAL extends FirebaseDAL{

    public static void leerContraseña(String pw, AdminLoginListener AL) {
        DatabaseReference admin = FirebaseDAL.dataBase.child("Admin");
        admin.child(pw).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                if (!task.isSuccessful()) {
                    Log.e("firebase error", "Error getting data", task.getException());
                } else {
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));

                    if (String.valueOf(task.getResult().getValue()) == "null") { //Cuando falla la busqueda en la BD, porque no existe ningún pw con esa id, te devuelve un null.
                        AL.onAdminLoginReadSuccess(null);
                    } else {
                        AL.onAdminLoginReadSuccess(String.valueOf(task.getResult().getValue()));
                    }

                }
            }
        });
    }
}
