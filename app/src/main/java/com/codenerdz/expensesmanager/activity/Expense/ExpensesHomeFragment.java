package com.codenerdz.expensesmanager.activity.Expense;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.codenerdz.expensesmanager.R;
import com.codenerdz.expensesmanager.activity.category.CategoryNewFragment;
import com.codenerdz.expensesmanager.activity.common.ToolbarDetail;
import com.codenerdz.expensesmanager.activity.spender.Spender;
import com.codenerdz.expensesmanager.model.SpenderExpenseViewModel;
import com.codenerdz.expensesmanager.test.db.ExpnsesList;
import com.codenerdz.expensesmanager.toolkit.ExpensesManagerConstantToolkit;
import com.codenerdz.expensesmanager.toolkit.ToolbarToolkit;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ExpensesHomeFragment extends Fragment implements ToolbarDetail
{
    private ListView expensesListView;
    private View view;
    private Spender selectedSpender;
    String parentFragment;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        parentFragment = getArguments().getString(ExpensesManagerConstantToolkit.PARENT_FRAGMENT);
        view = inflater.inflate(R.layout.expenses_home_layout,container,false);
        expensesListView = (ListView) view.findViewById(R.id.expenses_list_view);
        setTitle(getResources().getString(R.string.expenses));
        setHasOptionsMenu(true);
        spenderSelectedActionFired();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        ExpensesAdapter expenditureAdapter = new ExpensesAdapter(view.getContext(),
                ExpenditureDBAdapter.getInstance().fetchAllExpensesBySpender(view.getContext()
                       ,selectedSpender));
        expensesListView.setAdapter(expenditureAdapter);
        setDate();
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
                        .replace(((ViewGroup)getView().getParent()).getId(),new ExpenseNewFragment(),"new category fragement")
                        .addToBackStack(null)
                        .commit();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setDate()
    {
        TextView dateTextField = (TextView)view.findViewById(R.id.expenses_date);
        Date c = Calendar.getInstance().getTime();

        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String formattedDate = df.format(c);
        dateTextField.setText(formattedDate);
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
}
