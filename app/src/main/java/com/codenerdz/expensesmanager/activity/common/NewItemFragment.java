package com.codenerdz.expensesmanager.activity.common;

import android.os.Build;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.codenerdz.expensesmanager.R;
import com.codenerdz.expensesmanager.toolkit.ActionValidatorToolkit;
import com.codenerdz.expensesmanager.toolkit.ToolbarToolkit;

public abstract class NewItemFragment <T> extends Fragment implements ToolbarDetail
{
    public View selectedImage;

    public void imageItemSelectListener(final GridView gridView, final EditText editText)
    {
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            int preSelGridItem = -1;
            View viewPrev;
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                editText.setEnabled(false);
                if(preSelGridItem != -1)
                {
                    viewPrev = (View) gridView.
                            getChildAt(preSelGridItem-gridView.getFirstVisiblePosition());
                    if(viewPrev != null)
                    {
                        viewPrev.setBackgroundColor(getResources().getColor(R.color.white));
                    }
                }
                preSelGridItem = position;
                if(preSelGridItem == position)
                {
                    selectedImage = (View) gridView.
                            getChildAt(position-gridView.getFirstVisiblePosition());
                    selectedImage.setBackgroundColor(getResources().getColor(R.color.blue));
                }
                editText.setEnabled(true);
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

    public void addTreeObserveListener(GridView gridView,EditText editText)
    {
        gridView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {

                    @Override
                    public void onGlobalLayout() {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            gridView.getViewTreeObserver()
                                    .removeOnGlobalLayoutListener(this);
                        }
                        else
                        {
                            gridView.getViewTreeObserver()
                                    .removeGlobalOnLayoutListener(this);
                        }
                        imageItemSelectListener(gridView,editText);
                        selectedImage = (View) gridView.getChildAt(22);
                        System.out.println();
                    }
                });
    }
}
