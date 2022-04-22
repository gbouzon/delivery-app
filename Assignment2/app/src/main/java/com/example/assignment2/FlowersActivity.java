package com.example.assignment2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class FlowersActivity extends AppCompatActivity {

    ArrayList<String> titles = new ArrayList<>();
    ArrayList<String> descriptions = new ArrayList<>();
    ArrayList<String> prices = new ArrayList<>();
    ArrayList<String> images = new ArrayList<>();

    RecyclerView recyclerView;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.favouritesMenu:
                Toast.makeText(this, "Item 1 is selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.homeMenu:
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                break;
            case R.id.giftsMenu:
                intent = new Intent(getApplicationContext(), GiftsActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flowers);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        titles.add("Azalea");
        titles.add("Calla Lily");
        titles.add("Daisy");
        titles.add("Hydrangea");
        titles.add("Lily");
        titles.add("Tulip");
        titles.add("White Rose");

        descriptions.add("An azalea.");
        descriptions.add("A calla lily");
        descriptions.add("A daisy");
        descriptions.add("A hydrangea");
        descriptions.add("A lily");
        descriptions.add("A tulip");
        descriptions.add("A white Rose");

        prices.add("5.00");
        prices.add("5.00");
        prices.add("5.00");
        prices.add("5.00");
        prices.add("5.00");
        prices.add("5.00");
        prices.add("5.00");

        images.add("@drawable/azalea");
        images.add("@drawable/callalily");
        images.add("@drawable/daisy");
        images.add("@drawable/hydrangea");
        images.add("@drawable/lily");
        images.add("@drawable/tulip");
        images.add("@drawable/whiteroses");

        RecyclerAdapter adapter = new RecyclerAdapter(getApplicationContext(), titles, descriptions, prices, images);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }
}