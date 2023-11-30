package com.example.firebaseauth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends Activity {

    Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Find views
        TextView generalInfoTextView = findViewById(R.id.generalInfoTextView);
        TextView personalizedContentTextView = findViewById(R.id.personalizedContentTextView);

        // Set general information
        generalInfoTextView.setText(R.string.general_information_about_hiv2);

        // Simulate personalized content based on user profile
        String userInterests = getUserInterests(); // Replace with actual method to get user interests
        String personalizedContent = getPersonalizedContent(userInterests);
        personalizedContentTextView.setText(personalizedContent);

        // Corrected placement of the following lines
        logoutButton = findViewById(R.id.logoutButton);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    // Simulated method to get user interests (replace with actual implementation)
    private String getUserInterests() {
        // In a real app, you would fetch this information from the user's profile
        return "Prevention, Treatment";
    }

    // Simulated method to generate personalized content based on user interests
    private String getPersonalizedContent(String userInterests) {
        // In a real app, you would fetch personalized content from a server or database
        if (userInterests.contains("Prevention")) {
            return "Content related to HIV prevention";
        } else if (userInterests.contains("Treatment")) {
            return "Content related to HIV treatment";
        } else {
            return "Generic personalized content";
        }
    }
}
