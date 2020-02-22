package com.codenerdz.expensesmanager.toolkit.expense;

public class ExpenditureDBToolkit
{

    private static class SingeltonHolder
    {
        public static ExpenditureDBToolkit instance = new ExpenditureDBToolkit();
    }

    private ExpenditureDBToolkit()
    {

    }

    public static ExpenditureDBToolkit getInstance()
    {
        return SingeltonHolder.instance;
    }

    public static final String EXPENDITURE_TABLE_NAME = "tbl_expenditure";
    public static final String COL_EXPENDITURE_ID = "_id";
    public static final String COL_EXPENDITURE_DESCRIPTION = "description";
    public static final String COL_SPENDER = "spender";
    public static final String COL_IS_SHARED_EXPENDITURE = "isSharedExpenditure";
    public static final String COL_EXPENDITURE_AMOUNT = "amount";
    public static final String COL_EXPENDITURE_CATEGORY = "categoryID";
    public static final String COL_EXPENDITURE_DATE = "date";
    public static final String COL_EXPENDITURE_PAYMENT_METHOD = "paymentMethodID";



    public String getExpenditureTableQueryString()
    {
        return "CREATE TABLE IF NOT EXISTS " + EXPENDITURE_TABLE_NAME + " ( " +
                COL_EXPENDITURE_ID + " INTEGER PRIMARY KEY autoincrement, " +
                COL_EXPENDITURE_DESCRIPTION + " TEXT, " +
                COL_SPENDER + " INTEGER, " +
                COL_EXPENDITURE_AMOUNT + " INTEGER, " +
                COL_EXPENDITURE_CATEGORY + " INTEGER, " +
                COL_EXPENDITURE_PAYMENT_METHOD + " INTEGER, " +
                COL_EXPENDITURE_DATE + " LONG, " +
                COL_IS_SHARED_EXPENDITURE + " BOOLEAN);";
    }

}
