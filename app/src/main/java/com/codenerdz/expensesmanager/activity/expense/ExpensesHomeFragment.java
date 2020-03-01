package com.codenerdz.expensesmanager.activity.expense;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.codenerdz.expensesmanager.R;
import com.codenerdz.expensesmanager.activity.common.ToolbarDetail;
import com.codenerdz.expensesmanager.activity.spender.Spender;
import com.codenerdz.expensesmanager.model.ExpensesViewModel;
import com.codenerdz.expensesmanager.model.SpenderExpenseViewModel;
import com.codenerdz.expensesmanager.toolkit.DateTimeToolkit;
import com.codenerdz.expensesmanager.toolkit.ToolbarToolkit;

import java.text.ParseException;
import java.util.List;

public class ExpensesHomeFragment extends Fragment implements ToolbarDetail
{
    private ListView expensesListView;
    TextView dateTextField;
    private View view;
    private Spender selectedSpender;
    private float firstX;
    private int minDistance;
    private ExpensesViewModel expensesViewModel;
    private Button deleteButton;
    private Button editButton;
    private List<Expense> checkedExpenses;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        expensesViewModel = ViewModelProviders.of(getActivity()).get(ExpensesViewModel.class);
        final Observer<List<Expense>> expenseObserver = listUpdateListener();
        expensesViewModel.getExpensesList().observe(this,expenseObserver);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.expenses_home_layout,container,false);
        clearExpenseViewModelList();
        expensesListView = (ListView) view.findViewById(R.id.expenses_list_view);
        setTitle(getResources().getString(R.string.expenses));
        setHasOptionsMenu(true);
        spenderSelectedActionFired();
        //add swap functionality to toolbar
        handleTouchEvent( (Toolbar)getActivity().findViewById(R.id.toolbar));
        //add swapping functionality to expense home screen
        handleTouchEvent(view);
        return view;
    }

    private void editDeleteButtonHandler()
    {
        deleteButton = (Button)view.findViewById(R.id.expenses_delete);
        editButton = (Button) view.findViewById(R.id.expenses_edit);
        deleteButton.setEnabled(false);
        editButton.setEnabled(false);
        deleteButtonActionPerformed();
        editButtonActionPerformed();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        setCurrentDate();
        loadExpenses();
        setDateTextField(DateTimeToolkit.getInstance().getCurrentDate());
        editDeleteButtonHandler();
    }

    private void updateExpenditureAdapter(ExpensesAdapter expenditureAdapter)
    {
        expensesListView.setAdapter(expenditureAdapter);
    }

    private void clearExpenseViewModelList()
    {
        if(expensesViewModel != null)
        {
            expensesViewModel.clearExpensesList();
        }
    }


    @Override
    public void setTitle(String title)
    {
        ToolbarToolkit.getInstance().
                setTitle((Toolbar)getActivity().findViewById(R.id.toolbar),title);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.expenses_actionbar_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.action_create_new_expense:
                getFragmentManager().beginTransaction()
                        .replace(((ViewGroup)getView().getParent()).getId(),new ExpenseNewFragment()
                                ,"new category fragement")
                        .addToBackStack(null)
                        .commit();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * This method is used to manipulate left/right swapping in Expenses home.
     * return true, if swap left,
     * return false, if swap right.
     */
    private void handleTouchEvent(View view)
    {
        view.setOnTouchListener(new View.OnTouchListener(){
            @Override public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        firstX = event.getX();
                        return true;
                    case MotionEvent.ACTION_UP:
                        float secondX = event.getX();
                        if (Math.abs(secondX - firstX) > minDistance)
                        {
                            if (secondX > firstX)
                            {
                                expensesViewModel.clearExpensesList();
                                setPreviousDate();
                                loadExpenses();
                            } else {
                                expensesViewModel.clearExpensesList();
                                setNextDate();
                                loadExpenses();
                            }
                        }
                        return false;
                }
                return false;
            }
        });
    }

    private void setPreviousDate()
    {
        try
        {
            setDateTextField(DateTimeToolkit.getInstance().
                getPreviousDateForGivenDate(getDateTextFieldValue()));
        }
        catch (ParseException exception)
        {
            //handle this.
        }
    }

    private void setNextDate()
    {
        try
        {
            setDateTextField(DateTimeToolkit.getInstance().
                    getNextDateForGivenDate(getDateTextFieldValue()));
        }
        catch (ParseException exception)
        {
            //handle this.
        }
    }

    private void setCurrentDate()
    {
        setDateTextField(DateTimeToolkit.getInstance().
                getCurrentDate());
    }

    private void loadExpenses()
    {
        updateExpenditureAdapter(new ExpensesAdapter(view.getContext(),
                ExpenditureDBAdapter.getInstance().fetchAllExpensesBySpenderDate(
                        view.getContext(),
                        selectedSpender,
                        DateTimeToolkit.getInstance().
                                getMinMaxMillisecondsForGivenDate(getDateTextFieldValue()))));
    }

    private String getDateTextFieldValue()
    {
        return dateTextField.getText().toString();
    }

    private void setDateTextField(String date)
    {
        dateTextField = (TextView)view.findViewById(R.id.expenses_date);
        dateTextField.setText(date);
    }

    private void setSelectedSpender(Spender spender)
    {
        selectedSpender = spender;
    }

    private void spenderSelectedActionFired()
    {
        final SpenderExpenseViewModel<Spender> modelSpender =
                ViewModelProviders.of(getActivity()).get(SpenderExpenseViewModel.class);
        setSelectedSpender(modelSpender.getSelectedItem());
    }

    private Observer<List<Expense>> listUpdateListener() {
        return new Observer<List<Expense>>() {
            @Override
            public void onChanged(List<Expense> expensesList) {
                checkedExpenses = expensesList;
                if(checkedExpenses.size()>0)
                {
                    deleteButton.setEnabled(true);
                    editButton.setEnabled(true);
                    if(checkedExpenses.size()>1)
                    {
                        editButton.setEnabled(false);
                    }
                }
                else
                {
                    deleteButton.setEnabled(false);
                    editButton.setEnabled(false);
                }
            }
        };
    }

    private void editButtonActionPerformed()
    {
        editButton.setOnClickListener(new View.OnClickListener(){

            @Override public void onClick(View v) {
                getFragmentManager().beginTransaction()
                        .replace(((ViewGroup)getView().getParent()).getId(),new ExpenseEditFragment()
                                ,"EditExpense")
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    private void deleteButtonActionPerformed()
    {
        deleteButton.setOnClickListener(new View.OnClickListener(){

            @Override public void onClick(View v) {
                showAlertDialogWhenDeleteExpenses();
            }
        });
    }

    private void showAlertDialogWhenDeleteExpenses(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
        alertDialogBuilder.setMessage("Are you sure,You wanted to make decision");
                alertDialogBuilder.setPositiveButton("yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                ExpenditureDBAdapter.getInstance().deleteSelectedExpenses(view.getContext(),
                                        checkedExpenses);
                                expensesViewModel.clearExpensesList();
                                loadExpenses();
                            }
                        });

        alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                System.out.println("test2");
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

}
