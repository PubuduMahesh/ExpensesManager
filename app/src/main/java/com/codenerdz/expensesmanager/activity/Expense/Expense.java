package com.codenerdz.expensesmanager.activity.Expense;

import java.util.Date;

public class Expense
{
    private int expenseID;
    private int expenser;
    private int expenditureCategory;
    private int expenditureAmount;
    private boolean isSharedExpenditure;
    private String expenditureDescription;
    private long expenseDate;

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

    public int getExpenditureCategory()
    {
        return expenditureCategory;
    }

    public void setExpenditureCategory(int expenditureCategory)
    {
        this.expenditureCategory = expenditureCategory;
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
