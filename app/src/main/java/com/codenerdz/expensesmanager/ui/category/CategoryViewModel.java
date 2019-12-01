package com.codenerdz.expensesmanager.ui.category;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CategoryViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private ArrayList<Category> categoryList;

    public CategoryViewModel() {

    }

    public LiveData<String> getText() {
        mText = new MutableLiveData<>();
        mText.setValue("This is category fragment");
        return mText;
    }

    public ArrayList<Category> getCategoryList(){
        /*temporary category list*/
        categoryList = new ArrayList<>();
        Category category1 = new Category();
        Category category2 = new Category();
        /*data feeding for category items*/
        category1.setCategoryName("Keels");
        category1.setCategoryID(1);
        category2.setCategoryName("Fuel");
        category2.setCategoryID(2);
        /*adding category items to list.*/

        categoryList.add(category2);
        return categoryList;
    }
}