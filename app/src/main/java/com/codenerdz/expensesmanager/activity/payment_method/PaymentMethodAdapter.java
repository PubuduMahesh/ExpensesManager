package com.codenerdz.expensesmanager.activity.payment_method;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codenerdz.expensesmanager.R;

public class PaymentMethodAdapter extends BaseAdapter {
    private Context context;
    private PaymentMethod[] paymentMethods;
    public PaymentMethodAdapter(Context context, PaymentMethod[] paymentMethods)
    {
        this.context = context;
        this.paymentMethods = paymentMethods;
    }

    @Override
    public int getCount()
    {
        return paymentMethods.length;
    }

    @Override
    public long getItemId(int position)
    {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final PaymentMethod paymentMethod = paymentMethods[position];

        if(convertView == null)
        {
            final LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.image_text_item_layout,null);
        }
        final ImageView imageView = (ImageView)convertView.findViewById(R.id.image_text_image);
        final TextView nameTextView = (TextView)convertView.findViewById(R.id.image_text_text);

        imageView.setImageResource(paymentMethod.getPaymentMethodSource());
        nameTextView.setText(paymentMethod.getPaymentMethodName());
        return convertView;
    }
}
