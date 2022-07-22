package com.example.groupproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class RegisterActivity extends AppCompatActivity {

    EditText email, pass, name;
    FirebaseAuth mAuth;
    MapsActivity coordinates = new MapsActivity();
    DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm");
    DatabaseReference databaseUsers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        FirebaseApp.initializeApp(this);

        databaseUsers = FirebaseDatabase.getInstance("https://group-project-ict650-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Users");
        email = (EditText)findViewById(R.id.email);
        pass = (EditText)findViewById(R.id.pass);
        name = (EditText)findViewById(R.id.name);

        mAuth = FirebaseAuth.getInstance();

    }

    private void addUsers(){

        String names = name.getText().toString().trim();
        String emails = email.getText().toString().trim();
        String date = df.format(Calendar.getInstance().getTime());

        if(!TextUtils.isEmpty(names)){
            String id = databaseUsers.push().getKey();
            LatLng latLng = new LatLng(0,0);
            Users users = new Users(names,id,emails,date,latLng);
            databaseUsers.child(id).setValue(users);
        }
    }

    public void createUser(View v){
        if(email.getText().toString().equals("") && pass.getText().toString().equals("") && name.getText().toString().equals(""))
        {
            Toast.makeText(getApplicationContext(),"Blank not allowed", Toast.LENGTH_SHORT).show();
        }else{
            String emails = email.getText().toString();
            String password = pass.getText().toString();

            addUsers();
            mAuth.createUserWithEmailAndPassword(emails,password)
                    .addOnCompleteListener(task -> {
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"User created successfully",Toast.LENGTH_SHORT).show();
                            finish();
                            Intent i = new Intent(getApplicationContext(),ProfileActivity.class);
                            startActivity(i);
                        }else{
                            Toast.makeText(getApplicationContext(),"User could not be found",Toast.LENGTH_SHORT).show();
                        }
                    });

        }
    }






}