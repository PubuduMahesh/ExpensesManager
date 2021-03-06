package com.codenerdz.expensesmanager.activity.expense;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

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
import com.codenerdz.expensesmanager.activity.spender.SpenderHomeFragment;
import com.codenerdz.expensesmanager.model.CategoryExpenseViewModel;
import com.codenerdz.expensesmanager.model.PaymentMethodExpenseViewModel;
import com.codenerdz.expensesmanager.model.SpenderExpenseViewModel;
import com.codenerdz.expensesmanager.model.SponsorExpenseViewModel;
import com.codenerdz.expensesmanager.toolkit.EMConstantToolkit;
import com.codenerdz.expensesmanager.toolkit.ToolbarToolkit;

import java.util.Calendar;

public class ExpenseNewFragment extends Fragment implements ToolbarDetail
{
    protected View view;
    protected CategoryExpenseViewModel model;
    protected Category selectedCategory;
    protected Spender selectedSpender;
    protected Spender selectedSponsor;
    protected PaymentMethod selectedPaymentMethod;
    protected Button categoryButton;
    protected Button sponsorButton;
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
        if(view == null)
        {
            view = inflater.inflate(R.layout.new_expense_layout, container, false);
            componentInitializing();
            setTitle(getResources().getString(R.string.new_expense));
            updateCalendarEvent();
        }
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
        categoryButton = (Button)view.findViewById(R.id.add_category);
        sponsorButton = (Button)view.findViewById(R.id.add_sponsor);
        paymentMethodButton = (Button)view.findViewById(R.id.add_payment_method);
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
    public void startPostponedEnterTransition() {
        super.startPostponedEnterTransition();
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
        addSponsorClickListener();
        paymentMethodSelectActionFiered();
        spenderSelectedActionFired();
        sponsorSelectedActionFired();
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

    private void sponsorSelectedActionFired()
    {
        final SponsorExpenseViewModel<Spender> modelSponsor =
                ViewModelProviders.of(getActivity()).get(SponsorExpenseViewModel.class);
        modelSponsor.getSelected().observe(this, item -> {
            setSelectedSponsor(modelSponsor.getSelectedItem());
        });
    }


    private void setSelectedSpender(Spender spender)
    {
        selectedSpender = spender;
        setSelectedSponsor(spender);
    }

    private void setSelectedSponsor(Spender sponsor)
    {
        selectedSponsor = sponsor;
        updateSponsorButton();
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

    private void updateSponsorButton()
    {
        sponsorButton.setText("");
        sponsorButton.setBackgroundResource(selectedSponsor.getSpenderImageSource());
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
        paymentMethodButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction()
                        .replace(((ViewGroup)getView().getParent()).getId(),
                                NextFragment.getInstance().setArgumentsForNextFragment
                                (new PaymentMethodHomeFragment(), EMConstantToolkit.
                                        EXPENSER_NEW_AS_PARENT_FRAGMENT),
                                "payment method home fragement")
                        .addToBackStack("ExpensesNewFragment")
                        .commit();
            }
        });

    }

    private void addSponsorClickListener()
    {
        sponsorButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction()
                        .replace(((ViewGroup)getView().getParent()).getId(),
                                NextFragment.getInstance().setArgumentsForNextFragment
                                        (new SpenderHomeFragment(), EMConstantToolkit.
                                                EXPENSER_NEW_AS_PARENT_FRAGMENT),
                                "spender home fragement")
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
                if(validateExpenseObject())
                {
                    ExpenditureDBAdapter.getInstance().
                            createExpense(createExpenseObjectToSubmit(),getContext());
                    getFragmentManager().popBackStack();
                }
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
        expense.setSponsor(selectedSponsor.getSpenderID());
        expense.setExpenditurePaymentMethodID(selectedPaymentMethod.getPaymentMethodID());
        return expense;
    }

    protected boolean validateExpenseObject() {
        //TODO: add tooltips to this false occasions.
        if(selectedCategory == null)
        {
            Toast.makeText(getContext(),"You should select category.",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        if(selectedPaymentMethod == null)
        {
            Toast.makeText(getContext(),"You should select payment method.",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        if(expenditureAmountEditText.getText().toString().isEmpty())
        {
            Toast.makeText(getContext(),"You should add amount to the expense.",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        if(expenditureDescriptionEditText.getText().toString().isEmpty())
        {
            Toast.makeText(getContext(),"You should add description.",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean isSharedExpense() {
        if(isSharedRadioButton.isChecked())
        {
            return true;
        }
        return false;
    }

}
