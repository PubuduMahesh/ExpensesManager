package com.codenerdz.expensesmanager.activity.Expense;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import com.codenerdz.expensesmanager.R;
import com.codenerdz.expensesmanager.activity.category.CategoryDBAdapter;
import com.codenerdz.expensesmanager.activity.common.ToolbarDetail;
import com.codenerdz.expensesmanager.activity.payment_method.PaymentMethodDBAdapter;
import com.codenerdz.expensesmanager.model.ExpensesViewModel;
import com.codenerdz.expensesmanager.toolkit.ToolbarToolkit;

public class ExpenseEditFragment extends ExpenseNewFragment implements ToolbarDetail {

    private ExpensesViewModel expensesViewModel;
    private Expense selectedExpense;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        expensesViewModel = ViewModelProviders.of(getActivity()).get(ExpensesViewModel.class);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        super.onCreateView(inflater,container,savedInstanceState);
        setComponentValue();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void setTitle(String title) {
        ToolbarToolkit.getInstance().
                setTitle((Toolbar)getActivity().findViewById(R.id.toolbar),title);
    }

    private void setComponentValue()
    {
        selectedExpense = expensesViewModel.getExpensesList().getValue().get(0);
        expenditureAmountEditText.setText(String.valueOf(selectedExpense.getExpenditureAmount()));
        expenditureDescriptionEditText.setText(selectedExpense.getExpenditureDescription());
        selectedCategory = CategoryDBAdapter.getInstance().fetchCategoryByID(selectedExpense.
                getExpenditureCategoryID(),getContext());
        selectedPaymentMethod = PaymentMethodDBAdapter.getInstance().fetchPaymentMethodByID
                (selectedExpense.getExpenditurePaymentMethodID(),getContext());
        selectedDate = selectedExpense.getExpenseDate();
        isSharedRadioButton.setChecked(selectedExpense.isSharedExpenditure());
        categoryButton.setBackgroundResource(selectedCategory.getCategoryImageSource());
        paymentMethodButton.setBackgroundResource
                (selectedPaymentMethod.getPaymentMethodImageSource());

    }

    @Override
    protected void addButtonActionPerformed()
    {
        (submitButton).
                setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(validateExpenseObject())
                        {
                            ExpenditureDBAdapter.getInstance().
                                    createExpense(ExpenseEditFragment.this.createExpenseObjectToSubmit(),getContext());
                            getFragmentManager().popBackStack();
                            expensesViewModel.clearExpensesList();
                        }
                    }
                });
    }

    @Override
    protected Expense createExpenseObjectToSubmit()
    {
        Expense expense = super.createExpenseObjectToSubmit();
        expense.setExpenseID(selectedExpense.getExpenseID());
        return expense;
    }
}
