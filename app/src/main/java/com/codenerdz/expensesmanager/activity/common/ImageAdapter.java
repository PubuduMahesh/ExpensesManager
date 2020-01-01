package com.codenerdz.expensesmanager.activity.common;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.codenerdz.expensesmanager.R;

public class ImageAdapter extends BaseAdapter
{
    private Context context;
    private int [] images;

    public ImageAdapter(Context context,int[]images)
    {
        this.context = context;
        this.images = images;
    }

    @Override
    public int getCount()
    {
        return images.length;
    }

    @Override
    public long getItemId(int position)
    {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int image = images[position];

        if(convertView == null)
        {
            final LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.image_item_layout,null);
        }
        final ImageView imageView = (ImageView)convertView.findViewById(R.id.image);
        imageView.setTag(image);
        imageView.setImageResource(image);
        return convertView;
    }

}
