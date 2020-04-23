package com.codenerdz.expensesmanager.activity.balance;

import android.content.Context;
import android.database.Cursor;

import com.codenerdz.expensesmanager.activity.analysor.AnalysorDBAdapter;
import com.codenerdz.expensesmanager.toolkit.DBAdapterTollkit;

public class BalanceDBAdapter {
    private static class singeltonHolder
    {
        public static BalanceDBAdapter instance = new BalanceDBAdapter();
    }

    private BalanceDBAdapter()
    {

    }

    public static BalanceDBAdapter getInstance()
    {
        return singeltonHolder.instance;
    }

    public void fetchAllExpensesForBalance(Context context)
    {
        final String query = "SELECT \n" +
                "\te.sponsor,\n" +
                "\ts.spenderName as sponsorName,\n" +
                "\te.categoryID,\n" +
                "\tc.categoryName,\n" +
                "\te.paymentMethodID,\n" +
                "\tp.paymentMethodName,\n" +
                "\tp.sharedMethod,\n" +
                "\te.isSharedExpenditure,\n" +
                "\te.amount\n" +
                "\tFROM tbl_expenditure e \n" +
                "\t\tinner join tbl_spender s on e.sponsor = s._id \n" +
                "\t\tinner join tbl_category c on c._id = e.categoryID \n" +
                "\t\tinner join tbl_payment_method p on p._id = e.paymentMethodID\n" +
                "\tgroup by e.spender,e.categoryID,e.paymentMethodID,e.isSharedExpenditure\n" +
                "\torder by \tspender,categoryID,paymentMethodID asc\n" +
                "\t";
        Cursor expenseAnalysorCursor = DBAdapterTollkit.getInstance().
                open(context).rawQuery(query,null);
        if(expenseAnalysorCursor != null)
        {
            expenseAnalysorCursor.moveToFirst();
        }
        DBAdapterTollkit.getInstance().close();
        //return getExpensesAnalysorArray(expenseAnalysorCursor);
    }



}
