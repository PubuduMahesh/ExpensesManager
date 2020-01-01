package com.codenerdz.expensesmanager.activity.category;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleCursorAdapter;

import com.codenerdz.expensesmanager.toolkit.category.CategoryDBToolkit;

public class CategoryDBCursorAdapter extends SimpleCursorAdapter
{

    public CategoryDBCursorAdapter
            (Context context, int layout, Cursor c, String[] from, int[] to, int flags)
    {
        super(context, layout, c, from, to, flags);
    }

    @Override
    public ViewBinder getViewBinder()
    {
        return super.getViewBinder();
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return super.newView(context, cursor, parent);
    }

    @Override
    public void bindView(View view,Context context, Cursor cursor)
    {
        super.bindView(view,context,cursor);
        ViewHolder holder = (ViewHolder)view.getTag();
        if(holder == null)
        {
            holder = new ViewHolder();
            holder.categoryName =
                    cursor.getColumnIndexOrThrow(CategoryDBToolkit.COL_CATEGORY_NAME);
            view.setTag(holder);
        }
    }

    static class ViewHolder
    {
        int categoryName;
    }
}
