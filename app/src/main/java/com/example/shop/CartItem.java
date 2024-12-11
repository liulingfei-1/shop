package com.example.shop;

public class CartItem {
    private String image;
    private String name;
    private int quantity;
    private double price;
    private double total;

    public CartItem(String image, String name, int quantity, double price, double total) {
        this.image = image;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public double getTotal() {
        return total;
    }
}
