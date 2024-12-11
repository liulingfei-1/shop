package com.example.shop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class ProductDetailActivity extends AppCompatActivity {

    private ImageView ivImage;
    private TextView tvName, tvPrice, tvDesc;
    private Button btnAddCart;
    private int productId;
    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        ivImage = findViewById(R.id.iv_image);
        tvName = findViewById(R.id.tv_name);
        tvPrice = findViewById(R.id.tv_price);
        tvDesc = findViewById(R.id.tv_desc);
        btnAddCart = findViewById(R.id.btn_add_cart);

        productId = getIntent().getIntExtra("product_id", -1);

        ProductDao productDao = new ProductDao(this);
        product = productDao.getProductById(productId);

        if(product != null) {
            tvName.setText(product.getName());
            tvPrice.setText("价格：" + product.getPrice());
            tvDesc.setText(product.getDesc());
            // 简化：根据image名称设置drawable
            int resId = getDrawableIdByName(product.getImage());
            if(resId != 0) {
                ivImage.setImageResource(resId);
            }
        }

        btnAddCart.setOnClickListener(v -> {
            CartDao cartDao = new CartDao(ProductDetailActivity.this);
            cartDao.addToCart(productId, 1);
            Toast.makeText(ProductDetailActivity.this, "已加入购物车", Toast.LENGTH_SHORT).show();
        });
    }

    private int getDrawableIdByName(String name) {
        return getResources().getIdentifier(name, "drawable", getPackageName());
    }
}
