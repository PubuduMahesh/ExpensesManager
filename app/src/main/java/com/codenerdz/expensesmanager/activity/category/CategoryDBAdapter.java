package com.codenerdz.expensesmanager.activity.category;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.codenerdz.expensesmanager.toolkit.category.CategoryDBToolkit;
import com.codenerdz.expensesmanager.toolkit.DBAdapterTollkit;
import com.codenerdz.expensesmanager.toolkit.expense.ExpenditureDBToolkit;

import java.util.ArrayList;
import java.util.List;

public class CategoryDBAdapter {

    /**
     * this static class will be used to implement Singelton design pattern.
     */
    private static class SingeltonHolder
    {
        public static CategoryDBAdapter instance = new CategoryDBAdapter();
    }

    private CategoryDBAdapter()
    {

    }

    /**
     * @return Only one instance for this class.
     */
    public static CategoryDBAdapter getInstance()
    {
        return SingeltonHolder.instance;
    }

    /**
     * this method will be used to save newly crated category object in db.
     * @param category Category object which is needed to save in db.
     * @param context relevant android context which is used to access the db.
     * @return long value will be returned if the db save action is succeeded.
     */
    public long createCategory(Category category, Context context)
    {
        long returnValue;
        ContentValues values = getContentValues(category);
        returnValue = DBAdapterTollkit.getInstance().open(context).
                insert(CategoryDBToolkit.CATEGORY_TABLE_NAME,null,values);
        DBAdapterTollkit.getInstance().close();
        return returnValue;
    }

    /**
     * will be used to generate ContentValues object according to the category object's attributes.
     * @param category which is going to be saved in db.
     * @return updated content values related to the category attributes.
     */
    private ContentValues getContentValues(Category category) {
        ContentValues values = new ContentValues();
        values.put(CategoryDBToolkit.COL_CATEGORY_NAME,category.getCategoryName());
        values.put(CategoryDBToolkit.COL_CATEGORY_IMAGE_SOURCE,category.getCategoryImageSource());
        return values;
    }

    public Category fetchCategoryByID(int categoryID, Context context)
    {
        Cursor categoryCursor = DBAdapterTollkit.getInstance().open(context)
                .query(CategoryDBToolkit.CATEGORY_TABLE_NAME,getColumnsList(),
                        CategoryDBToolkit.COL_CATEGORY_ID+" =?",new String[]
                                {String.valueOf(categoryID)},null,null,null);
        if(categoryCursor != null)
        {
            categoryCursor.moveToFirst();
        }
        DBAdapterTollkit.getInstance().close();
        return getCategoryObject(categoryCursor);

    }

    /**
     * This method will be used to fetch all registered categories from db.
     * @param context android context which is going to access db
     * @return retrieved category array will be returned.
     */
    public Category[] fetchAllCategory(Context context)
    {
        Cursor categoryCursor = DBAdapterTollkit.getInstance().
                open(context).query(CategoryDBToolkit.CATEGORY_TABLE_NAME, getColumnsList(),
                null,null,null,null,null);
        if(categoryCursor != null)
        {
            categoryCursor.moveToFirst();
        }
        DBAdapterTollkit.getInstance().close();
        return getCategoryArray(categoryCursor);

    }

    private String[] getColumnsList() {
        return new String[]
        {
                CategoryDBToolkit.COL_CATEGORY_ID,
                CategoryDBToolkit.COL_CATEGORY_NAME,
                CategoryDBToolkit.COL_CATEGORY_IMAGE_SOURCE
        };
    }

    /**
     * This method is used to convert DB cursor values to Category array.
     * @param cursor retrieved from db
     * @return fetched category array
     */
    private Category[] getCategoryArray(Cursor cursor)
    {
        List<Category>categoryList = new ArrayList<>();
        if(cursor != null)
        {
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext())
            {
                categoryList.add(getCategoryObject(cursor));
            }
        }
        Category[] categoryArray = new Category[categoryList.size()];
        return categoryList.toArray(categoryArray);
    }

    /**
     * This method is used to generate category object from cursor.
     * @param cursor retrieved from db
     * @return updated category object
     */
    private Category getCategoryObject(Cursor cursor)
    {
        Category category  = new Category();
        category.setCategoryID
            (cursor.getInt(cursor.getColumnIndex(CategoryDBToolkit.COL_CATEGORY_ID)));
        category.setCategoryName
                (cursor.getString
                        (cursor.getColumnIndex(CategoryDBToolkit.COL_CATEGORY_NAME)));
        category.setCategoryImageSource
                (cursor.getInt
                        (cursor.getColumnIndex(CategoryDBToolkit.COL_CATEGORY_IMAGE_SOURCE)));
        return category;
    }

    public void deleteCategoryById(Context context,Category category) {
        DBAdapterTollkit.getInstance().open(context).delete(
                CategoryDBToolkit.CATEGORY_TABLE_NAME,CategoryDBToolkit.COL_CATEGORY_ID +
                        "=?",new String[]{String.valueOf(category.getCategoryID())});
    }
}
