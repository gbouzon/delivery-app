package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class CheckoutActivity extends AppCompatActivity {

    EditText nameEditText, emailEditText, notesEditText;
    RadioButton pickupRadioBtn, deliveryRadioBtn;
    Button completeBtn;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        nameEditText = (EditText) findViewById(R.id.nameEditText);
        emailEditText = (EditText) findViewById(R.id.emailEditText);
        notesEditText = (EditText) findViewById(R.id.notesEditText);
        pickupRadioBtn = (RadioButton) findViewById(R.id.pickupRadioBtn);
        deliveryRadioBtn = (RadioButton) findViewById(R.id.deliveryRadioBtn);
        completeBtn = (Button) findViewById(R.id.completeBtn);

        completeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nameEditText.getText().toString().isEmpty() || emailEditText.getText().toString().isEmpty())
                    Toast.makeText(getApplicationContext(), "Please enter a name and an email before continuing.", Toast.LENGTH_SHORT).show();
                else {
                    intent = new Intent(getApplicationContext(), TheEndActivity.class);
                    if (pickupRadioBtn.isChecked() || deliveryRadioBtn.isChecked()) {
                        if (pickupRadioBtn.isChecked()) {
                            intent.putExtra("delivery", "Pick-Up");
                        } else {
                            intent.putExtra("delivery", "Delivery");
                        }
                        startActivity(intent);
                    } else
                        Toast.makeText(getApplicationContext(), "Method of delivery cannot be empty.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}