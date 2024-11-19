package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

public class ImagineAdapter extends BaseAdapter {
    private Context ctx;
    private int layoutRes;
    private List<ItemImagine> imaginiItems;

    public ImagineAdapter(Context ctx, int layoutRes, List<ItemImagine> imaginiItems) {
        this.ctx = ctx;
        this.layoutRes = layoutRes;
        this.imaginiItems = imaginiItems;
    }

    @Override
    public int getCount() {
        return imaginiItems.size();
    }

    @Override
    public Object getItem(int position) {
        return imaginiItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View v = inflater.inflate(layoutRes, parent, false);

        ItemImagine item = imaginiItems.get(position);

        ImageView img = v.findViewById(R.id.imagineIV);
        TextView text = v.findViewById(R.id.textTV);

        img.setImageBitmap(item.getImagine());
        text.setText(item.getTextAfisat());

        return v;
    }
}
