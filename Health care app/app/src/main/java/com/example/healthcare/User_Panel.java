// User_Panel.java
package com.example.healthcare;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class User_Panel extends AppCompatActivity {

    AdView adView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_panel);

        String name = getIntent().getStringExtra("user");
        TextView user = findViewById(R.id.user_name);
        user.setText(name);

        Button Book_Appointment = findViewById(R.id.Book_Appointment);
        Button View_Appointment = findViewById(R.id.patient_record_btn);
        Button Account_info = findViewById(R.id.account_info_button);
        Button view_health = findViewById(R.id.health_care);
        Button logoutButton = findViewById(R.id.logout_button); // Assuming you have a logout button with this ID

        Book_Appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book();
            }
        });

        View_Appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View_Appoint();
            }
        });

        Account_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AccountInfo();
            }
        });

        view_health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Benefits();
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });
    }

    private void Book() {
        Intent intent = new Intent(User_Panel.this, BookAppointment.class);
        String name = getIntent().getStringExtra("user");
        intent.putExtra("user", name);
        startActivity(intent);
    }

    private void View_Appoint() {
        Intent intent = new Intent(User_Panel.this, AppointmentList.class);
        String name = getIntent().getStringExtra("user");
        intent.putExtra("user", name);
        startActivity(intent);
    }

    private void AccountInfo() {
        Intent intent = new Intent(User_Panel.this, Account_info.class);
        String name = getIntent().getStringExtra("user");
        intent.putExtra("user", name);
        startActivity(intent);
    }

    private void Benefits() {
        String user_save = getIntent().getStringExtra("user");
        String activityType = "Article Opened";  // Replace with the desired activity type
        Intent serviceIntent = new Intent(getApplicationContext(), UserActivityLoggingService.class);
        serviceIntent.putExtra("userId", user_save);
        serviceIntent.putExtra("activityType", activityType);
        startService(serviceIntent);

        String articleUrl = "https://www.healthcare.gov/glossary/essential-health-benefits/#:~:text=A%20set%20of%2010%20categories,Some%20plans%20cover%20more%20services.";
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(articleUrl));

        startActivity(intent);
    }

    private void logout() {
        // Implement any logout-related logic here

        // Navigate back to the start screen
        Intent intent = new Intent(User_Panel.this, StartActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        finish();
    }
}
