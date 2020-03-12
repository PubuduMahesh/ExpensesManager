package com.codenerdz.expensesmanager.activity.analysor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.codenerdz.expensesmanager.R;
import com.codenerdz.expensesmanager.activity.spender.SpenderAdapter;
import com.codenerdz.expensesmanager.activity.spender.SpenderDBAdapter;

public class AnalysorHomeFragment extends Fragment {

    private View view;
    private CheckBox spenderCheckBox;
    private CheckBox daysCheckBox;
    private CheckBox categoryCheckBox;
    private CheckBox paymentMethodCheckBox;
    private Spinner spenderSpinner;
    private Spinner daysSpinner;
    private Spinner categorySpinner;
    private Spinner paymentMethodSpinner;
    private Button spenderAddButton;
    private Button daysAddButton;
    private Button categoryAddButton;
    private Button paymentMethodAddButton;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_analysor_option_layout, container, false);
        spenderCheckBox = (CheckBox)view.findViewById(R.id.spender_check_box);
        daysCheckBox = (CheckBox) view.findViewById(R.id.days_check_box);
        categoryCheckBox = (CheckBox) view.findViewById(R.id.category_check_box);
        paymentMethodCheckBox = (CheckBox) view.findViewById(R.id.payment_method_check_box);
        spenderSpinner = (Spinner) view.findViewById(R.id.spender_spinner);
        daysSpinner = (Spinner) view.findViewById(R.id.days_spinner);
        categorySpinner = (Spinner) view.findViewById(R.id.category_spinner);
        paymentMethodSpinner = (Spinner) view.findViewById(R.id.payment_method_spinner);
        spenderAddButton = (Button) view.findViewById(R.id.spender_add_button);
        daysAddButton = (Button) view.findViewById(R.id.days_add_button);
        categoryAddButton = (Button) view.findViewById(R.id.analyse_category_add_button);
        paymentMethodAddButton = (Button) view.findViewById(R.id.analyse_payment_method_add_button);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        addClickListenerSpenderCheckBox();
        addClickListenerDaysCheckBox();
        addClickListenerCategoryCheckBox();
        addClickListenerPaymentMethodCheckBox();

        SpenderAdapter spenderAdapter = new SpenderAdapter(view.getContext(),
                SpenderDBAdapter.getInstance().fetchAllSpenders(view.getContext()));
        spenderSpinner.setAdapter(spenderAdapter);
    }

    private void addClickListenerSpenderCheckBox() {
        spenderCheckBox.setOnClickListener(new View.OnClickListener(){

            @Override public void onClick(View v) {
                if (spenderCheckBox.isChecked()) {
                    spenderSpinner.setVisibility(View.VISIBLE);
                    spenderAddButton.setVisibility(View.VISIBLE);

                } else {
                    spenderSpinner.setVisibility(View.GONE);
                    spenderAddButton.setVisibility(View.GONE);
                }
            }
        });
    }

    private void addClickListenerDaysCheckBox() {
        daysCheckBox.setOnClickListener(new View.OnClickListener(){

            @Override public void onClick(View v) {
                if (daysCheckBox.isChecked()) {
                    daysSpinner.setVisibility(View.VISIBLE);
                    daysAddButton.setVisibility(View.VISIBLE);

                } else {
                    daysSpinner.setVisibility(View.GONE);
                    daysAddButton.setVisibility(View.GONE);
                }
            }
        });
    }

    private void addClickListenerCategoryCheckBox() {
        categoryCheckBox.setOnClickListener(new View.OnClickListener(){

            @Override public void onClick(View v) {
                if (categoryCheckBox.isChecked()) {
                    categorySpinner.setVisibility(View.VISIBLE);
                    categoryAddButton.setVisibility(View.VISIBLE);

                } else {
                    categorySpinner.setVisibility(View.GONE);
                    categoryAddButton.setVisibility(View.GONE);
                }
            }
        });
    }

    private void addClickListenerPaymentMethodCheckBox() {
        paymentMethodCheckBox.setOnClickListener(new View.OnClickListener(){

            @Override public void onClick(View v) {
                if (paymentMethodCheckBox.isChecked()) {
                    paymentMethodSpinner.setVisibility(View.VISIBLE);
                    paymentMethodAddButton.setVisibility(View.VISIBLE);

                } else {
                    paymentMethodSpinner.setVisibility(View.GONE);
                    paymentMethodAddButton.setVisibility(View.GONE);
                }
            }
        });
    }
}
