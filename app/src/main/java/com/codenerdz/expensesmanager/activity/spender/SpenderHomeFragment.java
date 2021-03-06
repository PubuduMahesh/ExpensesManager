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

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.codenerdz.expensesmanager.R;
import com.codenerdz.expensesmanager.activity.common.NextFragment;
import com.codenerdz.expensesmanager.activity.expense.ExpensesHomeFragment;
import com.codenerdz.expensesmanager.activity.common.ToolbarDetail;
import com.codenerdz.expensesmanager.model.SpenderExpenseViewModel;
import com.codenerdz.expensesmanager.model.SponsorExpenseViewModel;
import com.codenerdz.expensesmanager.toolkit.EMConstantToolkit;
import com.codenerdz.expensesmanager.toolkit.ToolbarToolkit;

public class SpenderHomeFragment extends Fragment implements ToolbarDetail {

    private GridView gridView;
    private View view;
    private String parentFragment;
    private SpenderExpenseViewModel<Spender> spenderViewModel;
    private SponsorExpenseViewModel<Spender> sponsorViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        parentFragment = NextFragment.getInstance().setParentFragment(this);
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
        spenderViewModel = ViewModelProviders.of(getActivity()).get(SpenderExpenseViewModel .class);
        sponsorViewModel = ViewModelProviders.of(getActivity()).get(SponsorExpenseViewModel.class);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                if(parentFragment!=null && parentFragment.equals(EMConstantToolkit.
                        EXPENSER_NEW_AS_PARENT_FRAGMENT))
                {
                    sponsorViewModel.selectedItem((Spender)gridView.getItemAtPosition(position));
                    getFragmentManager().popBackStackImmediate();
                }
                else
                {
                    spenderViewModel.selectedItem((Spender)gridView.getItemAtPosition(position));
                    openExpenseHomeFragment();
                }
            }
        });
    }

    private void openExpenseHomeFragment() {
        getFragmentManager().beginTransaction()
                .replace(((ViewGroup)getView().getParent()).getId(),
                        new ExpensesHomeFragment(),"")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void setTitle(String title)
    {
        ToolbarToolkit.getInstance().
                setTitle((Toolbar)getActivity().findViewById(R.id.toolbar),title);

    }
}