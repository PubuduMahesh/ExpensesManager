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
           convertView = layoutInflater.inflate(R.layout.category_item_layout,null);
       }
       final ImageView imageView = (ImageView)convertView.findViewById(R.id.category_image);
       final TextView nameTextView = (TextView)convertView.findViewById(R.id.text_category_name);

       imageView.setImageResource(category.getCategorySource());
       nameTextView.setText(category.getCategoryName());
       return convertView;
    }
}
