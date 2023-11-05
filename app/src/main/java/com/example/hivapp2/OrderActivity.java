package com.example.hivapp2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OrderActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        Button orderSelfTestKitsButton = findViewById(R.id.order_self_test_kits_button);
        Button orderArvDrugsButton = findViewById(R.id.order_arv_drugs_button);

        orderSelfTestKitsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle ordering self-test kits here
                // You can send requests to your server or interact with Firestore
            }
        });

        orderArvDrugsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle ordering ARV drugs here
                // You can send requests to your server or interact with Firestore
            }
            Intent intent = new Intent(OrderActivity.this, LoginActivity.class);
            
        });
    }
}
