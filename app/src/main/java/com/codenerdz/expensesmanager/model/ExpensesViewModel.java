package com.codenerdz.expensesmanager.model;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.codenerdz.expensesmanager.activity.expense.Expense;

import java.util.ArrayList;
import java.util.List;

public class ExpensesViewModel extends ViewModel
{
    private MutableLiveData<List<Expense>> mutableLiveExpensesData = new MutableLiveData<>();
    private List<Expense> expensesList = new ArrayList<>();

    public void updateExpensesInList(Expense expense)
    {
        for(Expense item:expensesList)
        {
            if(item.getExpenseID() == expense.getExpenseID())
            {
                expensesList.remove(item);
                mutableLiveExpensesData.setValue(expensesList);
                return;
            }
        }
        expensesList.add(expense);
        mutableLiveExpensesData.setValue(expensesList);
    }

    public void clearExpensesList()
    {
        expensesList.clear();
        mutableLiveExpensesData.setValue(expensesList);
    }

    public void removeExpenseFromExpensesList(Expense expense)
    {
        expensesList.remove(expense);
        mutableLiveExpensesData.setValue(expensesList);
    }

    public MutableLiveData<List<Expense>> getExpensesList()
    {
        return mutableLiveExpensesData;
    }
}
