package com.codenerdz.expensesmanager.activity.category;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.codenerdz.expensesmanager.toolkit.category.CategoryDBToolkit;
import com.codenerdz.expensesmanager.toolkit.DBAdapterTollkit;

import java.util.ArrayList;
import java.util.List;

public class CategoryDBAdapter {

    private static class SingeltonHolder
    {
        public static CategoryDBAdapter instance = new CategoryDBAdapter();
    }

    private CategoryDBAdapter()
    {

    }

    public static CategoryDBAdapter getInstance()
    {
        return SingeltonHolder.instance;
    }

    public long createCategory(Category category, Context context)
    {
        long returnValue;
        ContentValues values = new ContentValues();
        values.put(CategoryDBToolkit.COL_CATEGORY_NAME,category.getCategoryName());
        values.put(CategoryDBToolkit.COL_CATEGORY_IMAGE_SOURCE,category.getCategorySource());
        returnValue = DBAdapterTollkit.getInstance().
                open(context).insert(CategoryDBToolkit.CATEGORY_TABLE_NAME,null,values);
        DBAdapterTollkit.getInstance().close();
        return returnValue;
    }

    public Category[] fetchAllCategory(Context context)
    {
        Cursor categoryCursor = DBAdapterTollkit.getInstance().
                open(context).query(CategoryDBToolkit.CATEGORY_TABLE_NAME,new String[]
                {
                        CategoryDBToolkit.COL_CATEGORY_ID,
                        CategoryDBToolkit.COL_CATEGORY_NAME,
                        CategoryDBToolkit.COL_CATEGORY_IMAGE_SOURCE
                },
                null,null,null,null,null);
        if(categoryCursor != null)
        {
            categoryCursor.moveToFirst();
        }
        DBAdapterTollkit.getInstance().close();
        return getCategoryArray(categoryCursor);

    }

    private Category[] getCategoryArray(Cursor cursor)
    {
        List<Category>categoryList = new ArrayList<>();
        if(cursor != null)
        {
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext())
            {
                Category category  = new Category();
                category.setCategoryID
                    (cursor.getInt(cursor.getColumnIndex(CategoryDBToolkit.COL_CATEGORY_ID)));
                category.setCategoryName
                        (cursor.getString
                                (cursor.getColumnIndex(CategoryDBToolkit.COL_CATEGORY_NAME)));
                category.setCategorySource
                        (cursor.getInt
                                (cursor.getColumnIndex(CategoryDBToolkit.COL_CATEGORY_IMAGE_SOURCE)));
                categoryList.add(category);
            }
        }
        Category[] categoryArray = new Category[categoryList.size()];
        return categoryList.toArray(categoryArray);
    }
}
