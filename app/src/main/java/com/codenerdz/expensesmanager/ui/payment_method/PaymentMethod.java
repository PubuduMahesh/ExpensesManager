package com.codenerdz.expensesmanager.ui.payment_method;

public class PaymentMethod {
    String paymentMethodName;
    int paymentMethodID;
    int paymentMethodSource;

    public PaymentMethod(String paymentMethodName,int paymentMethodID,int paymentMethodSource)
    {
        this.paymentMethodName = paymentMethodName;
        this.paymentMethodID = paymentMethodID;
        this.paymentMethodSource = paymentMethodSource;
    }

    public PaymentMethod()
    {

    }

    public String getPaymentMethodName() {
        return paymentMethodName;
    }

    public void setPaymentMethodName(String paymentMethodName) {
        this.paymentMethodName = paymentMethodName;
    }

    public int getPaymentMethodID() {
        return paymentMethodID;
    }

    public void setPaymentMethodID(int paymentMethodID) {
        this.paymentMethodID = paymentMethodID;
    }

    public int getPaymentMethodSource() {
        return paymentMethodSource;
    }

    public void setPaymentMethodSource(int paymentMethodSource) {
        this.paymentMethodSource = paymentMethodSource;
    }
}
