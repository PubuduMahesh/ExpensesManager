package com.codenerdz.expensesmanager.toolkit;

public class ActionValidatorToolkit
{
    private static class SingeltonHolder
    {
        public static ActionValidatorToolkit instance = new ActionValidatorToolkit();
    }

    private ActionValidatorToolkit()
    {

    }

    public static ActionValidatorToolkit getInstance()
    {
        return SingeltonHolder.instance;
    }

    public boolean isNameTextFieldEmpty(String name)
    {
        if(name.length()<=0)
        {
            return false;
        }
        return  true;
    }

    public boolean isImageFieldEmpty(int imageSource)
    {
        if(imageSource>0)
        {
            return true;
        }
        return false;
    }
}
