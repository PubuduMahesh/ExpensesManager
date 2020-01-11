package com.codenerdz.expensesmanager.activity.spender;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.codenerdz.expensesmanager.R;
import com.codenerdz.expensesmanager.activity.common.CommonItemAdapter;

public class SpenderAdapter extends CommonItemAdapter<Spender>
{
    public SpenderAdapter(Context context, Spender[] items) {
        super(context, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Spender spender = items[position];

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