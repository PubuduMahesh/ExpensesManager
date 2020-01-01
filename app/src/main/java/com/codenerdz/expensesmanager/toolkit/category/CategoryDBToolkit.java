package com.codenerdz.expensesmanager.toolkit.category;

public class CategoryDBToolkit {
    private static final CategoryDBToolkit ourInstance = new CategoryDBToolkit();

    public static CategoryDBToolkit getInstance() {
        return ourInstance;
    }

    private CategoryDBToolkit() {
    }

    public static final String TABLE_NAME = "tbl_category";
    public static final String COL_CATEGORY_ID = "_id";
    public static final String COL_CATEGORY_NAME = "categoryName";
    public static final String COL_CATEGORY_IMAGE_SOURCE = "categoryImageSource";


    public String getCategoryTableQueryString()
    {
        return "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ( " +
                COL_CATEGORY_ID + " INTEGER PRIMARY KEY autoincrement, " +
                COL_CATEGORY_NAME + " TEXT, " +
                COL_CATEGORY_IMAGE_SOURCE + " INTEGER );";
    }
}
