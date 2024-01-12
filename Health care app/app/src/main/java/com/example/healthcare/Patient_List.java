package com.example.healthcare;

import android.annotation.SuppressLint;
import android.content.Context;
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

public class Patient_List extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    ArrayList<Patient> dataSet = new ArrayList<Patient>();
    FirebaseDatabase db;
    DatabaseReference ref;
    private Context mContext;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_record_list);
        TextView patient_records_title = findViewById(R.id.patient_records);
        mContext = this;

        recyclerView = findViewById(R.id.patient_list);
        layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new record_Adapter(dataSet, mContext);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);

        db = FirebaseDatabase.getInstance();
        ref = db.getReference("Health");


        DatabaseReference appointRef = db.getReference("Health").child("Appointment");
        appointRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dataSet.clear();
                for (DataSnapshot appointmentSnapshot : dataSnapshot.getChildren()) {
                    String appointmentName = appointmentSnapshot.getKey();
                    // Create a new Patient object using the appointment name as the patient's name
                    Patient patient = new Patient();
                    patient.setName(appointmentName);
                    dataSet.add(patient);
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


