package com.example.shop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.shop.adapter.ProductAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ProductAdapter adapter;
    private ProductDao productDao;
    private Button btnCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        productDao = new ProductDao(this);

        listView = findViewById(R.id.list_view);
        btnCart = findViewById(R.id.btn_cart);
        List<Product> productList = productDao.getAllProducts();
        adapter = new ProductAdapter(this, productList);
        listView.setAdapter(adapter);

        btnCart.setOnClickListener(v -> {
            // 跳转购物车页面
            startActivity(new Intent(MainActivity.this, CartActivity.class));
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 可更新购物车计数（如显示在btnCart上）
        CartDao cartDao = new CartDao(this);
        int count = cartDao.getCartCount();
        if(count > 0) {
            btnCart.setText("购物车(" + count + ")");
        } else {
            btnCart.setText("购物车");
        }
    }
}
