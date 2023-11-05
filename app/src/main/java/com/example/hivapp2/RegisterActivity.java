package com.example.hivapp;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.hivapp2.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends Activity {
    private EditText passwordEditText;
    private EditText emailEditText;
    private Object username;
    private Object email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText usernameEditText = findViewById(R.id.register_username);
        passwordEditText = findViewById(R.id.register_password);
        emailEditText = findViewById(R.id.register_email);
        Button registerButton = findViewById(R.id.register_button);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseFirestore db= FirebaseFirestore.getInstance();
        Map<String,Object> user=new HashMap<>();
        user.put("username",username);
        user.put("email",email);
        registerButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(RegisterActivity.this, task -> {
                        if (task.isSuccessful()) {
                            // Sign up success, update UI with the signed-in user's information
                            mAuth.getCurrentUser();
                            // Proceed to the next screen
                        } else {
                            // If sign up fails, display a message to the user.
                            Toast.makeText(RegisterActivity.this, "Registration failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });

            db.collection("users")
                    .add(user)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error adding document", e);
                        }
                    });

        });
    }
}