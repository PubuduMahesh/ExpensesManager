package com.codenerdz.expensesmanager.activity.expense;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;

import com.codenerdz.expensesmanager.activity.spender.Spender;
import com.codenerdz.expensesmanager.toolkit.DBAdapterTollkit;
import com.codenerdz.expensesmanager.toolkit.expense.ExpenditureDBToolkit;

import java.util.ArrayList;
import java.util.Collections;
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
                COL_EXPENDITURE_CATEGORY,expense.getExpenditureCategoryID());
        contentValues.put(ExpenditureDBToolkit.
                COL_EXPENDITURE_PAYMENT_METHOD,expense.getExpenditurePaymentMethodID());
        contentValues.put(ExpenditureDBToolkit.COL_EXPENDITURE_DATE,expense.getExpenseDate());
        returnValue = addOrUpdateExpense(context, contentValues,expense);
        DBAdapterTollkit.getInstance().close();
        return returnValue;
    }

    private long addOrUpdateExpense(Context context, ContentValues contentValues,Expense expense) {
        long returnValue;
        if(expense.getExpenseID() == -1)
        {
        returnValue = DBAdapterTollkit.getInstance().open(context).insert(ExpenditureDBToolkit.
                EXPENDITURE_TABLE_NAME,null,contentValues);
        }
        else
        {
            contentValues.put(ExpenditureDBToolkit.
                    COL_EXPENDITURE_ID,String.valueOf(expense.getExpenseID()));
            returnValue = DBAdapterTollkit.getInstance().open(context).update(ExpenditureDBToolkit.
                    EXPENDITURE_TABLE_NAME,contentValues,createWhereClauseForUpdateItemList(),
                    new String[]{Integer.toString(expense.getExpenseID())});
        }
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
                        ExpenditureDBToolkit.COL_IS_SHARED_EXPENDITURE,
                        ExpenditureDBToolkit.COL_EXPENDITURE_PAYMENT_METHOD,
                        ExpenditureDBToolkit.COL_EXPENDITURE_DATE
                };
    }

    /**
     * To fetch all expenses according to the given user and date.
     * @param context context for current view
     * @param spender responsible spender for retrieving expenses.
     * @param date related minimum milliseconds of the date and
     *            maximum milliseconds of the date of retrieving expenses.
     * @return will be returned retrieved expenses array
     */
    public Expense[] fetchAllExpensesBySpenderDate(Context context, Spender spender, long[] date)
    {
        Cursor expenseCursor = DBAdapterTollkit.getInstance().
                open(context).query(ExpenditureDBToolkit.EXPENDITURE_TABLE_NAME,
                ExpenseTableColumnArray(),ExpenditureDBToolkit.COL_SPENDER+"=? AND "+
                ExpenditureDBToolkit.COL_EXPENDITURE_DATE+">? AND "+
                ExpenditureDBToolkit.COL_EXPENDITURE_DATE+"<?",
                new String[]{spender.getSpenderID()+"",Long.toString(date[0]),
                        Long.toString(date[1])},null,null,
                null);
        return getExpensesArray(expenseCursor);
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
                        (cursor.getInt(cursor.getColumnIndex(
                                ExpenditureDBToolkit.COL_EXPENDITURE_ID)));
                expense.setExpenditureDescription
                        (cursor.getString
                                (cursor.getColumnIndex(
                                        ExpenditureDBToolkit.COL_EXPENDITURE_DESCRIPTION)));
                expense.setExpenditureAmount
                        (cursor.getInt
                                (cursor.getColumnIndex(
                                        ExpenditureDBToolkit.COL_EXPENDITURE_AMOUNT)));
                expense.setExpenditureCategoryID(cursor.getInt(cursor.getColumnIndex(
                        ExpenditureDBToolkit.COL_EXPENDITURE_CATEGORY)));
                expense.setExpenser(cursor.getInt(cursor.getColumnIndex(
                        ExpenditureDBToolkit.COL_SPENDER)));
                expense.setExpenditurePaymentMethodID(cursor.getInt(cursor.getColumnIndex
                        (ExpenditureDBToolkit.COL_EXPENDITURE_PAYMENT_METHOD)));
                expense.setExpenseDate(cursor.getLong(cursor.getColumnIndex
                        (ExpenditureDBToolkit.COL_EXPENDITURE_DATE)));
                expense.setSharedExpenditure(cursor.getInt(cursor.getColumnIndex
                        (ExpenditureDBToolkit.COL_IS_SHARED_EXPENDITURE))==1?true:false);
                expensesArrayList.add(expense);
            }
        }
        Expense[] expensesArray = new Expense[expensesArrayList.size()];
        return expensesArrayList.toArray(expensesArray);
    }

    public void deleteSelectedExpenses(Context context, List<Expense> expenseList)
    {
        createDeletingIDList(expenseList);
        DBAdapterTollkit.getInstance().open(context).delete(
                ExpenditureDBToolkit.EXPENDITURE_TABLE_NAME, createWhereClauseForDeleteItemList(
                        expenseList.size()),createDeletingIDList(expenseList));
    }

    private String[] createDeletingIDList(List<Expense> expenseList) {
        List<String> idList = new ArrayList<>();
        for(Expense expense : expenseList)
        {
            idList.add(Integer.toString(expense.getExpenseID()));
        }
        return idList.toArray(new String[expenseList.size()]);
    }

    private String createWhereClauseForDeleteItemList(int size)
    {
        return  String.format(ExpenditureDBToolkit.COL_EXPENDITURE_ID + " IN (%s)", new Object[]{
                TextUtils.join(",", Collections.nCopies(size, "?")) });
    }

    private String createWhereClauseForUpdateItemList()
    {
        return ExpenditureDBToolkit.COL_EXPENDITURE_ID + " =? ";
    }



}
