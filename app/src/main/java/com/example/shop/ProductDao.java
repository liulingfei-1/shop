package com.example.shop;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    private SQLiteDatabase db;

    public ProductDao(Context context) {
        DBHelper helper = ((MyApp)context.getApplicationContext()).getDbHelper();
        db = helper.getReadableDatabase();
    }

    public List<Product> getAllProducts() {
        List<Product> list = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT id,name,price,image,desc FROM product", null);
        while (c.moveToNext()) {
            int id = c.getInt(0);
            String name = c.getString(1);
            double price = c.getDouble(2);
            String image = c.getString(3);
            String desc = c.getString(4);
            list.add(new Product(id, name, price, image, desc));
        }
        c.close();
        return list;
    }

    public Product getProductById(int productId) {
        Cursor c = db.rawQuery("SELECT id,name,price,image,desc FROM product WHERE id=?", new String[]{String.valueOf(productId)});
        if(c.moveToFirst()) {
            int id = c.getInt(0);
            String name = c.getString(1);
            double price = c.getDouble(2);
            String image = c.getString(3);
            String desc = c.getString(4);
            c.close();
            return new Product(id, name, price, image, desc);
        }
        c.close();
        return null;
    }
}
