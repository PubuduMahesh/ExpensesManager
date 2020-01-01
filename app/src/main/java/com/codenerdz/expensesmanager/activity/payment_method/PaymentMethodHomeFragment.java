package com.codenerdz.expensesmanager.activity.payment_method;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.codenerdz.expensesmanager.R;
import com.codenerdz.expensesmanager.test.db.PaymentMethodList;

public class PaymentMethodHomeFragment extends Fragment {

    private GridView gridView;
    private View view;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.content_grid_layout, container, false);
        gridView = (GridView) view.findViewById(R.id.grid_view);

        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        PaymentMethodAdapter paymentMethodAdapter = new PaymentMethodAdapter(view.getContext(),
                (PaymentMethodList.getInstance().getPaymentMethodsArray()));
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
}
