package com.example.firebaseauth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;

public class ProfileActivity extends AppCompatActivity {
    private EditText phoneNumberEditText;
    private EditText symptomsEditText;
    private RadioButton maleRadioButton;
    private RadioButton femaleRadioButton;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Correct order of initialization based on XML layout
        maleRadioButton = findViewById(R.id.maleRadioButton);
        femaleRadioButton = findViewById(R.id.femaleRadioButton);
        phoneNumberEditText = findViewById(R.id.phoneNumberEditText);
        symptomsEditText=findViewById(R.id.symptomsEditText);
        Button saveButton = findViewById(R.id.saveButton);
        Button fetchButton = findViewById(R.id.fetchButton); // Initialize fetch button
        // Add delete account button
        Button deleteAccountButton = findViewById(R.id.deleteAccountButton); // Initialize delete account button

        fetchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call the fetch method when the fetch button is clicked
                fetchAndDisplayUserDetails();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getgender = maleRadioButton.isChecked() ? "Male" : "Female";
                String getphoneNumber = phoneNumberEditText.getText().toString();
                String getsymptoms = symptomsEditText.getText().toString();

                HashMap<String, Object> updatedData = new HashMap<>();
                updatedData.put("gender", getgender);
                updatedData.put("phoneNumber", getphoneNumber);
                updatedData.put("symptoms", getsymptoms);

                saveUserData(updatedData);
            }
        });

        // Add click listener for delete account button
        // Method to delete the account
        deleteAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.getCurrentUser().delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> taskTwo) {
                        if (taskTwo.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Account is Completely Deleted", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
                        }
                    }
                });
            }
        });
    }

    private void fetchAndDisplayUserDetails() {
        FirebaseFirestore.getInstance().collection("profile")
                .document("prof")
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            // Document exists, populate UI elements with data
                            String gender = documentSnapshot.getString("gender");
                            String phoneNumber = documentSnapshot.getString("phoneNumber");
                            String allergies = documentSnapshot.getString("symptoms");

                            // Populate UI elements with existing data
                            if (gender != null) {
                                if (gender.equals("Male")) {
                                    maleRadioButton.setChecked(true);
                                } else if (gender.equals("Female")) {
                                    femaleRadioButton.setChecked(true);
                                }
                            }

                            if (phoneNumber != null) {
                                phoneNumberEditText.setText(phoneNumber);
                            }

                            if (symptomsEditText != null) {
                                symptomsEditText.setText(allergies);
                            }
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ProfileActivity.this, "Failed to fetch user details: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void saveUserData(HashMap<String, Object> updatedData) {
        FirebaseFirestore.getInstance().collection("profile")
                .document("prof")
                .set(updatedData)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        phoneNumberEditText.getText().clear();
                        symptomsEditText.getText().clear();
                        maleRadioButton.setChecked(false);
                        femaleRadioButton.setChecked(false);

                        Toast.makeText(ProfileActivity.this, "Data saved Successfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ProfileActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
