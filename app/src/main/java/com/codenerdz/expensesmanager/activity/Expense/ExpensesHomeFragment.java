package com.codenerdz.expensesmanager.activity.Expense;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.codenerdz.expensesmanager.R;
import com.codenerdz.expensesmanager.activity.common.ToolbarDetail;
import com.codenerdz.expensesmanager.test.db.ExpnsesList;
import com.codenerdz.expensesmanager.toolkit.ToolbarToolkit;

public class ExpensesHomeFragment extends Fragment implements ToolbarDetail
{
    private ListView expensesListView;
    private View view;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.expenses_home_layout,container,false);
        expensesListView = (ListView) view.findViewById(R.id.expenses_list_view);
        setTitle(getResources().getString(R.string.expenses));
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        ExpensesAdapter expenditureAdapter = new ExpensesAdapter(view.getContext(),
                ExpnsesList.getInstance().getExpensesList());
        expensesListView.setAdapter(expenditureAdapter);
    }


    @Override
    public void setTitle(String title)
    {
        ToolbarToolkit.getInstance().
                setTitle((Toolbar)getActivity().findViewById(R.id.toolbar),title);
    }
}
