package com.codenerdz.expensesmanager.activity.spender;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.codenerdz.expensesmanager.R;

public class SpenderFragment extends Fragment {

    private SpenderViewModel spenderViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        spenderViewModel =
                ViewModelProviders.of(this).get(SpenderViewModel.class);
        View root = inflater.inflate(R.layout.fragment_spender_layout, container, false);
        final TextView textView = root.findViewById(R.id.text_spender);
        spenderViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}