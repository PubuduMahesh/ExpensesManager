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
        int[] imageList = {R.drawable.image_1,R.drawable.image_2,R.drawable.image_4,
                R.drawable.image_5,R.drawable.image_6,R.drawable.image_7,R.drawable.image_8,
                R.drawable.image_9,R.drawable.image_10};
        return imageList;
    }
}
