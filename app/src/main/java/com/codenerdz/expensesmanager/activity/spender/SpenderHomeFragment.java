package com.codenerdz.expensesmanager.activity.spender;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.codenerdz.expensesmanager.R;
import com.codenerdz.expensesmanager.activity.Expense.ExpensesHomeFragment;
import com.codenerdz.expensesmanager.activity.category.Category;
import com.codenerdz.expensesmanager.activity.category.CategoryAdapter;
import com.codenerdz.expensesmanager.activity.category.CategoryDBAdapter;
import com.codenerdz.expensesmanager.activity.category.CategoryNewFragment;
import com.codenerdz.expensesmanager.activity.common.ToolbarDetail;
import com.codenerdz.expensesmanager.model.CategoryExpenseViewModel;
import com.codenerdz.expensesmanager.model.SpenderExpenseViewModel;
import com.codenerdz.expensesmanager.toolkit.ToolbarToolkit;

public class SpenderHomeFragment extends Fragment implements ToolbarDetail {

    private GridView gridView;
    private View view;
    private SpenderExpenseViewModel<Spender> spenderViewmodel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.content_grid_layout, container, false);
        gridView = (GridView) view.findViewById(R.id.grid_view);
        setTitle(getResources().getString(R.string.menu_spender));
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        SpenderAdapter spenderAdapter = new SpenderAdapter(view.getContext(),
                SpenderDBAdapter.getInstance().fetchAllSpenders(view.getContext()));
        gridView.setAdapter(spenderAdapter);
        spenderClickListener();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.spender_actionbar_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.action_create_new_spender:
                getFragmentManager().beginTransaction()
                        .replace(((ViewGroup)getView().getParent()).getId(),
                                new SpenderNewFragment(),"new spender fragement")
                        .addToBackStack(null)
                        .commit();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void spenderClickListener()
    {
        spenderViewmodel = ViewModelProviders.of(getActivity()).get(SpenderExpenseViewModel .class);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                spenderViewmodel.selectItem((Spender)gridView.getItemAtPosition(position));
                getFragmentManager().beginTransaction()
                        .replace(((ViewGroup)getView().getParent()).getId(),
                                new ExpensesHomeFragment(),"expenser home fragement")
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    @Override
    public void setTitle(String title)
    {
        ToolbarToolkit.getInstance().
                setTitle((Toolbar)getActivity().findViewById(R.id.toolbar),title);

    }
}