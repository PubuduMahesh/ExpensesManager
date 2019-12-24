package com.codenerdz.expensesmanager.activity.category;

import androidx.fragment.app.FragmentActivity;

public class CategoryActivity extends FragmentActivity {

    private CategoryActivity()
    {

    }

    private static class SingletonHolder {
        public static final CategoryActivity instance = new CategoryActivity();
    }

    public static CategoryActivity getInstance() {
        return SingletonHolder.instance;
    }


}
