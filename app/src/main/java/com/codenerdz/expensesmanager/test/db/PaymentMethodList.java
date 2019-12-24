package com.codenerdz.expensesmanager.test.db;

import com.codenerdz.expensesmanager.R;
import com.codenerdz.expensesmanager.ui.payment_method.PaymentMethod;

import java.util.ArrayList;
import java.util.List;

public class PaymentMethodList {

    private static class SingeltonHolder
    {
        public static PaymentMethodList instance = new PaymentMethodList();
    }

    private PaymentMethodList(){}

    public static PaymentMethodList getInstance()
    {
        return SingeltonHolder.instance;
    }

    public PaymentMethod[] getPaymentMethodsArray()
    {
        List<PaymentMethod> paymentMethodList = new ArrayList<>();
        PaymentMethod paymentMethod1 = new PaymentMethod("CreditCard",
                1,R.drawable.payment_method_image_1);
        PaymentMethod paymentMethod2 = new PaymentMethod("Cash",
                2,R.drawable.payment_method_image_2);
        PaymentMethod[] paymentMethods = {paymentMethod1,paymentMethod2};
        return paymentMethods;
    }
}
