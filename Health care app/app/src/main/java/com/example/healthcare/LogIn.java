package com.example.healthcare;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LogIn  extends AppCompatActivity {
    EditText nameEditText;
    EditText passwordEditText;
    FirebaseDatabase db;
    DatabaseReference UserRef;
    DatabaseReference AdminRef;
    Boolean Success=false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);

        nameEditText = findViewById(R.id.name_input);
        passwordEditText = findViewById(R.id.password_input);

        Button LogIn_Button = findViewById(R.id.LogIn_button);
        LogIn_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String name=nameEditText.getText().toString();
               String password=passwordEditText.getText().toString();
                //Check if login details match
                db = FirebaseDatabase.getInstance();
             /*   db.setPersistenceEnabled(true);//offline storage*/
                AdminRef = db.getReference("Health").child("Admin");
                UserRef = db.getReference("Health").child("Users");


                //Check if User
                UserRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                                String userName = userSnapshot.child("name").getValue(String.class);
                                String userPassword = userSnapshot.child("password").getValue(String.class);

                                // Logging statements
                                Log.d("Firebase", "User: Name = " + userName + ", Password = " + userPassword);
                                Log.d("Firebase", "Input: Name = " + name + ", Password = " + password);

                                if (name.equals(userName) && password.equals(userPassword)) {
                                    Toast.makeText(getApplicationContext(), "Login successful,User", Toast.LENGTH_SHORT).show();
                                    Success=true;
                                    Intent intent = new Intent(LogIn.this, User_Panel.class);
                                    intent.putExtra("user",name);
                                    startActivity(intent);
                                    System.out.println("User here");
                                    break;
                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Handle the error case
                    }
                });

                //Check if admin

                AdminRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            for (DataSnapshot adminSnapshot : dataSnapshot.getChildren()) {
                                String adminName = adminSnapshot.child("name").getValue(String.class);
                                String adminPassword = adminSnapshot.child("password").getValue(String.class);

                                if (name.equals(adminName) && password.equals(adminPassword)) {
                                    Toast.makeText(getApplicationContext(), "Login successful,Admin", Toast.LENGTH_SHORT).show();
                                    Success=true;
                                    Intent intent = new Intent(LogIn.this, Admin_Panel.class);
                                    startActivity(intent);
                                    System.out.println("Admin here");
                                    break;
                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Handle the error case
                    }
                });
            }
        });
    }
}
