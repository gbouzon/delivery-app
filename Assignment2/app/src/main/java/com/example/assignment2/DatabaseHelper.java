package com.example.assignment2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.sql.Array;
import java.util.ArrayList;

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
                "product_id INTEGER UNIQUE, CONSTRAINT fk_products FOREIGN KEY (product_id) " +
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
        sqLiteDatabase.execSQL("Drop table if exists favourites");
        sqLiteDatabase.execSQL("Drop table if exists cart");
        sqLiteDatabase.execSQL("Drop table if exists products");
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

    public boolean removeFavourite(int productId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("favourites", "product_id" + " = ?",
                new String[] { String.valueOf(productId) });
        db.close();
        return true;
    }

    public boolean addToCart(int productId) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("product_id", productId);
        database.insert("cart", null, values);
        database.close();
        return true;
    }

    public boolean removeFromCart(int productId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("cart", "product_id" + " = ?",
                new String[] { String.valueOf(productId) });
        db.close();
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

    public ArrayList<Product> getFlowers() {
        ArrayList<Product> flowers = new ArrayList<>();

        String select = "SELECT * FROM products WHERE category = 'flower'";
        SQLiteDatabase database = getWritableDatabase();
        Cursor cursor = database.rawQuery(select, null);

        if (cursor.moveToFirst()) {
            do {
                Product product = new Product();
                product.setCategory(cursor.getString(1));
                product.setTitle(cursor.getString(2));
                product.setDescription(cursor.getString(3));
                product.setPrice(Double.parseDouble(cursor.getString(4)));
                product.setImage(cursor.getString(5));

                flowers.add(product);
            } while (cursor.moveToNext());
        }
        return flowers;
    }

    public ArrayList<Product> getGifts() {
        ArrayList<Product> gifts = new ArrayList<>();

        String select = "SELECT * FROM products WHERE category = 'gift'";
        SQLiteDatabase database = getWritableDatabase();
        Cursor cursor = database.rawQuery(select, null);

        if (cursor.moveToFirst()) {
            do {
                Product product = new Product();
                product.setCategory(cursor.getString(1));
                product.setTitle(cursor.getString(2));
                product.setDescription(cursor.getString(3));
                product.setPrice(Double.parseDouble(cursor.getString(4)));
                product.setImage(cursor.getString(5));

                gifts.add(product);
            } while (cursor.moveToNext());
        }
        return gifts;
    }

    public ArrayList<Product> getAll() {
        ArrayList<Product> all = new ArrayList<>();

        String select = "SELECT * FROM products ORDER BY title";
        SQLiteDatabase database = getWritableDatabase();
        Cursor cursor = database.rawQuery(select, null);

        if (cursor.moveToFirst()) {
            do {
                Product product = new Product();
                product.setCategory(cursor.getString(1));
                product.setTitle(cursor.getString(2));
                product.setDescription(cursor.getString(3));
                product.setPrice(Double.parseDouble(cursor.getString(4)));
                product.setImage(cursor.getString(5));

                all.add(product);
            } while (cursor.moveToNext());
        }
        return all;
    }

    public ArrayList<Product> getFavourites() {
        ArrayList<Product> all = new ArrayList<>();

        String select = "SELECT * FROM favourites";
        SQLiteDatabase database = getWritableDatabase();
        Cursor cursor = database.rawQuery(select, null);

        if (cursor.moveToFirst()) {
            do {
                Product product = getProductById(Integer.parseInt(cursor.getString(1)));
                all.add(product);
            } while (cursor.moveToNext());
        }
        return all;
    }

    public ArrayList<Product> getCart() {
        ArrayList<Product> all = new ArrayList<>();

        String select = "SELECT * FROM cart";
        SQLiteDatabase database = getWritableDatabase();
        Cursor cursor = database.rawQuery(select, null);

        if (cursor.moveToFirst()) {
            do {
                Product product = getProductById(Integer.parseInt(cursor.getString(1)));
                all.add(product);
            } while (cursor.moveToNext());
        }
        return all;
    }

    public int getIdByTitle(String title) {
        SQLiteDatabase db = this.getReadableDatabase();
        Product product = new Product();

        String select = "SELECT * FROM products WHERE title = '" + title + "'";
        Cursor cursor = db.rawQuery(select, null);

        if (cursor != null)
            cursor.moveToFirst();

        return cursor.getInt(0);
    }

    public Product getProductById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Product product = new Product();

        String select = "SELECT * FROM products WHERE product_id = '" + id + "'";
        Cursor cursor = db.rawQuery(select, null);

        if (cursor != null)
            cursor.moveToFirst();

        product.setCategory(cursor.getString(1));
        product.setTitle(cursor.getString(2));
        product.setDescription(cursor.getString(3));
        product.setPrice(Double.parseDouble(cursor.getString(4)));
        product.setImage(cursor.getString(5));

        return product;
    }

    public void deleteAll() {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("favourites", null, null);
        db.delete("cart", null, null);
        db.close();
    }
}
