package com.codenerdz.expensesmanager.activity.category;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.codenerdz.expensesmanager.R;
import com.codenerdz.expensesmanager.activity.common.NewItemFragment;
import com.codenerdz.expensesmanager.test.db.CategoryImageList;
import com.codenerdz.expensesmanager.activity.common.ImageAdapter;
import com.codenerdz.expensesmanager.toolkit.ActionValidatorToolkit;


public class CategoryNewFragment extends NewItemFragment<Category>
{

    private View view;
    private GridView gridView;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.new_category_layout, container, false);
        gridView = (GridView) view.findViewById(R.id.grid_view);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        ImageAdapter categoryImageAdapter = new ImageAdapter(view.getContext(),
                CategoryImageList.getInstance().getImageList());
        gridView.setAdapter(categoryImageAdapter);
        imageItemSelectListener(gridView);
        addCategoryButtonClickListener();

    }

    private void addCategoryButtonClickListener()
    {
        Button addButton = (Button)view.findViewById(R.id.add_new_category_button);
        addButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int categorySource = -100;
                Category category = new Category();
                String categoryName = ((EditText)view.findViewById
                        (R.id.category_name_textfield)).getText().toString();
                if(selectedImage != null)
                {
                    categorySource =
                            (Integer)(((ImageView)selectedImage.findViewById(R.id.image)).getTag());
                }

                if(validateAddNewCategoryAction(categorySource, categoryName))
                {
                    category.setCategoryName(categoryName);
                    category.setCategorySource(categorySource);
                    createNewItem(category);
                    getFragmentManager().popBackStack();

                }

            }
        });
    }

    @Override
    public void createNewItem(Category category)
    {
        CategoryDBAdapter.getInstance().createCategory(category,view.getContext());
    }
}