package com.example.healthcare;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BookAppointment extends AppCompatActivity {

    FirebaseDatabase db;
    DatabaseReference ref;
    EditText AgeEditText ;
    EditText illnessEditText ;
    EditText timeEditText;

    EditText appointmentEditText;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_appointment_screen);
        String name= getIntent().getStringExtra("user");
        TextView user=findViewById(R.id.user__appoint);
        user.setText(name);

        db = FirebaseDatabase.getInstance();
        ref = db.getReference("Health");

        AgeEditText = findViewById(R.id.age_input);
        illnessEditText = findViewById(R.id.illness_input);
        timeEditText = findViewById(R.id.time_input);
        appointmentEditText=findViewById(R.id.appointment_number);

        Button signUpButton = findViewById(R.id.appoint_button);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Appoint();
            }
        });
    }


    private void Appoint() {
        String age = AgeEditText.getText().toString();
        String illness = illnessEditText.getText().toString();
        String time = timeEditText.getText().toString();
        String appoint=appointmentEditText.getText().toString();
        String name= getIntent().getStringExtra("user");

        Patient user = new Patient(name,age,illness,time ,appoint);
        ref.child("Appointment").child(name).child(appoint).setValue(user);
    }

}


