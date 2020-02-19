package com.codenerdz.expensesmanager.activity.Expense;

import android.app.Activity;
import android.content.Context;
import android.util.StateSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import com.codenerdz.expensesmanager.R;
import com.codenerdz.expensesmanager.activity.category.Category;
import com.codenerdz.expensesmanager.activity.common.CommonItemAdapter;
import com.codenerdz.expensesmanager.model.CategoryExpenseViewModel;
import com.codenerdz.expensesmanager.model.ExpensesViewModel;

public class ExpensesAdapter extends CommonItemAdapter <Expense>
{
    private Context context;

    public ExpensesAdapter(Context context, Expense[] items)
    {
        super(context, items);
        this.context = context;
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

        CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.expense_item_checkbox);
        addCheckBoxClickListener(checkBox,expense,position);

        description.setText(expense.getExpenditureDescription());
        value.setText(expense.getExpenditureAmount()+"");
        return convertView;
    }

    private void addCheckBoxClickListener(CheckBox checkBox,Expense expense,int possition)
    {
        checkBox.setOnClickListener(new View.OnClickListener(){

            @Override public void onClick(View v) {
                ExpensesViewModel model = ViewModelProviders.of((FragmentActivity) context).get(ExpensesViewModel.class);
                expense.setSelectedInList(checkBox.isSelected()?true:false);
                model.updateExpensesInList(possition,expense);
            }
        });
    }

}
