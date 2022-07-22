package com.example.groupproject;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;
import java.util.HashMap;


public class Users {

    String name;
    String id,email;
    String currentTime;
    LatLng latLng;


    public Users(){

    }

    public Users(String name, String id, String email,String currentTime, LatLng latLng) {
        this.name = name;
        this.id = id;
        this.email = email;
        this.currentTime = currentTime;
        this.latLng = latLng;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public String getEmail() {return  email;}

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

}
