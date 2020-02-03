package com.codenerdz.expensesmanager.activity.category;

/**
 * Model Class for Category.
 */
public class Category {
    private String categoryName;
    private int categoryID;
    private int categoryImageSource;

    public Category()
    {

    }

    /**
     * @return integer value for selected image in res/drawable directory.
     */
    public int getCategoryImageSource()
    {
        return categoryImageSource;
    }

    /**
     * @param categoryImageSource integer value for selected image in res/drawable directory
     */
    public void setCategoryImageSource(int categoryImageSource)
    {
        this.categoryImageSource = categoryImageSource;
    }

    /**
     * separate constructor to manage category initialization.
     * @param categoryName string value for category name.
     * @param categoryID integer value for category id which will be generated in side the DB.
     * @param categorySource integer value for selected image in res/drawable directory.
     */
    public Category(String categoryName, int categoryID, int categorySource)
    {
        this.categoryName = categoryName;
        this.categoryID = categoryID;
        this.categoryImageSource = categorySource;
    }

    /**
     * @return integer value, this value will be automatically incremented in db level.
     */
    public int getCategoryID() {
        return categoryID;
    }

    /**
     * @param categoryID integer value to be set for category object which is generated in db.
     */
    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    /**
     * @return string value will be return as category name
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * @param categoryName
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
