package com.codenerdz.expensesmanager.test.db;

import com.codenerdz.expensesmanager.R;

public class SpenderImageList
{
    private static class SingeltonHolder
    {
        public static SpenderImageList instance = new SpenderImageList();
    }

    private SpenderImageList(){}

    public static SpenderImageList getInstance()
    {
        return SingeltonHolder.instance;
    }

    public int[] getImageList()
    {
        int[] imageList = {R.drawable.spender1,R.drawable.spender2,R.drawable.spender3,
                R.drawable.spender4,R.drawable.spender5,R.drawable.spender6,R.drawable.spender7,
                R.drawable.spender8,R.drawable.spender9,R.drawable.spender10
        };
        return imageList;
    }
}
