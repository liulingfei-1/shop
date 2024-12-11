package com.example.shop.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shop.CartDao;
import com.example.shop.Product;
import com.example.shop.ProductDetailActivity;
import com.example.shop.R;

import java.util.List;

public class ProductAdapter extends BaseAdapter {
    private Context context;
    private List<Product> data;

    public ProductAdapter(Context context, List<Product> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() { return data.size(); }

    @Override
    public Object getItem(int position) { return data.get(position); }

    @Override
    public long getItemId(int position) { return data.get(position).getId(); }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if(convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
            vh = new ViewHolder();
            vh.ivImage = convertView.findViewById(R.id.iv_image);
            vh.tvName = convertView.findViewById(R.id.tv_name);
            vh.tvPrice = convertView.findViewById(R.id.tv_price);
            vh.btnDetail = convertView.findViewById(R.id.btn_detail);
            vh.btnAddCart = convertView.findViewById(R.id.btn_add_cart);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        Product p = data.get(position);
        vh.tvName.setText(p.getName());
        vh.tvPrice.setText("￥" + p.getPrice());

        // 根据image名称获取资源ID
        int resId = context.getResources().getIdentifier(p.getImage(), "drawable", context.getPackageName());
        if(resId != 0) {
            vh.ivImage.setImageResource(resId);
        } else {
            vh.ivImage.setImageResource(R.drawable.ic_launcher_foreground);
        }

        vh.btnDetail.setOnClickListener(v -> {
            Intent intent = new Intent(context, ProductDetailActivity.class);
            intent.putExtra("product_id", p.getId());
            context.startActivity(intent);
        });

        vh.btnAddCart.setOnClickListener(v -> {
            CartDao cartDao = new CartDao(context);
            cartDao.addToCart(p.getId(),1);
        });

        return convertView;
    }

    class ViewHolder {
        ImageView ivImage;
        TextView tvName, tvPrice;
        Button btnDetail, btnAddCart;
    }
}
