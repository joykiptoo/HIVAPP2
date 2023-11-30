package com.example.firebaseauth;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;


public class Chat extends AppCompatActivity {

    private EditText phoneNumberEditText;

    private EditText editTextMessage;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Button sendButton = findViewById(R.id.buttonSend);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getphoneNumber = phoneNumberEditText.getText().toString();
                String getmessage = editTextMessage.getText().toString();

                HashMap<String, Object> updatedData = new HashMap<>();
                updatedData.put("phoneNumber", getphoneNumber);
                updatedData.put("message", getmessage);

                saveUserData(updatedData);


            }

            private void saveUserData(HashMap<String, Object> updatedData) {
                FirebaseFirestore.getInstance().collection("users")
                        .document("patients")
                        .set(updatedData)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                phoneNumberEditText.getText().clear();
                                editTextMessage.getText().clear();

                                Toast.makeText(Chat.this, "Data saved Successfully", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Chat.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }

        });
        };
    }