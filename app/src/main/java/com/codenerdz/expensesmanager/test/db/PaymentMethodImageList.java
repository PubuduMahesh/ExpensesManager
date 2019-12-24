package com.codenerdz.expensesmanager.test.db;

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
        int[] imageList = {R.drawable.payment_method_image_1,R.drawable.payment_method_image_2,R.drawable.payment_method_image_4,
                R.drawable.payment_method_image_5,R.drawable.payment_method_image_6,R.drawable.payment_method_image_7,R.drawable.payment_method_image_8,
                R.drawable.payment_method_image_9,R.drawable.payment_method_image_10,};
        return imageList;
    }
}
