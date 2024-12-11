package com.example.shop;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "shop.db";
    private static final int VERSION = 1;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 创建商品表
        db.execSQL("CREATE TABLE product (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "price REAL," +
                "image TEXT," +
                "desc TEXT)");

        // 创建购物车表
        db.execSQL("CREATE TABLE cart (" +
                "cart_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "product_id INTEGER," +
                "quantity INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
