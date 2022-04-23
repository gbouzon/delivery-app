package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

// --------------------------------------------------------------------
// Assignment 2
// Written by: Giuliana Bouzon - 1940108
// For Application Development 2 (Mobile) - Winter 2022
// --------------------------------------------------------------------
public class TheEndActivity extends AppCompatActivity {

    TextView deliveryTextView, timerTextView;
    String delivery;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_end);

        deliveryTextView = (TextView) findViewById(R.id.deliveryTextView);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        //gets the method of delivery passed on from checkout activity
        delivery = getIntent().getStringExtra("delivery");
        db = new DatabaseHelper(this);

        //sets text view to the method of delivery chosen
        deliveryTextView.setText(delivery);

        //5 minute timer for delivery
        new CountDownTimer(300000, 1000) {

            @Override
            public void onTick(long l) {
                timerTextView.setText(l / 1000 + " seconds");
            }

            @Override
            public void onFinish() {
                Toast.makeText(getApplicationContext(), "Your order is complete!", Toast.LENGTH_SHORT).show();
                db.deleteAll(); //deletes all records from favourites list and cart
                startActivity(new Intent(TheEndActivity.this, MainActivity.class));
            }
        }.start();
    }
}