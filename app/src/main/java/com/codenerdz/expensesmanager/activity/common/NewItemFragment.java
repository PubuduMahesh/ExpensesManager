package com.codenerdz.expensesmanager.activity.common;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.codenerdz.expensesmanager.R;
import com.codenerdz.expensesmanager.activity.category.Category;
import com.codenerdz.expensesmanager.activity.category.CategoryDBAdapter;
import com.codenerdz.expensesmanager.test.db.CategoryImageList;
import com.codenerdz.expensesmanager.toolkit.ActionValidatorToolkit;

public abstract class NewItemFragment <T> extends Fragment
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



    public boolean validateAddNewCategoryAction(int imageSource, String itemName) {
        return ActionValidatorToolkit.getInstance().isNameTextFieldEmpty(itemName) &&
                ActionValidatorToolkit.getInstance().isImageFieldEmpty(imageSource);
    }

    public abstract void createNewItem(T item);

}
