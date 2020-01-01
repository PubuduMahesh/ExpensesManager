package com.codenerdz.expensesmanager.activity.payment_method;

public class PaymentMethod {
    String paymentMethodName;
    int paymentMethodID;
    int paymentMethodSource;
    boolean isSharedOption;

    public PaymentMethod(String paymentMethodName,
                         int paymentMethodID,int paymentMethodSource,boolean isSharedOption)
    {
        this.paymentMethodName = paymentMethodName;
        this.paymentMethodID = paymentMethodID;
        this.paymentMethodSource = paymentMethodSource;
        this.isSharedOption = isSharedOption;
    }

    public PaymentMethod()
    {

    }

    public String getPaymentMethodName()
    {
        return paymentMethodName;
    }

    public void setPaymentMethodName(String paymentMethodName)
    {
        this.paymentMethodName = paymentMethodName;
    }

    public int getPaymentMethodID()
    {
        return paymentMethodID;
    }

    public void setPaymentMethodID(int paymentMethodID)
    {
        this.paymentMethodID = paymentMethodID;
    }

    public int getPaymentMethodSource()
    {
        return paymentMethodSource;
    }

    public void setPaymentMethodSource(int paymentMethodSource)
    {
        this.paymentMethodSource = paymentMethodSource;
    }
    public boolean isSharedOption()
    {
        return isSharedOption;
    }

    public void setSharedOption(boolean sharedOption)
    {
        isSharedOption = sharedOption;
    }
}
