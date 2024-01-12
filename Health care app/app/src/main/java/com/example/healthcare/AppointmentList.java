package com.example.healthcare;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AppointmentList extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<Patient> dataSet = new ArrayList<Patient>();
    FirebaseDatabase db;
    DatabaseReference ref;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_appointment_list);
        recyclerView = findViewById(R.id.view_list);
        layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new appointment_Adapter(dataSet);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);

        db = FirebaseDatabase.getInstance();
        ref = db.getReference("Health");

        String name = getIntent().getStringExtra("user");
        String admin = getIntent().getStringExtra("admin");


        if (name != null) {

            TextView Appointment_name = (findViewById(R.id.user_list));
            Appointment_name.setText("Patient name: " + name);

            // Retrieve Appointments
            DatabaseReference Appoint_ref = db.getReference("Health").child("Appointment").child(name);

            Appoint_ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    dataSet.clear();
                    for (DataSnapshot messageSnapshot : dataSnapshot.getChildren()) {
                        Patient patient = messageSnapshot.getValue(Patient.class);
                        if (patient != null) {
                            String name = messageSnapshot.child("name").getValue(String.class);
                            String appointment_num = messageSnapshot.child("appointment_num").getValue(String.class);
                            String illness = messageSnapshot.child("illness").getValue(String.class);
                            String age = messageSnapshot.child("age").getValue(String.class);
                            String time = messageSnapshot.child("time").getValue(String.class);

                            patient.setName(name);
                            patient.setAppointment_num(appointment_num);
                            patient.setAge(age);
                            patient.setIllness(illness);
                            patient.setTime(time);

                            dataSet.add(patient);
                        }
                    }
                    mAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    // Handle onCancelled event
                }
            });

        }

        if (admin != null) {
            DatabaseReference appointRef = db.getReference("Health").child("Appointment");

            appointRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    dataSet.clear();
                    for (DataSnapshot appointmentSnapshot : dataSnapshot.getChildren()) {
                        for (DataSnapshot patientSnapshot : appointmentSnapshot.getChildren()) {
                            Patient patient = patientSnapshot.getValue(Patient.class);
                            if (patient != null) {
                                String name = patientSnapshot.child("name").getValue(String.class);
                                String appointmentNum = patientSnapshot.child("appointment_num").getValue(String.class);
                                String illness = patientSnapshot.child("illness").getValue(String.class);
                                String age = patientSnapshot.child("age").getValue(String.class);
                                String time = patientSnapshot.child("time").getValue(String.class);

                                patient.setName(name);
                                patient.setAppointment_num(appointmentNum);
                                patient.setAge(age);
                                patient.setIllness(illness);
                                patient.setTime(time);

                                dataSet.add(patient);
                            }
                        }
                    }
                    mAdapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    // Handle onCancelled event
                }
            });
        }
    }
}
