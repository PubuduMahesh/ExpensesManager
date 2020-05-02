package com.codenerdz.expensesmanager.activity.common;

import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.codenerdz.expensesmanager.R;
import com.codenerdz.expensesmanager.toolkit.ActionValidatorToolkit;
import com.codenerdz.expensesmanager.toolkit.ToolbarToolkit;

public abstract class NewItemFragment <T> extends Fragment implements ToolbarDetail
{
    public View selectedImage;

    public void imageItemSelectListener(final GridView gridView)
    {
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            int preSelGridItem = -1;
            View viewPrev;
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(preSelGridItem != -1)
                {
                    viewPrev = (View) gridView.getChildAt(preSelGridItem);
                    viewPrev.setBackgroundColor(getResources().getColor(R.color.white));
                }
                preSelGridItem = position;
                if(preSelGridItem == position)
                {
                    selectedImage = (View) gridView.getChildAt(position);
                    selectedImage.setBackgroundColor(getResources().getColor(R.color.blue));
                }
            }
        });
    }



    public boolean validateAddNewItemAction(int imageSource, String itemName) {
        return ActionValidatorToolkit.getInstance().isNameTextFieldEmpty(itemName) &&
                ActionValidatorToolkit.getInstance().isImageFieldEmpty(imageSource);
    }

    public abstract void createNewItem(T item);

    @Override
    public void setTitle(String title) {
        ToolbarToolkit.getInstance().
                setTitle((Toolbar)getActivity().findViewById(R.id.toolbar),title);
    }
}
