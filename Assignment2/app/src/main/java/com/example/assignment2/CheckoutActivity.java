package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

// --------------------------------------------------------------------
// Assignment 2
// Written by: Giuliana Bouzon - 1940108
// For Application Development 2 (Mobile) - Winter 2022
// --------------------------------------------------------------------
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
                //checks if name or email fields are empty. If so, prompt user to enter something (mandatory fields)
                if (nameEditText.getText().toString().isEmpty() || emailEditText.getText().toString().isEmpty())
                    Toast.makeText(getApplicationContext(), "Please enter a name and an email before continuing.", Toast.LENGTH_SHORT).show();
                else {
                    intent = new Intent(getApplicationContext(), TheEndActivity.class);
                    //check if at least one radio button is checked
                    if (pickupRadioBtn.isChecked() || deliveryRadioBtn.isChecked()) {
                        if (pickupRadioBtn.isChecked()) {
                            intent.putExtra("delivery", "Pick-Up");
                        } else {
                            intent.putExtra("delivery", "Delivery");
                        }
                        //starts the timer activity
                        startActivity(intent);
                    } else
                        //prompts user to choose a delivery method if empty
                        Toast.makeText(getApplicationContext(), "Method of delivery cannot be empty.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}