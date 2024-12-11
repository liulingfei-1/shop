package com.example.shop;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

public class MyApp extends Application {
    private static MyApp instance;
    private DBHelper dbHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        dbHelper = new DBHelper(this);
        // 初始化商品数据(只在首次时)
        initData();
    }

    public static MyApp getInstance() {
        return instance;
    }

    public DBHelper getDbHelper() {
        return dbHelper;
    }

    private void initData() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // 清空旧数据
        db.execSQL("DELETE FROM product");
        db.execSQL("DELETE FROM cart");

        // 插入一些商品测试数据
        db.execSQL("INSERT INTO product(name,price,image,desc) VALUES('HUAWEI Mate30',4999,'mate30','华为Mate30 8GB+256GB 5G手机')");
        db.execSQL("INSERT INTO product(name,price,image,desc) VALUES('OPPO Reno3',2999,'reno3','OPPO Reno3 8GB+128GB 5G手机')");
        db.execSQL("INSERT INTO product(name,price,image,desc) VALUES('小米10',3999,'mi10','小米10 8GB+128GB 5G手机')");
        db.execSQL("INSERT INTO product(name,price,image,desc) VALUES('vivo X30',2998,'x30','vivo X30 8GB+128GB 5G手机')");
        db.execSQL("INSERT INTO product(name,price,image,desc) VALUES('荣耀30S',2399,'honor30s','荣耀30S 8GB+128GB 5G手机')");
    }
}
