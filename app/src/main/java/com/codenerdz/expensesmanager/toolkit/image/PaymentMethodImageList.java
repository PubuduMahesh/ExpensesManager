package com.codenerdz.expensesmanager.toolkit.image;

import com.codenerdz.expensesmanager.R;

public class PaymentMethodImageList {
    private static class SingeltonHolder
    {
        public static PaymentMethodImageList instance = new PaymentMethodImageList();
    }

    private PaymentMethodImageList(){}

    public static PaymentMethodImageList getInstance()
    {
        return PaymentMethodImageList.SingeltonHolder.instance;
    }

    public int[] getImageList()
    {
        int[] imageList = {R.drawable.payment_method1,R.drawable.payment_method2,
                R.drawable.payment_method3,R.drawable.payment_method4,
                R.drawable.payment_method5,R.drawable.payment_method6,R.drawable.payment_method7};
        return imageList;
    }
}
