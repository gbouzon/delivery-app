package com.example.assignment2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class GiftsActivity extends AppCompatActivity {

    ArrayList<Product> products = new ArrayList<>();

    RecyclerView recyclerView;
    DatabaseHelper db;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gifts);

        db = new DatabaseHelper(this);
        db.getWritableDatabase();
        products = db.getGifts();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        RecyclerAdapter adapter = new RecyclerAdapter(getApplicationContext(), products);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
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
            case R.id.favouritesMenu:
                intent = new Intent(getApplicationContext(), FavouriteActivity.class);
                startActivity(intent);
                break;
            case R.id.cartMenu:
                intent = new Intent(getApplicationContext(), CartActivity.class);
                startActivity(intent);
                break;
            case R.id.guideMenu:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://nymag.com/strategist/gift-guides/"));
                startActivity(intent);
                break;

        }
        return super.onOptionsItemSelected(item);
    }

}