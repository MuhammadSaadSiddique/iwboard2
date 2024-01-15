package com.example.iwboard;

// ColorGridAdapter.java

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class ColorGridAdapter extends BaseAdapter {

    private Context context;
    private int[] colors;

    public ColorGridAdapter(Context context, int[] colors) {
        this.context = context;
        this.colors = colors;
    }

    @Override
    public int getCount() {
        return colors.length;
    }

    @Override
    public Object getItem(int position) {
        return colors[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView colorView;
        if (convertView == null) {
            colorView = new ImageView(context);
            colorView.setLayoutParams(new ViewGroup.LayoutParams(64, 64));
            colorView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            colorView.setPadding(8, 8, 8, 8);
        } else {
            colorView = (ImageView) convertView;
        }

        int color = colors[position];
        colorView.setBackgroundColor(color);

        return colorView;
    }
}

