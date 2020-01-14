package com.codenerdz.expensesmanager.activity.Expense;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.codenerdz.expensesmanager.R;
import com.codenerdz.expensesmanager.activity.category.Category;
import com.codenerdz.expensesmanager.activity.category.CategoryHomeFragment;
import com.codenerdz.expensesmanager.activity.common.NextFragment;
import com.codenerdz.expensesmanager.activity.common.ToolbarDetail;
import com.codenerdz.expensesmanager.activity.payment_method.PaymentMethod;
import com.codenerdz.expensesmanager.activity.payment_method.PaymentMethodHomeFragment;
import com.codenerdz.expensesmanager.activity.spender.Spender;
import com.codenerdz.expensesmanager.model.CategoryExpenseViewModel;
import com.codenerdz.expensesmanager.model.PaymentMethodExpenseViewModel;
import com.codenerdz.expensesmanager.model.SpenderExpenseViewModel;
import com.codenerdz.expensesmanager.toolkit.ExpensesManagerConstantToolkit;
import com.codenerdz.expensesmanager.toolkit.ToolbarToolkit;

public class ExpenseNewFragment extends Fragment implements ToolbarDetail
{
    private View view;
    private CategoryExpenseViewModel model;
    private Category selectedCategory;
    private Spender selectedSpender;
    private PaymentMethod selectedPaymentMethod;
    private Button categoryButton;
    private Button paymentMethodButton;
    private String parentFragment;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        parentFragment = NextFragment.getInstance().setParentFragment(this);
        view = inflater.inflate(R.layout.new_expense_layout, container, false);
        setTitle(getResources().getString(R.string.new_expense));
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        extractDataFromOtherFragments();
        addButtonActionPerformed();

    }

    private void extractDataFromOtherFragments() {
        addCategoryClickListener();
        categorySelectActionFired();
        addPaymentMethodClickListener();
        paymentMethodSelectActionFiered();
        spenderSelectedActionFired();
    }

    private void categorySelectActionFired()
    {
        final CategoryExpenseViewModel<Category> modelCategory =
                ViewModelProviders.of(getActivity()).get(CategoryExpenseViewModel.class);
        modelCategory.getSelected().observe(this, item -> {
            setSelectedCategory(modelCategory.getSelectedItem());
            updateCategoryButton();
        });
    }

    private void spenderSelectedActionFired()
    {
        final SpenderExpenseViewModel<Spender> modelSpender =
                ViewModelProviders.of(getActivity()).get(SpenderExpenseViewModel.class);
        modelSpender.getSelected().observe(this, item -> {
            setSelectedSpender(modelSpender.getSelectedItem());
        });
    }

    private void setSelectedSpender(Spender spender)
    {
        selectedSpender = spender;
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

    private void addButtonActionPerformed()
    {
        ((Button)view.findViewById(R.id.add_new_expense)).
                setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExpenditureDBAdapter.getInstance().createExpense(createNewExpense(),getContext());
                getFragmentManager().popBackStack();
            }
        });
    }

    private Expense createNewExpense()
    {
        EditText textField = (EditText)view.findViewById(R.id.expense_reason_text_field);

        Expense expense = new Expense();
        expense.setExpenditureCategory(selectedCategory.getCategoryID());
        expense.setExpenditureDescription(((EditText)view.findViewById
                (R.id.expense_description_textfield)).getText().toString());
        expense.setExpenditureAmount(Integer.parseInt(((EditText)view.findViewById
                (R.id.expense_value_text_field)).getText().toString()));
        expense.setSharedExpenditure(isSharedExpense());
        expense.setExpenser(selectedSpender.getSpenderID());
        return expense;
    }

    private boolean isSharedExpense() {
        if(((RadioButton)view.findViewById(R.id.radio_button_public_expense)).isChecked())
        {
            return true;
        }
        return false;

    }

}
