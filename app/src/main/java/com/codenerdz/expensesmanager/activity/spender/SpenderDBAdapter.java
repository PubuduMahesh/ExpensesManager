package com.codenerdz.expensesmanager.activity.spender;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.codenerdz.expensesmanager.activity.category.Category;
import com.codenerdz.expensesmanager.toolkit.DBAdapterTollkit;
import com.codenerdz.expensesmanager.toolkit.category.CategoryDBToolkit;
import com.codenerdz.expensesmanager.toolkit.spender.SpenderDBToolkit;

import java.util.ArrayList;
import java.util.List;

public class SpenderDBAdapter
{
    private static class SingeltonHolder
    {
        public static SpenderDBAdapter instance = new SpenderDBAdapter();
    }

    private SpenderDBAdapter()
    {

    }

    public static SpenderDBAdapter getInstance()
    {
        return SpenderDBAdapter.SingeltonHolder.instance;
    }

    public long createSpender(Spender spender, Context context)
    {
        long returnValue;
        ContentValues values = new ContentValues();
        values.put(SpenderDBToolkit.COL_SPENDER_NAME,spender.getSpenderName());
        values.put(SpenderDBToolkit.COL_SPENDER_IMAGE_SOURCE,spender.getSpenderImageSource());
        returnValue = DBAdapterTollkit.getInstance().
                open(context).insert(SpenderDBToolkit.SPENDER_TABLE_NAME,null,values);
        DBAdapterTollkit.getInstance().close();
        return returnValue;
    }

    public Spender[] fetchAllSpenders(Context context)
    {
        Cursor spenderCursor = DBAdapterTollkit.getInstance().
                open(context).query(SpenderDBToolkit.SPENDER_TABLE_NAME,new String[]
                        {
                                SpenderDBToolkit.COL_SPENDER_ID,
                                SpenderDBToolkit.COL_SPENDER_NAME,
                                SpenderDBToolkit.COL_SPENDER_IMAGE_SOURCE
                        },
                null,null,null,null,null);
        if(spenderCursor != null)
        {
            spenderCursor.moveToFirst();
        }
        DBAdapterTollkit.getInstance().close();
        return getSpendersArray(spenderCursor);

    }

    private Spender[] getSpendersArray(Cursor cursor)
    {
        List<Spender> spenderList = new ArrayList<>();
        if(cursor != null)
        {
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext())
            {
                Spender spender  = new Spender();
                spender.setSpenderID
                        (cursor.getInt(cursor.getColumnIndex(SpenderDBToolkit.COL_SPENDER_ID)));
                spender.setSpenderName
                        (cursor.getString
                                (cursor.getColumnIndex(SpenderDBToolkit.COL_SPENDER_NAME)));
                spender.setSpenderImageSource
                        (cursor.getInt(cursor.
                                getColumnIndex(SpenderDBToolkit.COL_SPENDER_IMAGE_SOURCE)));
                spenderList.add(spender);
            }
        }
        Spender[] spenderArray = new Spender[spenderList.size()];
        return spenderList.toArray(spenderArray);
    }
}
