package com.codenerdz.expensesmanager.toolkit;

import androidx.appcompat.widget.Toolbar;

public class ToolbarToolkit
{
    private static class SingletonHolder
    {
        public static ToolbarToolkit instance = new ToolbarToolkit();
    }

    private ToolbarToolkit()
    {

    }

    public static ToolbarToolkit getInstance()
    {
        return SingletonHolder.instance;
    }

    public void setTitle(Toolbar toolbar,String title)
    {
        toolbar.setTitle(title);
    }
}
