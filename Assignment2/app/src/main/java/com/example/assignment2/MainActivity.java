package com.example.assignment2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> titles = new ArrayList<>();
    ArrayList<String> descriptions = new ArrayList<>();
    ArrayList<String> prices = new ArrayList<>();
    ArrayList<String> images = new ArrayList<>();
    DatabaseHelper db;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        db = new DatabaseHelper(this);
        db.getWritableDatabase();
        db.addToCart(1);

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

        titles.add("Mixed Basket");
        titles.add("Candy Basket");
        titles.add("Chocolate");
        titles.add("Corsair iCUE H150i Elite Capellix");
        titles.add("Asus ROG Strix NVIDIA GeForce RTX 3070 V2");
        titles.add("Samsung Odyssey G7 Gaming Monitor");
        titles.add("Perfume");
        titles.add("Watch");

        descriptions.add("A gift basket.");
        descriptions.add("A candy basket.");
        descriptions.add("A box of chocolates.");
        descriptions.add("A white 360mm Liquid CPU Cooler");
        descriptions.add("A ROG Strix GPU, White OC Edition");
        descriptions.add("A 28\" Gaming Monitor");
        descriptions.add("A perfume");
        descriptions.add("A watch");

        prices.add("75.00");
        prices.add("45.00");
        prices.add("15.00");
        prices.add("249.99");
        prices.add("919.00");
        prices.add("903.99");
        prices.add("65.00");
        prices.add("130.00");

        images.add("@drawable/basket");
        images.add("@drawable/candy");
        images.add("@drawable/chocolate");
        images.add("@drawable/cooler");
        images.add("@drawable/gpu");
        images.add("@drawable/monitor");
        images.add("@drawable/perfume");
        images.add("@drawable/watch");

        RecyclerAdapter adapter = new RecyclerAdapter(getApplicationContext(), titles, descriptions, prices, images);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

    }

    public void populateList(ArrayList<String> list) {

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
            case R.id.favouritesMenu:
                Toast.makeText(this, "Item 1 is selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.flowersMenu:
                Intent intent = new Intent(getApplicationContext(), FlowersActivity.class);
                startActivity(intent);
                break;
            case R.id.giftsMenu:
                intent = new Intent(getApplicationContext(), GiftsActivity.class);
                startActivity(intent);
                break;
            case R.id.cartMenu:
                intent = new Intent(getApplicationContext(), CartActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}