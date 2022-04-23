package com.example.assignment2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class CheckoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.flowersMenu:
                Intent intent = new Intent(getApplicationContext(), FlowersActivity.class);
                startActivity(intent);
                break;
            case R.id.homeMenu:
                intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                break;
            case R.id.giftsMenu:
                intent = new Intent(getApplicationContext(), GiftsActivity.class);
                startActivity(intent);
                break;
            case R.id.guideMenu:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://nymag.com/strategist/gift-guides/"));
                startActivity(intent);
                break;
            case R.id.cartMenu:
                intent = new Intent(getApplicationContext(), CartActivity.class);
                startActivity(intent);
                break;
            case R.id.favouritesMenu:
                intent = new Intent(getApplicationContext(), FavouriteActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}