package com.codenerdz.expensesmanager.test.db;

import com.codenerdz.expensesmanager.R;

public class CategoryImageList {
    private static class SingeltonHolder
    {
        public static CategoryImageList instance = new CategoryImageList();
    }

    private CategoryImageList(){}

    public static CategoryImageList getInstance()
    {
        return SingeltonHolder.instance;
    }

    public int[] getImageList()
    {
        int[] imageList = {R.drawable.category1,R.drawable.category2,R.drawable.category3,
                R.drawable.category4,R.drawable.category5,R.drawable.category6,R.drawable.category7,
                R.drawable.category8,R.drawable.category9,R.drawable.category10,
                R.drawable.category11,R.drawable.category12,R.drawable.category13,
                R.drawable.category14,R.drawable.category15,R.drawable.category16,
                R.drawable.category17,R.drawable.category18,R.drawable.category19,
                R.drawable.category20,R.drawable.category21,R.drawable.category22,
                R.drawable.category23
        };
        return imageList;
    }
}
