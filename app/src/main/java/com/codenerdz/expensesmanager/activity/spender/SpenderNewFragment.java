package com.codenerdz.expensesmanager.activity.spender;

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
import com.codenerdz.expensesmanager.activity.category.Category;
import com.codenerdz.expensesmanager.activity.common.ImageAdapter;
import com.codenerdz.expensesmanager.activity.common.NewItemFragment;
import com.codenerdz.expensesmanager.test.db.CategoryImageList;

public class SpenderNewFragment extends NewItemFragment<Spender>
{
    private View view;
    private GridView gridView;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.new_spender_layout, container, false);
        gridView = (GridView) view.findViewById(R.id.grid_view);
        setHasOptionsMenu(true);
        setTitle(getResources().getString(R.string.new_spender));
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        ImageAdapter spenderImageAdapter = new ImageAdapter(view.getContext(),
                CategoryImageList.getInstance().getImageList());
        gridView.setAdapter(spenderImageAdapter);
        imageItemSelectListener(gridView);
        addSpenderButtonClickListener();

    }

    private void addSpenderButtonClickListener()
    {
        Button addButton = (Button)view.findViewById(R.id.add_new_spender_button);
        addButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int spenderImageSource = -100;
                Spender spender = new Spender();
                String spenderName = ((EditText)view.findViewById
                        (R.id.spender_name_textfield)).getText().toString();
                if(selectedImage != null)
                {
                    spenderImageSource =
                            (Integer)(((ImageView)selectedImage.findViewById(R.id.image)).getTag());
                }

                if(validateAddNewItemAction(spenderImageSource, spenderName))
                {
                    spender.setSpenderName(spenderName);
                    spender.setSpenderImageSource(spenderImageSource);
                    createNewItem(spender);
                    getFragmentManager().popBackStack();

                }

            }
        });
    }

    @Override
    public void createNewItem(Spender spender)
    {
        SpenderDBAdapter.getInstance().createSpender(spender,view.getContext());
    }
}