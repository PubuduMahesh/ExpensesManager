package com.codenerdz.expensesmanager.test.db;

import com.codenerdz.expensesmanager.activity.Expense.Expense;

import java.util.ArrayList;
import java.util.List;

public class ExpnsesList
{
    private static class SingeltonHolder
    {
        public static ExpnsesList instance = new ExpnsesList();
    }
    private ExpnsesList()
    {

    }

    public static ExpnsesList getInstance()
    {
        return SingeltonHolder.instance;
    }

    public Expense[] getExpensesList()
    {
        Expense expense1 = new Expense();
        expense1.setSharedExpenditure(true);
        expense1.setExpenditureAmount(1000);
        expense1.setExpenditureDescription("Keels");
        expense1.setExpenditureCategory(1);
        expense1.setExpenser(2);
        Expense[] expensesArray = {expense1,expense1,expense1,expense1,expense1,expense1,expense1,expense1,expense1,expense1,expense1,expense1,expense1,expense1,expense1,expense1,expense1,expense1,expense1,expense1,expense1,expense1,expense1,expense1,expense1,expense1,expense1,expense1,expense1};
        return expensesArray;
    }
}
