package com.example.healthcare;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class UserActivityLoggingService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Get the user's activity data from the intent
        String userId = intent.getStringExtra("userId");
        String activityType = intent.getStringExtra("activityType");

        // Log the user's activity
        logUserActivity(userId, activityType);

        // Stop the service when the task is complete
        stopSelf();

        return START_NOT_STICKY;
    }

    private void logUserActivity(String userId, String activityType) {
        // Perform the logging operation, e.g., save the activity to a database or send it to a remote server
        // You can customize this method to fit your app's requirements
        Log.d("UserActivityLogging", "User " + userId + " performed activity: " + activityType);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

