package com.example.assignment2;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "database.db";
    private static final int DB_VERSION = 1;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE if not exists products (product_id INTEGER primary key autoincrement," +
                "category VARCHAR, title VARCHAR UNIQUE, description TEXT, price REAL, image VARCHAR)");

        sqLiteDatabase.execSQL("CREATE TABLE if not exists favourites (id INTEGER primary key autoincrement, " +
                "product_id INTEGER, CONSTRAINT fk_products FOREIGN KEY (product_id) " +
                "REFERENCES products(product_id))");

        sqLiteDatabase.execSQL("CREATE TABLE if not exists cart (id INTEGER primary key autoincrement, " +
                "product_id INTEGER, CONSTRAINT fk_products FOREIGN KEY (product_id) " +
                "REFERENCES products(product_id))");

        sqLiteDatabase.execSQL("INSERT INTO products(category, title, description, price, image) VALUES('flower', 'Azalea', 'An azalea', 5.00, '@drawable/azalea')");
        sqLiteDatabase.execSQL("INSERT INTO products(category, title, description, price, image) VALUES('flower', 'Calla Lily', 'A calla lily', 5.00, '@drawable/callalily')");
        sqLiteDatabase.execSQL("INSERT INTO products (category, title, description, price, image) VALUES('flower', 'Daisy', 'A daisy', 5.00, '@drawable/daisy')");
        sqLiteDatabase.execSQL("INSERT INTO products (category, title, description, price, image) VALUES('flower', 'Hydrangea', 'A hydrangea', 5.00, '@drawable/hydrangea')");
        sqLiteDatabase.execSQL("INSERT INTO products (category, title, description, price, image) VALUES('flower', 'Lily', 'A lily', 5.00, '@drawable/lily')");
        sqLiteDatabase.execSQL("INSERT INTO products (category, title, description, price, image) VALUES('flower', 'Tulip', 'A tulip', 5.00, '@drawable/tulip')");
        sqLiteDatabase.execSQL("INSERT INTO products (category, title, description, price, image) VALUES('flower', 'White Rose', 'A white rose', 5.00, '@drawable/whiteroses')");

        sqLiteDatabase.execSQL("INSERT INTO products (category, title, description, price, image) VALUES('gift', 'Mixed Basket', 'A gift basket', 75.00, '@drawable/basket')");
        sqLiteDatabase.execSQL("INSERT INTO products (category, title, description, price, image) VALUES('gift', 'Candy Basket', 'A candy basket', 45.00, '@drawable/candy')");
        sqLiteDatabase.execSQL("INSERT INTO products (category, title, description, price, image) VALUES('gift', 'Chocolate', 'A box of chocolates', 15.00, '@drawable/chocolate')");
        sqLiteDatabase.execSQL("INSERT INTO products (category, title, description, price, image) VALUES('gift', 'Corsair iCUE H150i Elite Capellix', 'A white 360mm Liquid CPU Cooler', 249.99, '@drawable/cooler')");
        sqLiteDatabase.execSQL("INSERT INTO products (category, title, description, price, image) VALUES('gift', 'Asus ROG Strix NVIDIA GeForce RTX 3070 V2', 'A ROG Strix GPU, White OC Edition', 919.00, '@drawable/gpu')");
        sqLiteDatabase.execSQL("INSERT INTO products (category, title, description, price, image) VALUES('gift', 'Samsung Odyssey G7 Gaming Monitor', 'A 28\" Gaming Monitor', 903.99, '@drawable/monitor')");
        sqLiteDatabase.execSQL("INSERT INTO products (category, title, description, price, image) VALUES('gift', 'Perfume', 'A perfume', 65.00, '@drawable/perfume')");
        sqLiteDatabase.execSQL("INSERT INTO products (category, title, description, price, image) VALUES('gift', 'Watch', 'A watch', 130.00, '@drawable/watch')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("Drop table if exists products");
        sqLiteDatabase.execSQL("Drop table if exists favourites");
        onCreate(sqLiteDatabase);
    }

    public boolean addFavourite(int productId) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("product_id", productId);
        database.insert("favourites", null, values);
        database.close();
        return true;
    }

    public boolean addToCart(Integer productId) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("product_id", productId);
        database.insert("cart", null, values);
        database.close();
        return true;
    }

    public boolean addProduct(String productCategory, String productTitle, String productDescription, double productPrice, String productImage) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("category", productCategory);
        values.put("title", productTitle);
        values.put("description", productDescription);
        values.put("price", productPrice);
        values.put("image", productImage);
        database.insert("products", null, values);
        database.close();
        return true;
    }

    pub


}
