package com.codenerdz.expensesmanager.activity.common;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.codenerdz.expensesmanager.activity.Expense.ExpensesHomeFragment;
import com.codenerdz.expensesmanager.toolkit.ExpensesManagerConstantToolkit;

public class NextFragment extends Fragment
{
    private static class SingeltonHolder
    {
        public static NextFragment instance = new NextFragment();
    }

    private NextFragment()
    {

    }

    public static NextFragment getInstance()
    {
        return SingeltonHolder.instance;
    }

    public Fragment setArgumentsForNextFragment(Fragment fragment,String nextFragmentTag)
    {
        Bundle args = new Bundle();
        args.putString(ExpensesManagerConstantToolkit.PARENT_FRAGMENT,nextFragmentTag);
        fragment.setArguments(args);
        return fragment;
    }

    public String setParentFragment(Fragment fragment)
    {
        String parentFragment = "";
        Bundle arguments = fragment.getArguments();
        if(arguments != null)
        {
            parentFragment = getArguments().
                    getString(ExpensesManagerConstantToolkit.PARENT_FRAGMENT);
        }
        return parentFragment;
    }
}
