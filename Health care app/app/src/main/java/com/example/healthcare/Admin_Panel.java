package com.example.healthcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Admin_Panel extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_panel);

        Button view_appointment_list = findViewById(R.id.appointment_list_button);
        Button patient_record = findViewById(R.id.patient_record_btn);
        Button logoutButton = findViewById(R.id.logout_button); // Assuming you have a logout button with this ID

        view_appointment_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Appoint_List();
            }
        });

        patient_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Patient_Record();
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });
    }

    private void Appoint_List() {
        Intent intent = new Intent(Admin_Panel.this, AppointmentList.class);
        String name = "admin";
        intent.putExtra("admin", name);
        startActivity(intent);
    }

    private void Patient_Record() {
        Intent intent = new Intent(Admin_Panel.this, Patient_List.class);
        startActivity(intent);
    }

    private void logout() {
        Intent intent = new Intent(Admin_Panel.this, StartActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        finish();
    }
}