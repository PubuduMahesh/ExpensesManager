package com.codenerdz.expensesmanager.activity.Expense;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.codenerdz.expensesmanager.activity.spender.Spender;
import com.codenerdz.expensesmanager.toolkit.DBAdapterTollkit;
import com.codenerdz.expensesmanager.toolkit.expense.ExpenditureDBToolkit;

import java.util.ArrayList;
import java.util.List;

public class ExpenditureDBAdapter
{
    private static class SingeltonHolder
    {
        public static ExpenditureDBAdapter instance = new ExpenditureDBAdapter();
    }

    private ExpenditureDBAdapter()
    {

    }

    public static ExpenditureDBAdapter getInstance()
    {
        return SingeltonHolder.instance;
    }

    public long createExpense(Expense expense, Context context)
    {
        long returnValue;
        ContentValues contentValues = new ContentValues();
        contentValues.put(ExpenditureDBToolkit.
                COL_EXPENDITURE_DESCRIPTION,expense.getExpenditureDescription());
        contentValues.put(ExpenditureDBToolkit.
                COL_EXPENDITURE_AMOUNT,expense.getExpenditureAmount());
        contentValues.put(ExpenditureDBToolkit.
                COL_SPENDER,expense.getExpenser());
        contentValues.put(ExpenditureDBToolkit.
                COL_IS_SHARED_EXPENDITURE,expense.isSharedExpenditure());
        contentValues.put(ExpenditureDBToolkit.
                COL_EXPENDITURE_CATEGORY,expense.getExpenditureCategory());
        returnValue = DBAdapterTollkit.getInstance().open(context).insert(ExpenditureDBToolkit.
                EXPENDITURE_TABLE_NAME,null,contentValues);
        DBAdapterTollkit.getInstance().close();
        return returnValue;
    }

    public Expense[] fetchAllExpenses(Context context)
    {
        Cursor expenseCursor = DBAdapterTollkit.getInstance().
                open(context).query(ExpenditureDBToolkit.EXPENDITURE_TABLE_NAME,
                ExpenseTableColumnArray(),null,null,null,null,
                null);
        if(expenseCursor != null)
        {
            expenseCursor.moveToFirst();
        }
        DBAdapterTollkit.getInstance().close();
        return getExpensesArray(expenseCursor);

    }

    private String[] ExpenseTableColumnArray()
    {
        return new String[]
                {
                        ExpenditureDBToolkit.COL_EXPENDITURE_AMOUNT,
                        ExpenditureDBToolkit.COL_EXPENDITURE_CATEGORY,
                        ExpenditureDBToolkit.COL_EXPENDITURE_DESCRIPTION,
                        ExpenditureDBToolkit.COL_EXPENDITURE_ID,
                        ExpenditureDBToolkit.COL_SPENDER,
                        ExpenditureDBToolkit.COL_IS_SHARED_EXPENDITURE
                };
    }

    public Expense[] fetchAllExpensesBySpender(Context context, Spender spender)
    {
        Cursor expenseCursor = DBAdapterTollkit.getInstance().
                open(context).query(ExpenditureDBToolkit.EXPENDITURE_TABLE_NAME,
                ExpenseTableColumnArray(),ExpenditureDBToolkit.COL_SPENDER+"=?",
                new String[]{spender.getSpenderID()+""},null,null,
                null);
        return getExpensesArray(expenseCursor);
    }

    private Expense[] getExpensesArray(Cursor cursor)
    {
        List<Expense> expensesArrayList = new ArrayList<>();
        if(cursor != null)
        {
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext())
            {
                Expense expense  = new Expense();
                expense.setExpenseID
                        (cursor.getInt(cursor.getColumnIndex(ExpenditureDBToolkit.COL_EXPENDITURE_ID)));
                expense.setExpenditureDescription
                        (cursor.getString
                                (cursor.getColumnIndex(ExpenditureDBToolkit.COL_EXPENDITURE_DESCRIPTION)));
                expense.setExpenditureAmount
                        (cursor.getInt
                                (cursor.getColumnIndex(ExpenditureDBToolkit.COL_EXPENDITURE_AMOUNT)));
                expense.setExpenditureCategory(cursor.getInt(cursor.getColumnIndex(ExpenditureDBToolkit.COL_EXPENDITURE_CATEGORY)));
                expense.setExpenser(cursor.getInt(cursor.getColumnIndex(ExpenditureDBToolkit.COL_SPENDER)));
                expensesArrayList.add(expense);
            }
        }
        Expense[] expensesArray = new Expense[expensesArrayList.size()];
        return expensesArrayList.toArray(expensesArray);
    }



}
