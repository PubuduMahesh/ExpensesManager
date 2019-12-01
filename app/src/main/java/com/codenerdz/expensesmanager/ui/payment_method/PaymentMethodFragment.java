package com.codenerdz.expensesmanager.ui.payment_method;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.codenerdz.expensesmanager.R;

public class PaymentMethodFragment extends Fragment {
    private PaymentMethodViewModel paymentMethodViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        paymentMethodViewModel =
                ViewModelProviders.of(this).get(PaymentMethodViewModel.class);
        View root = inflater.inflate(R.layout.fragment_payment_method_layout, container, false);
        final TextView textView = root.findViewById(R.id.text_payment_method);
        paymentMethodViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
