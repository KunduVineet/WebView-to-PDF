package com.teeniv.webview_to_pdf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class splash_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // Add a delay to automatically navigate to the MainActivity after a certain time
        new Handler().postDelayed(() -> {
            startActivity(new Intent(this, MainActivity.class));
            finish(); // Optional: Finish the splash activity to prevent returning to it
        }, 500); // Change the delay time as needed
    }

    @Override
    public void onBackPressed() {
        // Do nothing or handle the back press as needed
        // For example, you can prevent going back by not calling super.onBackPressed();
    }
}
