package com.codenerdz.expensesmanager.activity.common;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.codenerdz.expensesmanager.R;

import java.util.Arrays;

public abstract class CommonItemAdapter <T> extends ArrayAdapter<T>
{
    public Context context;
    public T[] items;
    public CommonItemAdapter(Context context, T[] items)
    {
        super(context, R.layout.expenses_list_item_layout, Arrays.asList(items));
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount()
    {
        return items.length;
    }

    @Override
    public long getItemId(int position)
    {
        return 0;
    }

    @Override
    public T getItem(int position) {
        return items[position];
    }

    @Override
    public abstract View getView(int position, View convertView, ViewGroup parent);
}
