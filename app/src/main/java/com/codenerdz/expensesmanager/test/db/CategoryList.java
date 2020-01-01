package com.codenerdz.expensesmanager.test.db;

import com.codenerdz.expensesmanager.R;
import com.codenerdz.expensesmanager.activity.category.Category;

public class CategoryList {

    private static class SingeltonHolder
    {
        public static CategoryList instance = new CategoryList();
    }

    private CategoryList()
    {

    }

    public static CategoryList getInstance()
    {
        return SingeltonHolder.instance;
    }

    public Category[] getCategoryList()
    {
        Category category1=new Category("Keels",1, R.drawable.image_1);
        Category category2=new Category("Sanitory",2,R.drawable.image_2);
        Category category3=new Category("Fuel",3,R.drawable.image_3);
        Category category4=new Category("Gift",4,R.drawable.image_1);
        Category category5=new Category("Sanitory",2,R.drawable.image_2);
        Category category6=new Category("Fuel",3,R.drawable.image_3);
        Category category7=new Category("Gift",4,R.drawable.image_1);
        Category category8=new Category("Sanitory",2,R.drawable.image_2);
        Category category9=new Category("Fuel",3,R.drawable.image_3);
        Category category10=new Category("Gift",4,R.drawable.image_1);
        Category[] categories = {category1,category2,category3,category4,category5,category6,
                category7,category8,category9,category10};
        return categories;
    }
}
