package com.codenerdz.expensesmanager.activity.spender;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codenerdz.expensesmanager.R;
import com.codenerdz.expensesmanager.activity.category.Category;

public class SpenderAdapter extends BaseAdapter
{
    private Context context;
    private Spender[] spenders;
    public SpenderAdapter(Context context, Spender[] spenders)
    {
        this.context = context;
        this.spenders = spenders;
    }

    @Override
    public int getCount()
    {
        return spenders.length;
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
        final Spender spender = spenders[position];

        if(convertView == null)
        {
            final LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.image_text_item_layout,null);
        }
        final ImageView imageView = (ImageView)convertView.findViewById(R.id.image_text_image);
        final TextView nameTextView = (TextView)convertView.findViewById(R.id.image_text_text);

        imageView.setImageResource(spender.getSpenderImageSource());
        nameTextView.setText(spender.getSpenderName());
        return convertView;
    }
}
