package com.codenerdz.expensesmanager.activity.Expense;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.codenerdz.expensesmanager.R;
import com.codenerdz.expensesmanager.activity.category.Category;
import com.codenerdz.expensesmanager.activity.category.CategoryHomeFragment;
import com.codenerdz.expensesmanager.activity.common.ToolbarDetail;
import com.codenerdz.expensesmanager.activity.payment_method.PaymentMethod;
import com.codenerdz.expensesmanager.activity.payment_method.PaymentMethodHomeFragment;
import com.codenerdz.expensesmanager.model.CategoryExpenseViewModel;
import com.codenerdz.expensesmanager.model.PaymentMethodExpenseViewModel;
import com.codenerdz.expensesmanager.toolkit.ToolbarToolkit;

public class ExpenseNewFragment extends Fragment implements ToolbarDetail
{
    private View view;
    private CategoryExpenseViewModel model;
    private Category selectedCategory;
    private PaymentMethod selectedPaymentMethod;
    private Button categoryButton;
    private Button paymentMethodButton;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.new_expense_layout, container, false);
        setTitle(getResources().getString(R.string.new_expense));
        addCategoryClickListener();
        categorySelectActionFired();
        addPaymentMethodClickListener();
        paymentMethodSelectActionFiered();
        return view;
    }

    private void categorySelectActionFired() {
        final CategoryExpenseViewModel<Category> modelCategory =
                ViewModelProviders.of(getActivity()).get(CategoryExpenseViewModel.class);
        modelCategory.getSelected().observe(this, item -> {
            setSelectedCategory(modelCategory.getSelectedItem());
            updateCategoryButton();
        });
    }

    private void paymentMethodSelectActionFiered() {
        final PaymentMethodExpenseViewModel<PaymentMethod> modelPaymentMethod =
                ViewModelProviders.of(getActivity()).get(PaymentMethodExpenseViewModel.class);
        modelPaymentMethod.getSelected().observe(this, item -> {
            setSelectedPaymentMethod(modelPaymentMethod.getSelectedItem());
            updatePaymentMethodButton();
        });
    }

    private void updatePaymentMethodButton()
    {
        paymentMethodButton.setText("");
        paymentMethodButton.setBackgroundResource(selectedPaymentMethod.getPaymentMethodImageSource());
    }

    private void setSelectedPaymentMethod(PaymentMethod paymentMethod)
    {
        selectedPaymentMethod = paymentMethod;
    }

    private void updateCategoryButton()
    {
        categoryButton.setText("");
        categoryButton.setBackgroundResource(selectedCategory.getCategorySource());
    }

    private void setSelectedCategory(Category category)
    {
        selectedCategory = category;
    }

    @Override
    public void setTitle(String title)
    {
        ToolbarToolkit.getInstance().
                setTitle((Toolbar)getActivity().findViewById(R.id.toolbar),title);

    }

    private void addCategoryClickListener()
    {
        categoryButton = (Button)view.findViewById(R.id.add_category);
        categoryButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getFragmentManager().beginTransaction()
                        .replace(((ViewGroup)getView().getParent()).getId(),
                                new CategoryHomeFragment(),"category home fragement")
                        .addToBackStack("ExpensesNewFragment")
                        .commit();
            }
        });
    }

    private void addPaymentMethodClickListener()
    {
        paymentMethodButton = (Button)view.findViewById(R.id.add_payment_method);
        paymentMethodButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction()
                        .replace(((ViewGroup)getView().getParent()).getId(),
                                new PaymentMethodHomeFragment(),"payment method home fragement")
                        .addToBackStack("ExpensesNewFragment")
                        .commit();
            }
        });

    }

}
