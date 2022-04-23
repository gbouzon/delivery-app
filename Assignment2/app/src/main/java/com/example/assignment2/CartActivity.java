package com.example.assignment2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
// --------------------------------------------------------------------
// Assignment 2
// Written by: Giuliana Bouzon - 1940108
// For Application Development 2 (Mobile) - Winter 2022
// --------------------------------------------------------------------
public class CartActivity extends AppCompatActivity {
    ArrayList<Product> products = new ArrayList<>();

    RecyclerView recyclerView;
    DatabaseHelper db;
    TextView total;
    Button checkoutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        db = new DatabaseHelper(this);
        db.getWritableDatabase();
        products = db.getCart();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        total = (TextView) findViewById(R.id.totalTextView);
        checkoutBtn = (Button) findViewById(R.id.checkoutBtn);

        RecyclerAdapter2 adapter = new RecyclerAdapter2(getApplicationContext(), products);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        //sets the total price in cart details
        total.setText("$" + calculateTotal());

        checkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CartActivity.this, CheckoutActivity.class));
            }
        });
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
            case R.id.favouritesMenu:
                intent = new Intent(getApplicationContext(), FavouriteActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Calculates the total price of the products in the cart
     * @return the total.
     */
    public double calculateTotal() {
        double total = 0;
        for (Product product : products) {
            total += product.getPrice();
        }
        return total;
    }
}