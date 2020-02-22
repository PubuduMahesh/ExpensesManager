package com.codenerdz.expensesmanager.toolkit.payment_method;

public class PaymentMethodDBToolkit
{

    private static class SingeltonHolder
    {
        public static PaymentMethodDBToolkit instance = new PaymentMethodDBToolkit();
    }

    private PaymentMethodDBToolkit()
    {

    }

    public static PaymentMethodDBToolkit getInstance()
    {
        return SingeltonHolder.instance;
    }

    public static final String PAYMENT_METHOD_TABLE_NAME = "tbl_payment_method";
    public static final String COL_PAYMENT_METHOD_ID = "_id";
    public static final String COL_PAYMENT_METHOD_NAME = "paymentMethodName";
    public static final String COL_PAYMENT_METHOD_IMAGE_SOURCE = "paymentMethodImageSource";
    public static final String COL_PAYMENT_METHOD_IS_SHARED = "sharedMethod";

    public String getPaymentMethodTableQueryString()
    {
        return "CREATE TABLE IF NOT EXISTS " + PAYMENT_METHOD_TABLE_NAME + " ( " +
                COL_PAYMENT_METHOD_ID + " INTEGER PRIMARY KEY autoincrement, " +
                COL_PAYMENT_METHOD_NAME + " TEXT, " +
                COL_PAYMENT_METHOD_IS_SHARED + " INTEGER, " +
                COL_PAYMENT_METHOD_IMAGE_SOURCE + " INTEGER );";
    }
}
