package com.codenerdz.expensesmanager.activity.payment_method;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.codenerdz.expensesmanager.R;
import com.codenerdz.expensesmanager.activity.category.Category;
import com.codenerdz.expensesmanager.activity.category.CategoryDBAdapter;
import com.codenerdz.expensesmanager.activity.common.NextFragment;
import com.codenerdz.expensesmanager.activity.common.ToolbarDetail;
import com.codenerdz.expensesmanager.model.PaymentMethodExpenseViewModel;
import com.codenerdz.expensesmanager.toolkit.DBAdapterTollkit;
import com.codenerdz.expensesmanager.toolkit.EMConstantToolkit;
import com.codenerdz.expensesmanager.toolkit.ToolbarToolkit;
import com.codenerdz.expensesmanager.toolkit.category.CategoryDBToolkit;
import com.codenerdz.expensesmanager.toolkit.payment_method.PaymentMethodDBToolkit;

public class PaymentMethodHomeFragment extends Fragment implements ToolbarDetail
{

    private GridView gridView;
    private View view;
    private PaymentMethodExpenseViewModel<PaymentMethod> model;
    private String parentFragment;
    private ActionMode actionMode;
    private PaymentMethod selectedPaymentMethod;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        parentFragment = NextFragment.getInstance().setParentFragment(this);
        view = inflater.inflate(R.layout.content_grid_layout, container, false);
        gridView = (GridView) view.findViewById(R.id.grid_view);
        setTitle(getResources().getString(R.string.menu_payment_method));
        setHasOptionsMenu(true);
        if(parentFragment!=null && parentFragment.equals(EMConstantToolkit.
                EXPENSER_NEW_AS_PARENT_FRAGMENT))
        {
            setSelectedItem();
        }
        else
        {
            gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                               int position, long arg3) {
                    setLongClickListener();
                    return false;
                }
            });
        }
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadPaymentMethod();
    }

    private void loadPaymentMethod() {
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

    private void setSelectedItem() {
        model = ViewModelProviders.of(getActivity()).get(PaymentMethodExpenseViewModel.class);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                PaymentMethod paymentMethod = (PaymentMethod) gridView.getItemAtPosition(position);
                model.selectItem(paymentMethod);
                getFragmentManager().popBackStackImmediate();
            }
        });
    }
    private void setLongClickListener() {
        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int position, long arg3) {
                if(actionMode != null)
                {
                    return false;
                }
                selectedPaymentMethod = (PaymentMethod) gridView.getItemAtPosition(position);
                actionMode = ((AppCompatActivity) getActivity()).
                        startSupportActionMode(actionModeCallback);
                return false;
            }
        });
    }

    private ActionMode.Callback actionModeCallback = new ActionMode.Callback()
    {

        @Override public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.item_delete_action,menu);
            mode.setTitle("delete selected item");
            return true;
        }

        @Override public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId())
            {
                case R.id.item_delete:
                    PaymentMethodDBAdapter.getInstance().deletePaymentMethodByID(getContext()
                            ,selectedPaymentMethod);
                    mode.finish();
                    loadPaymentMethod();
                    return true;
                default:
                    return false;
            }
        }

        @Override public void onDestroyActionMode(ActionMode mode) {
            actionMode = null;
        }
    };
}
