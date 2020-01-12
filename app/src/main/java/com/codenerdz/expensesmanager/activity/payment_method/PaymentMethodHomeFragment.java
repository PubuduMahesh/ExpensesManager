package com.codenerdz.expensesmanager.activity.payment_method;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.codenerdz.expensesmanager.R;
import com.codenerdz.expensesmanager.activity.common.ToolbarDetail;
import com.codenerdz.expensesmanager.model.CategoryExpenseViewModel;
import com.codenerdz.expensesmanager.model.PaymentMethodExpenseViewModel;
import com.codenerdz.expensesmanager.toolkit.ToolbarToolkit;

public class PaymentMethodHomeFragment extends Fragment implements ToolbarDetail
{

    private GridView gridView;
    private View view;
    private PaymentMethodExpenseViewModel<PaymentMethod> model;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.content_grid_layout, container, false);
        gridView = (GridView) view.findViewById(R.id.grid_view);
        setTitle(getResources().getString(R.string.menu_payment_method));
        setHasOptionsMenu(true);
        setSlectedItem();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        PaymentMethodAdapter paymentMethodAdapter = new PaymentMethodAdapter(view.getContext(),
                PaymentMethodDBAdapter.getInstance().fetchAllPaymentMethods(view.getContext()));
        gridView.setAdapter(paymentMethodAdapter);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.payment_method_actionbar_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.action_create_new_payment_method:
                getFragmentManager().beginTransaction().replace(((ViewGroup)getView().getParent()).
                        getId(),new PaymentMethodNewFragment(),null).addToBackStack(null).
                        commit();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setTitle(String title)
    {
        ToolbarToolkit.getInstance().
                setTitle((Toolbar)getActivity().findViewById(R.id.toolbar),title);

    }

    private void setSlectedItem() {
        model = ViewModelProviders.of(getActivity()).get(PaymentMethodExpenseViewModel.class);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                PaymentMethod paymentMethod = (PaymentMethod) gridView.getItemAtPosition(position);
                model.selectItem(paymentMethod);
                getFragmentManager().popBackStack();
            }
        });
    }
}
