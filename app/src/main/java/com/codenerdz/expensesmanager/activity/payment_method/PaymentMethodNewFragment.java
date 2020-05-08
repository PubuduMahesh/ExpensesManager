package com.codenerdz.expensesmanager.activity.payment_method;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.codenerdz.expensesmanager.R;
import com.codenerdz.expensesmanager.activity.common.NewItemFragment;
import com.codenerdz.expensesmanager.toolkit.image.PaymentMethodImageList;
import com.codenerdz.expensesmanager.activity.common.ImageAdapter;

public class PaymentMethodNewFragment extends NewItemFragment<PaymentMethod>
{
    private View view;
    private GridView gridView;
    private EditText paymentMethodNameTextField;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.new_payment_method_layout, container, false);
        gridView = (GridView) view.findViewById(R.id.grid_view);
        paymentMethodNameTextField = ((EditText)view.findViewById
                (R.id.payment_method_name_text_field));
        setHasOptionsMenu(true);
        setTitle(getResources().getString(R.string.new_payment_method));
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        ImageAdapter paymentMethodImageAdapter = new ImageAdapter(view.getContext(),
                PaymentMethodImageList.getInstance().getImageList());
        gridView.setAdapter(paymentMethodImageAdapter);
        imageItemSelectListener(gridView,paymentMethodNameTextField);
        addPaymentMethodButtonClickListener();
    }

    private void addPaymentMethodButtonClickListener()
    {
        Button addButton = (Button)view.findViewById(R.id.add_new_payment_method_button);
        addButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int paymentMethodImageSource = -100;
                boolean isSharedOption = false;
                PaymentMethod paymentMethod = new PaymentMethod();
                String paymentMethodName = paymentMethodNameTextField.getText().toString();
                if(selectedImage != null)
                {
                    paymentMethodImageSource =
                            (Integer)(((ImageView)selectedImage.findViewById(R.id.image)).getTag());
                }
                isSharedOption = isSharedOption(paymentMethod);

                if(validateAddNewItemAction(paymentMethodImageSource, paymentMethodName))
                {
                    paymentMethod.setSharedOption(isSharedOption);
                    paymentMethod.setPaymentMethodName(paymentMethodName);
                    paymentMethod.setPaymentMethodImageSource(paymentMethodImageSource);
                    createNewItem(paymentMethod);
                    getFragmentManager().popBackStack();
                }
                else
                {
                    Toast.makeText(getContext(),"Category name and picture is compulsory",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private boolean isSharedOption(PaymentMethod paymentMethod) {
        if(((RadioButton)view.findViewById(R.id.radio_button_shared)).isChecked())
        {
            return true;
        }
        return false;

    }

    @Override
    public void createNewItem(PaymentMethod paymentMethod) {
        PaymentMethodDBAdapter.getInstance().createPaymentMethod(paymentMethod,view.getContext());
    }
}
