package com.example.shop;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class CartDao {
    private SQLiteDatabase db;

    public CartDao(Context context) {
        DBHelper helper = ((MyApp)context.getApplicationContext()).getDbHelper();
        db = helper.getWritableDatabase();
    }

    public void addToCart(int productId, int quantity) {
        Cursor cursor = db.rawQuery("SELECT quantity FROM cart WHERE product_id=?", new String[]{String.valueOf(productId)});
        if(cursor.moveToFirst()){
            int oldQuantity = cursor.getInt(0);
            int newQuantity = oldQuantity + quantity;
            db.execSQL("UPDATE cart SET quantity=? WHERE product_id=?", new Object[]{newQuantity, productId});
        } else {
            db.execSQL("INSERT INTO cart (product_id, quantity) VALUES(?,?)", new Object[]{productId, quantity});
        }
        cursor.close();
    }

    public List<CartItem> getCartItems() {
        List<CartItem> list = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT p.image, p.name, c.quantity, p.price, (p.price*c.quantity) as total " +
                "FROM cart c JOIN product p ON c.product_id = p.id", null);
        while(c.moveToNext()) {
            String image = c.getString(0);
            String name = c.getString(1);
            int quantity = c.getInt(2);
            double price = c.getDouble(3);
            double total = c.getDouble(4);
            list.add(new CartItem(image, name, quantity, price, total));
        }
        c.close();
        return list;
    }

    public void clearCart() {
        db.execSQL("DELETE FROM cart");
    }

    public double getCartTotalPrice() {
        Cursor c = db.rawQuery("SELECT SUM(p.price*c.quantity) FROM cart c JOIN product p ON c.product_id = p.id", null);
        double total = 0;
        if(c.moveToFirst()) {
            total = c.getDouble(0);
        }
        c.close();
        return total;
    }

    public int getCartCount() {
        Cursor c = db.rawQuery("SELECT SUM(quantity) FROM cart", null);
        int count = 0;
        if(c.moveToFirst()) {
            count = c.getInt(0);
        }
        c.close();
        return count;
    }
}
