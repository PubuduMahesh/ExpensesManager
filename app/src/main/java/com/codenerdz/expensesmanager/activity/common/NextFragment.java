package com.codenerdz.expensesmanager.activity.common;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.codenerdz.expensesmanager.toolkit.EMConstantToolkit;

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
        args.putString(EMConstantToolkit.PARENT_FRAGMENT,nextFragmentTag);
        fragment.setArguments(args);
        return fragment;
    }

    public String setParentFragment(Fragment fragment)
    {
        String parentFragment = "";
        Bundle arguments = fragment.getArguments();
        if(arguments != null)
        {
            parentFragment = arguments.
                    getString(EMConstantToolkit.PARENT_FRAGMENT);
        }
        return parentFragment;
    }
}
