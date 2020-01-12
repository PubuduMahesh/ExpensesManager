package com.codenerdz.expensesmanager.activity.Expense;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.codenerdz.expensesmanager.R;
import com.codenerdz.expensesmanager.activity.common.CommonItemAdapter;

public class ExpensesAdapter extends CommonItemAdapter <Expense>
{

    public ExpensesAdapter(Context context, Expense[] items)
    {
        super(context, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        final Expense expense = items[position];
        if (convertView == null)
        {
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.expenses_list_item_layout, parent, false);
        }
        final TextView description =
                (TextView)convertView.findViewById(R.id.expense_reason_text_field);
        final TextView value = (TextView)convertView.findViewById(R.id.expense_value_text_field);

        description.setText(expense.getExpenditureDescription());
        value.setText(expense.getExpenditureAmount()+"");
        return convertView;
    }

}
