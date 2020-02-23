package com.codenerdz.expensesmanager.activity.payment_method;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.codenerdz.expensesmanager.toolkit.DBAdapterTollkit;
import com.codenerdz.expensesmanager.toolkit.payment_method.PaymentMethodDBToolkit;

import java.util.ArrayList;
import java.util.List;

public class PaymentMethodDBAdapter
{
    private static class SingletonHolder
    {
        public static PaymentMethodDBAdapter instance = new PaymentMethodDBAdapter();
    }

    private PaymentMethodDBAdapter()
    {

    }

    public static PaymentMethodDBAdapter getInstance()
    {
        return SingletonHolder.instance;
    }

    public long createPaymentMethod(PaymentMethod pm, Context context)
    {
        long returnValue;
        ContentValues contentValues = new ContentValues();
        contentValues.put(PaymentMethodDBToolkit.COL_PAYMENT_METHOD_NAME,pm.getPaymentMethodName());
        contentValues.put(PaymentMethodDBToolkit.COL_PAYMENT_METHOD_IMAGE_SOURCE,
                pm.getPaymentMethodImageSource());
        contentValues.put(PaymentMethodDBToolkit.COL_PAYMENT_METHOD_IS_SHARED,
                pm.getIsSharedOption());
        returnValue = DBAdapterTollkit.getInstance().open(context).insert
                (PaymentMethodDBToolkit.PAYMENT_METHOD_TABLE_NAME,null,contentValues);
        DBAdapterTollkit.getInstance().close();
        return returnValue;
    }

    public PaymentMethod[] fetchAllPaymentMethods(Context context)
    {
        Cursor paymentMethodCursor = DBAdapterTollkit.getInstance().
                open(context).query(PaymentMethodDBToolkit.PAYMENT_METHOD_TABLE_NAME,getColumnsList(),
                null,null,null,null,null);
        if(paymentMethodCursor != null)
        {
            paymentMethodCursor.moveToFirst();
        }
        DBAdapterTollkit.getInstance().close();
        return getPaymentMethodsArray(paymentMethodCursor);

    }

    public PaymentMethod fetchPaymentMethodByID(int paymentMethodID,Context context)
    {
        Cursor paymentMethodCursor = DBAdapterTollkit.getInstance().open(context)
                .query(PaymentMethodDBToolkit.PAYMENT_METHOD_TABLE_NAME,getColumnsList(),
                        PaymentMethodDBToolkit.COL_PAYMENT_METHOD_ID+" =?",new String[]
                                {String.valueOf(paymentMethodID)},null,null,
                        null);
        if(paymentMethodCursor != null)
        {
            paymentMethodCursor.moveToFirst();
        }
        DBAdapterTollkit.getInstance().close();
        return createPaymentMethodObject(paymentMethodCursor);
    }

    private PaymentMethod[] getPaymentMethodsArray(Cursor cursor)
    {
        List<PaymentMethod> paymentMethodList = new ArrayList<>();
        if(cursor != null)
        {
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext())
            {
                paymentMethodList.add(createPaymentMethodObject(cursor));
            }
        }
        PaymentMethod[] paymentMethodArray = new PaymentMethod[paymentMethodList.size()];
        return paymentMethodList.toArray(paymentMethodArray);
    }

    private PaymentMethod createPaymentMethodObject(Cursor cursor) {
        PaymentMethod paymentMethod  = new PaymentMethod();
        paymentMethod.setPaymentMethodID(cursor.getInt
                (cursor.getColumnIndex(PaymentMethodDBToolkit.COL_PAYMENT_METHOD_ID)));
        paymentMethod.setPaymentMethodName(cursor.getString(cursor.getColumnIndex
                (PaymentMethodDBToolkit.COL_PAYMENT_METHOD_NAME)));
        paymentMethod.setPaymentMethodImageSource(cursor.getInt(cursor.
                getColumnIndex(PaymentMethodDBToolkit.COL_PAYMENT_METHOD_IMAGE_SOURCE)));
        paymentMethod.setSharedOption(cursor.getInt(cursor.getColumnIndex
                (PaymentMethodDBToolkit.COL_PAYMENT_METHOD_IS_SHARED))==1?true:false);
        return paymentMethod;
    }

    private String[] getColumnsList()
    {
        return new String[]
                {
                        PaymentMethodDBToolkit.COL_PAYMENT_METHOD_ID,
                        PaymentMethodDBToolkit.COL_PAYMENT_METHOD_NAME,
                        PaymentMethodDBToolkit.COL_PAYMENT_METHOD_IS_SHARED,
                        PaymentMethodDBToolkit.COL_PAYMENT_METHOD_IMAGE_SOURCE
                };
    }

    public void deletePaymentMethodByID(Context context, PaymentMethod paymentMethod) {
        DBAdapterTollkit.getInstance().open(context).delete(
                PaymentMethodDBToolkit.PAYMENT_METHOD_TABLE_NAME,PaymentMethodDBToolkit.
                        COL_PAYMENT_METHOD_ID +"=?",new String[]
                        {String.valueOf(paymentMethod.getPaymentMethodID())});
    }
}

