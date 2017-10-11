package com.example.admin.bugproject.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.admin.bugproject.Objects.FPackage;
import com.example.admin.bugproject.R;

/**
 * Created by Admin on 9/19/2017.
 */

public class BillListAdapter extends ArrayAdapter<Bitmap>{

    public BillListAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_listbill, parent, false);
        ViewHolder holder = new ViewHolder();
        holder.img = (ImageView) view.findViewById(R.id.item_listbill_img);

        Bitmap item = getItem(position);

        holder.img.setImageBitmap(item);
        view.setTag(holder);
        return  view;
    }

    private class ViewHolder{
        private ImageView img;
    }
}
