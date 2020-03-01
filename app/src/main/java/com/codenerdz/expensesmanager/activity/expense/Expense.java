package com.codenerdz.expensesmanager.activity.expense;

public class Expense
{
    private int expenseID;
    private int expenser;
    private int expenditureCategoryID;
    private int expenditurePaymentMethodID;
    private int expenditureAmount;
    private boolean isSharedExpenditure;
    private String expenditureDescription;
    private long expenseDate;
    private boolean isSelectedInList;

    public int getExpenditurePaymentMethodID() {
        return expenditurePaymentMethodID;
    }

    public void setExpenditurePaymentMethodID(int expenditurePaymentMethodID) {
        this.expenditurePaymentMethodID = expenditurePaymentMethodID;
    }

    public boolean isSelectedInList()
    {
        return isSelectedInList;
    }

    public void setSelectedInList(boolean selectedInList)
    {
        isSelectedInList = selectedInList;
    }

    /**
     * @return Date value where expend is done.
     */
    public long getExpenseDate()
    {
        return expenseDate;
    }

    public void setExpenseDate(long expenseDate)
    {
        this.expenseDate = expenseDate;
    }


    public int getExpenseID() {
        return expenseID;
    }

    public void setExpenseID(int expenseID) {
        this.expenseID = expenseID;
    }

    public int getExpenser()
    {
        return expenser;
    }

    public void setExpenser(int expenser)
    {
        this.expenser = expenser;
    }

    public int getExpenditureCategoryID()
    {
        return expenditureCategoryID;
    }

    public void setExpenditureCategoryID(int expenditureCategoryID)
    {
        this.expenditureCategoryID = expenditureCategoryID;
    }

    public int getExpenditureAmount()
    {
        return expenditureAmount;
    }

    public void setExpenditureAmount(int expenditureAmount)
    {
        this.expenditureAmount = expenditureAmount;
    }

    public boolean isSharedExpenditure()
    {
        return isSharedExpenditure;
    }

    public void setSharedExpenditure(boolean sharedExpenditure)
    {
        isSharedExpenditure = sharedExpenditure;
    }

    public String getExpenditureDescription()
    {
        return expenditureDescription;
    }

    public void setExpenditureDescription(String expenditureDescription)
    {
        this.expenditureDescription = expenditureDescription;
    }
}
