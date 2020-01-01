package com.codenerdz.expensesmanager.toolkit.spender;

public class SpenderDBToolkit
{
    private static final SpenderDBToolkit ourInstance = new SpenderDBToolkit();

    public static SpenderDBToolkit getInstance() {
        return ourInstance;
    }

    private SpenderDBToolkit() {
    }

    public static final String SPENDER_TABLE_NAME = "tbl_spender";
    public static final String COL_SPENDER_ID = "_id";
    public static final String COL_SPENDER_NAME = "spenderName";
    public static final String COL_SPENDER_IMAGE_SOURCE = "spenderImageSource";


    public String getSpenderTableQueryString()
    {
        return "CREATE TABLE IF NOT EXISTS " + SPENDER_TABLE_NAME + " ( " +
                COL_SPENDER_ID + " INTEGER PRIMARY KEY autoincrement, " +
                COL_SPENDER_NAME + " TEXT, " +
                COL_SPENDER_IMAGE_SOURCE + " INTEGER );";
    }
}
