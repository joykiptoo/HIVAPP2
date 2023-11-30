package com.example.firebaseauth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth;
    Button button;

    TextView textView;
    FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button nextButton = findViewById(R.id.btn_nextpage);
        nextButton.setOnClickListener(view -> {

            Intent intent = new Intent(MainActivity.this, MoreInfo.class);
            startActivity(intent);

            Button chatButton = findViewById(R.id.btn_advise);

            chatButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Start the ChatActivity when the button is clicked
                    Intent intent = new Intent(MainActivity.this, Chat.class);
                    startActivity(intent);
                }
            });

    Button btnNo = findViewById(R.id.btnNo);

    btnNo.setOnClickListener(view1 -> {
        Intent intent1 = new Intent(MainActivity.this, TestKit.class);
        startActivity(intent1);
    });
    Button btnYes = findViewById(R.id.btnYes);

    btnYes.setOnClickListener(view12 -> {
        // Launch the OrderActivity
        Intent intent12 = new Intent(MainActivity.this, OrderActivity.class);
        startActivity(intent12);
    });

    auth=FirebaseAuth.getInstance();
    button=findViewById(R.id.logout);
    textView=findViewById(R.id.user_details);
    user=auth.getCurrentUser();
    if(user==null){
        intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
        finish();
    }
    else{
        textView.setText(user.getEmail());
    }

    Button goToProfileButton = findViewById(R.id.goToProfileButton);
    goToProfileButton.setOnClickListener(v -> {
        // Start ProfileActivity when the button is clicked
        startActivity(new Intent(MainActivity.this, ProfileActivity.class));
    });

    button.setOnClickListener(view13 -> {
        FirebaseAuth.getInstance().signOut();
        Intent intent13 = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent13);
        finish();
    });


});}}