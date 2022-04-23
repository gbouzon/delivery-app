package com.example.assignment2;

// --------------------------------------------------------------------
// Assignment 2
// Written by: Giuliana Bouzon - 1940108
// For Application Development 2 (Mobile) - Winter 2022
// --------------------------------------------------------------------
public class Product {

    //attributes
    private String category;
    private String title;
    private String description;
    private double price;
    private String image;

    //default constructor
    public Product() {}

    //parameterized constructor
    public Product(String category, String title, String description, double price, String image) {
        this.category = category;
        this.title = title;
        this.description = description;
        this.price = price;
        this.image = image;
    }

    //copy constructor
    public Product(Product product) {
        this(product.category, product.title, product.description, product.price, product.image);
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}