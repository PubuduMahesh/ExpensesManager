package com.codenerdz.expensesmanager.activity.category;

import java.sql.Blob;

/**
 * Model Class for Category.
 */
public class Category {
    private String categoryName;
    private int categoryID;
    private int categorySource;

    public Category()
    {

    }

    public int getCategorySource() {
        return categorySource;
    }

    public void setCategorySource(int categorySource) {
        this.categorySource = categorySource;
    }

    public Category(String categoryName, int categoryID, int categorySource)
    {
        this.categoryName = categoryName;
        this.categoryID = categoryID;
        this.categorySource = categorySource;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}