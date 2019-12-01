package com.codenerdz.expensesmanager.ui.category;

public class Category {
    String categoryName;
    int categoryID;
    int categorySource;

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
