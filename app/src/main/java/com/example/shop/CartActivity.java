package com.example.shop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shop.adapter.CartAdapter;

import java.util.List;

public class CartActivity extends AppCompatActivity {

    private ListView listView;
    private TextView tvEmpty, tvTotal;
    private Button btnClear, btnCheckout, btnGoShop;
    private CartDao cartDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        cartDao = new CartDao(this);

        listView = findViewById(R.id.list_cart);
        tvEmpty = findViewById(R.id.tv_empty);
        tvTotal = findViewById(R.id.tv_total);
        btnClear = findViewById(R.id.btn_clear);
        btnCheckout = findViewById(R.id.btn_checkout);
        btnGoShop = findViewById(R.id.btn_go_shop);

        refreshCart();

        btnClear.setOnClickListener(v -> {
            cartDao.clearCart();
            refreshCart();
        });

        btnCheckout.setOnClickListener(v -> {
            double total = cartDao.getCartTotalPrice();
            if(total > 0) {
                Toast.makeText(CartActivity.this, "结算成功，合计：" + total, Toast.LENGTH_LONG).show();
                // 模拟结算完成后清空购物车
                cartDao.clearCart();
                refreshCart();
            } else {
                Toast.makeText(CartActivity.this, "购物车为空", Toast.LENGTH_SHORT).show();
            }
        });

        btnGoShop.setOnClickListener(v -> finish());
    }

    private void refreshCart() {
        List<CartItem> items = cartDao.getCartItems();
        if(items.size() == 0) {
            // 购物车为空
            tvEmpty.setVisibility(View.VISIBLE);
            btnGoShop.setVisibility(View.VISIBLE);
            listView.setVisibility(View.GONE);
            tvTotal.setText("总金额：0");
            btnClear.setEnabled(false);
            btnCheckout.setEnabled(false);
        } else {
            tvEmpty.setVisibility(View.GONE);
            btnGoShop.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);

            CartAdapter adapter = new CartAdapter(this, items);
            listView.setAdapter(adapter);
            double totalPrice = cartDao.getCartTotalPrice();
            tvTotal.setText("总金额：" + totalPrice);
            btnClear.setEnabled(true);
            btnCheckout.setEnabled(true);
        }
    }
}
