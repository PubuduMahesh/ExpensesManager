package com.codenerdz.expensesmanager.ui.spender;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SpenderViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SpenderViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is spender fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}