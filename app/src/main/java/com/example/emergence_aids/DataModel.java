package com.example.emergence_aids;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class DataModel {

    static public ArrayList<Patient> patients = new ArrayList<>();

    public static void save(){
        FirebaseDatabase database;
        DatabaseReference dbRef;
        database = FirebaseDatabase.getInstance("https://emergenceadmin-default-rtdb.firebaseio.com/");
        dbRef = database.getReference("patients");

        dbRef.setValue(DataModel.patients);
    }
}
