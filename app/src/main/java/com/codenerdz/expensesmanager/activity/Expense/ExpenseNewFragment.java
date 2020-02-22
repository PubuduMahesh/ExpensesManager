package com.codenerdz.expensesmanager.activity.Expense;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
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
import com.codenerdz.expensesmanager.toolkit.EMConstantToolkit;
import com.codenerdz.expensesmanager.toolkit.ToolbarToolkit;

import java.util.Calendar;

public class ExpenseNewFragment extends Fragment implements ToolbarDetail
{
    protected View view;
    protected CategoryExpenseViewModel model;
    protected Category selectedCategory;
    protected Spender selectedSpender;
    protected PaymentMethod selectedPaymentMethod;
    protected Button categoryButton;
    protected Button paymentMethodButton;
    protected Long selectedDate;
    protected Button submitButton;
    protected CalendarView calendarView;
    protected EditText expenditureDescriptionEditText;
    protected EditText expenditureAmountEditText;
    protected RadioButton isSharedRadioButton;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.new_expense_layout, container, false);
        componentInitializing();
        setTitle(getResources().getString(R.string.new_expense));
        updateCalendarEvent();
        return view;
    }

    private void componentInitializing()
    {
        submitButton = (Button)view.findViewById(R.id.add_new_expense);
        expenditureDescriptionEditText = ((EditText)view.findViewById
                (R.id.expense_description_textfield));
        expenditureAmountEditText = ((EditText)view.findViewById
                (R.id.expense_value_text_field));
        isSharedRadioButton = (RadioButton)view.findViewById(R.id.radio_button_public_expense);
    }

    /**
     * will be used to update calendar behavior.
     */
    private void updateCalendarEvent()
    {
        calendarView = (CalendarView)view.findViewById(R.id.calendarView);
        setSelectedDate();
        disableFutureDates(calendarView);
    }

    /**
     * @param calendarView in new expense fragment. WIll be used to disable future dates.
     */
    private void disableFutureDates(CalendarView calendarView)
    {
        calendarView.setMaxDate(System.currentTimeMillis());
    }

    private void setSelectedDate()
    {
        CalendarView calendarView = (CalendarView)view.findViewById(R.id.calendarView);
        selectedDate = calendarView.getDate();
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override public void onSelectedDayChange(@NonNull CalendarView view, int year,
                                                      int month, int dayOfMonth)
            {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth);
                selectedDate = calendar.getTimeInMillis();
            }
        });

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        extractDataFromOtherFragments();
        addButtonActionPerformed();

    }

    /**
     * Methods included in this methods will be invoked when action fired in different fragment
     * which are relevant to this fragment.
     */
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
        categoryButton.setBackgroundResource(selectedCategory.getCategoryImageSource());
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
                                NextFragment.getInstance().setArgumentsForNextFragment
                                        (new CategoryHomeFragment(), EMConstantToolkit.
                                                EXPENSER_NEW_AS_PARENT_FRAGMENT),
                                "category home fragement")
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

    /**
     * This method will be invoked when click the 'add' button in the nex expense fragment.
     */
    protected void addButtonActionPerformed()
    {
        (submitButton).
                setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExpenditureDBAdapter.getInstance().createExpense(createExpenseObjectToSubmit(),getContext());
                getFragmentManager().popBackStack();
            }
        });
    }

    /**
     * Will be create Expense object by collecting data to Expense object attribute from the new
     * Expense fragment UI elements.
     * @return Expense object to save in DB.
     */
    protected Expense createExpenseObjectToSubmit()
    {
        Expense expense = new Expense();
        expense.setExpenseID(-1);
        expense.setExpenseDate(selectedDate);
        expense.setExpenditureCategoryID(selectedCategory.getCategoryID());
        expense.setExpenditureDescription(expenditureDescriptionEditText.getText().toString());
        expense.setExpenditureAmount(Integer.parseInt(expenditureAmountEditText.
                getText().toString()));
        expense.setSharedExpenditure(isSharedExpense());
        expense.setExpenser(selectedSpender.getSpenderID());
        expense.setExpenditurePaymentMethodID(selectedPaymentMethod.getPaymentMethodID());
        return expense;
    }

    private boolean isSharedExpense() {
        if(isSharedRadioButton.isChecked())
        {
            return true;
        }
        return false;

    }

}
