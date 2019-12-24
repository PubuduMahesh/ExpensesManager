package com.codenerdz.expensesmanager.ui.category;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.codenerdz.expensesmanager.R;

public class CategoryAdapter extends BaseAdapter {
    private Context context;
    private Category[] categories;
    public CategoryAdapter(Context context, Category[] categories)
    {
        this.context = context;
        this.categories = categories;
    }

    @Override
    public int getCount()
    {
        return categories.length;
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
       final Category category = categories[position];

       if(convertView == null)
       {
           final LayoutInflater layoutInflater = LayoutInflater.from(context);
           convertView = layoutInflater.inflate(R.layout.image_text_item_layout,null);
       }
       final ImageView imageView = (ImageView)convertView.findViewById(R.id.image_text_image);
       final TextView nameTextView = (TextView)convertView.findViewById(R.id.image_text_text);

       imageView.setImageResource(category.getCategorySource());
       nameTextView.setText(category.getCategoryName());
       return convertView;
    }
}
