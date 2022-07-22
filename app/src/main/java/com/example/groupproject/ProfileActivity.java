package com.example.groupproject;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseUser user ;
    DatabaseReference databaseUsers;

    TextView Name;
    String names;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        databaseUsers = FirebaseDatabase.getInstance("https://group-project-ict650-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Users");

        auth = FirebaseAuth.getInstance();

        String email = auth.getCurrentUser().getEmail();
        Name = (TextView)findViewById(R.id.name);
        databaseUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot keyID : snapshot.getChildren()){
                    if(keyID.child("email").getValue().equals(email)){
                        names = keyID.child("name").getValue().toString();
                        Name.setText("Welcome "+names);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),"error occured",Toast.LENGTH_LONG);
            }
        });

    }

    public void aboutUs(View v){
        Intent i = new Intent("android.intent.action.VIEW", Uri.parse("https://muhdimman.github.io/Hospital-project-/aboutus.html"));
        startActivity(i);
    }

    public void viewMap(View v){
        Intent i = new Intent(this, MapsActivity.class);
        startActivity(i);
    }
    public void signout(View v){
        auth.signOut();
        finish();
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);

    }
}