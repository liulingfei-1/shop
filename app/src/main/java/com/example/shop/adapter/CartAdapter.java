package com.example.shop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shop.CartItem;
import com.example.shop.R;

import java.util.List;

public class CartAdapter extends BaseAdapter {
    private Context context;
    private List<CartItem> data;

    public CartAdapter(Context context, List<CartItem> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() { return data.size(); }

    @Override
    public Object getItem(int position) { return data.get(position); }

    @Override
    public long getItemId(int position) { return position; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if(convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);
            vh = new ViewHolder();
            vh.ivImage = convertView.findViewById(R.id.iv_image);
            vh.tvName = convertView.findViewById(R.id.tv_name);
            vh.tvQuantity = convertView.findViewById(R.id.tv_quantity);
            vh.tvPrice = convertView.findViewById(R.id.tv_price);
            vh.tvTotal = convertView.findViewById(R.id.tv_total);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        CartItem item = data.get(position);
        vh.tvName.setText(item.getName());
        vh.tvQuantity.setText("数量：" + item.getQuantity());
        vh.tvPrice.setText("单价：" + item.getPrice());
        vh.tvTotal.setText("总价：" + item.getTotal());

        int resId = context.getResources().getIdentifier(item.getImage(), "drawable", context.getPackageName());
        if(resId != 0) {
            vh.ivImage.setImageResource(resId);
        } else {
            vh.ivImage.setImageResource(R.drawable.ic_launcher_foreground);
        }

        return convertView;
    }

    class ViewHolder {
        ImageView ivImage;
        TextView tvName, tvQuantity, tvPrice, tvTotal;
    }
}
