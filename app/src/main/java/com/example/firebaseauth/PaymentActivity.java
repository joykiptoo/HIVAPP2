package com.example.firebaseauth;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import java.util.HashMap;
import java.util.Map;


public class PaymentActivity extends Activity {

    private EditText editTextPhoneNumber, editTextAmount;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_activity);

        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        Button btnPay = findViewById(R.id.btnPay);


        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Call a method to initiate the payment process
                initiatePayment();
            }

            private void initiatePayment() {
                String phoneNumber = editTextPhoneNumber.getText().toString();
                String amount = editTextAmount.getText().toString();
                String transactionDescription = "Payment for XYZ product";

                Map<String, Object> payload = new HashMap<>();
                payload.put("phoneNumber", phoneNumber);
                payload.put("amount", amount);
                payload.put("transactionDescription", transactionDescription);

            }
        });
    }

    }

