package com.codenerdz.expensesmanager.model;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PaymentMethodExpenseViewModel<Item> extends ViewModel
{
    private final MutableLiveData<Item>selected = new MutableLiveData<Item>();
    public void selectItem(Item item)
    {
        selected.setValue(item);
    }

    public MutableLiveData<Item> getSelected()
    {
        return selected;
    }

    public Item getSelectedItem()
    {
        return selected.getValue();
    }
}
