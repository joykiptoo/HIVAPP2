package com.example.firebaseauth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


import com.google.android.material.floatingactionbutton.FloatingActionButton;



public class TestKit extends Activity {



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teskit);

        FloatingActionButton btnOrder = (FloatingActionButton) findViewById(R.id.btnOrder);

        btnOrder.setOnClickListener(view -> {
            Intent intent = new Intent(TestKit.this, PaymentActivity.class);
            startActivity(intent);
            finish();
        });
    }

    }
