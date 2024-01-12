package com.example.healthcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp  extends AppCompatActivity {
    FirebaseDatabase db;
    DatabaseReference ref;
    EditText nameEditText;
    EditText emailEditText;
    EditText passwordEditText;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        db = FirebaseDatabase.getInstance();
        ref = db.getReference("Health");

        nameEditText = findViewById(R.id.name_input2);
        emailEditText = findViewById(R.id.email_input);
        passwordEditText = findViewById(R.id.password_input2);

        Button signUpButton = findViewById(R.id.SignUp_button);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });
        }
    private void signUp() {
        String name = nameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        int type = 0; // for users

        User user = new User(name, email, password, type);
        ref.child("Users").child(name).setValue(user);
        String key2 = ref.push().getKey();

    /* User Admin= new User("admin","admin@gmail.com","123",1);
    ref.child("Admin").child(key2).setValue(Admin);*/

        // After successful signup, navigate back to the login page
        startActivity(new Intent(SignUp.this, StartActivity.class));
        finish(); // Close the current activity to prevent going back to it when pressing back
    }

}



