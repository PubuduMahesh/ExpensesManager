package com.codenerdz.expensesmanager.toolkit;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.codenerdz.expensesmanager.toolkit.category.CategoryDBToolkit;

public class DBAdapterTollkit{

    private static final int DATABASE_VERSION = 1;
    private String TAG;
    private Context context;
    private SQLiteDatabase dbConnection;
    private DatabaseHelper dbHelper;
    private static final String DATABASE_NAME = "dba_expenses_manager";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS ";

    private static class SingeltonHolder
    {
        public static DBAdapterTollkit instance = new DBAdapterTollkit();
    }

    private DBAdapterTollkit()
    {
    }

    public static DBAdapterTollkit getInstance()
    {
        return SingeltonHolder.instance;
    }

    private static class DatabaseHelper extends SQLiteOpenHelper
    {

        public DatabaseHelper(@Nullable Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CategoryDBToolkit.getInstance().getCategoryTableQueryString());
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            db.execSQL(DROP_TABLE+ CategoryDBToolkit.getInstance().TABLE_NAME);
            onCreate(db);
        }
    }


    public SQLiteDatabase open(Context context)
    {
        if(dbConnection == null || !dbConnection.isOpen())
        {
            if(dbHelper == null)
            {
                dbHelper = new DatabaseHelper(context);
            }
            dbConnection = dbHelper.getWritableDatabase();
        }
        return dbConnection;
    }
    /**
     * Database Connection closing.
     */
    public void close()
    {
        if(dbConnection != null)
        {
            dbConnection.close();
        }
    }
}
