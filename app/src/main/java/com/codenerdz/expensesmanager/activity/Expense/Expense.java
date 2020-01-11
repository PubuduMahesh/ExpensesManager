package com.codenerdz.expensesmanager.activity.Expense;

public class Expense
{
    private int expenseID;
    private int expenser;
    private int expenditureCategory;
    private int expenditureAmount;
    private boolean isSharedExpenditure;
    private String expenditureDescription;


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
