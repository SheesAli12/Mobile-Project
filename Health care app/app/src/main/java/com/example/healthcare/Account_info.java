package com.example.healthcare;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Account_info extends AppCompatActivity {

    FirebaseDatabase db;
    DatabaseReference ref;

    TextView Name;
    TextView Email;
    TextView Password;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_info);
        db = FirebaseDatabase.getInstance();
        ref = db.getReference("Health");

        String name = getIntent().getStringExtra("user");
        Name = findViewById(R.id.name_info);
        Email = findViewById(R.id.Email_info);
        Password = findViewById(R.id.password_info);



        DatabaseReference Info = db.getReference("Health").child("Users").child(name);

        Info.addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String name = dataSnapshot.child("name").getValue(String.class);
                    String email = dataSnapshot.child("email").getValue(String.class);
                    String password = dataSnapshot.child("password").getValue(String.class);

                    Name.setText("Name: " + name);
                    Email.setText("Email: " + email);
                    Password.setText("Password: " + password);
                } else {
                    // Handle when the data for the given user doesn't exist

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle onCancelled event
            }
        });
    }
}


