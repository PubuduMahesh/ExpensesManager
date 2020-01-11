package com.codenerdz.expensesmanager.toolkit;

import androidx.appcompat.widget.Toolbar;

import com.codenerdz.expensesmanager.R;

public class ToolbarToolkit
{
    private static class SingeltonHolder
    {
        public static ToolbarToolkit instance = new ToolbarToolkit();
    }

    private ToolbarToolkit()
    {

    }

    public static ToolbarToolkit getInstance()
    {
        return SingeltonHolder.instance;
    }

    public void setTitle(Toolbar toolbar,String title)
    {
        toolbar.setTitle(title);
    }
}
