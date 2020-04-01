package com.codenerdz.expensesmanager.activity.analysor;

import com.codenerdz.expensesmanager.activity.expense.Expense;

public class Analysor extends Expense {

    private String spenderName;
    private String categoryName;
    private String paymentMethodName;

    public String getSpenderName() {
        return spenderName;
    }

    public void setSpenderName(String spenderName) {
        this.spenderName = spenderName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getPaymentMethodName() {
        return paymentMethodName;
    }

    public void setPaymentMethodName(String paymentMethodName) {
        this.paymentMethodName = paymentMethodName;
    }
}
