package com.codenerdz.expensesmanager.activity.analysor;

import android.content.Context;
import android.database.Cursor;

import com.codenerdz.expensesmanager.activity.expense.Expense;
import com.codenerdz.expensesmanager.toolkit.DBAdapterTollkit;
import com.codenerdz.expensesmanager.toolkit.category.CategoryDBToolkit;
import com.codenerdz.expensesmanager.toolkit.expense.ExpenditureDBToolkit;
import com.codenerdz.expensesmanager.toolkit.payment_method.PaymentMethodDBToolkit;
import com.codenerdz.expensesmanager.toolkit.spender.SpenderDBToolkit;

import java.util.ArrayList;
import java.util.List;

public class AnalysorDBAdapter {
    public static class SingeltonHolder
    {
        public static AnalysorDBAdapter instance = new AnalysorDBAdapter();
    }

    private AnalysorDBAdapter()
    {

    }

    public static AnalysorDBAdapter getInstance()
    {
        return SingeltonHolder.instance;
    }

    public Analysor[] fetchAllExpensesDataForAnalysor(Context context)
    {
        final String query = "SELECT \n" +
                "\te.spender,\n" +
                "\ts.spenderName,\n" +
                "\te.categoryID,\n" +
                "\tc.categoryName,\n" +
                "\te.paymentMethodID,\n" +
                "\tp.paymentMethodName,\n" +
                "\te.isSharedExpenditure,\n" +
                "\tsum(e.amount) as amount\n" +
                "\tFROM tbl_expenditure e \n" +
                "\t\tinner join tbl_spender s on e.spender = s._id \n" +
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
            return getExpensesAnalysorArray(expenseAnalysorCursor);
    }

    private Analysor[] getExpensesAnalysorArray(Cursor cursor)
    {
        List<Analysor> expensesAnalysorArrayList = new ArrayList<>();
        if(cursor != null)
        {
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext())
            {
                Analysor analysor  = new Analysor();

                analysor.setExpenser(cursor.getInt(cursor.getColumnIndex(
                        ExpenditureDBToolkit.COL_SPENDER)));
                analysor.setSpenderName(cursor.getString(cursor.getColumnIndex(
                        SpenderDBToolkit.COL_SPENDER_NAME)));
                analysor.setExpenditureCategoryID(cursor.getInt(cursor.getColumnIndex(
                        ExpenditureDBToolkit.COL_EXPENDITURE_CATEGORY)));
                analysor.setCategoryName(cursor.getString(cursor.getColumnIndex(
                        CategoryDBToolkit.COL_CATEGORY_NAME)));
                analysor.setExpenditurePaymentMethodID(cursor.getInt(cursor.getColumnIndex(
                        ExpenditureDBToolkit.COL_EXPENDITURE_PAYMENT_METHOD)));
                analysor.setPaymentMethodName(cursor.getString(cursor.getColumnIndex(
                        PaymentMethodDBToolkit.COL_PAYMENT_METHOD_NAME)));
                analysor.setSharedExpenditure(cursor.getInt(cursor.getColumnIndex
                        (ExpenditureDBToolkit.COL_IS_SHARED_EXPENDITURE))==1?true:false);
                analysor.setExpenditureAmount
                        (cursor.getInt
                                (cursor.getColumnIndex(
                                        ExpenditureDBToolkit.COL_EXPENDITURE_AMOUNT)));
                expensesAnalysorArrayList.add(analysor);
            }
        }
        Analysor[] expensesArray = new Analysor[expensesAnalysorArrayList.size()];
        return expensesAnalysorArrayList.toArray(expensesArray);
    }
}
