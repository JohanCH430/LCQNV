package com.example.lascosasquenovemos.model;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;

public interface FirebaseListener {
    void onSucced(Task<DataSnapshot> task);
    void afterReadID(Task<DataSnapshot> task);
    void afterReadList(Task<DataSnapshot> task);
    void onFailure(Exception e);
}
