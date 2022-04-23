package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

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
        delivery = getIntent().getStringExtra("delivery");
        db = new DatabaseHelper(this);


        deliveryTextView.setText(delivery);

        new CountDownTimer(30000, 1000) {

            @Override
            public void onTick(long l) {
                timerTextView.setText(l / 1000 + " seconds");
            }

            @Override
            public void onFinish() {
                Toast.makeText(getApplicationContext(), "Your order is complete!", Toast.LENGTH_SHORT).show();
                db.deleteAll();
                startActivity(new Intent(TheEndActivity.this, MainActivity.class));
            }
        }.start();

    }
}