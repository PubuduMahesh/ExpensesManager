package com.codenerdz.expensesmanager.ui.category;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import com.codenerdz.expensesmanager.R;

public class CategoryFragment extends Fragment {


    private GridView gridView;
    private View view;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.content_grid_layout, container, false);
        gridView = (GridView) view.findViewById(R.id.grid_view);

        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        /*temp*/
        Category category1=new Category("Keels",1,R.drawable.image_1);
        Category category2=new Category("Sanitory",2,R.drawable.image_2);
        Category category3=new Category("Fuel",3,R.drawable.image_3);
        Category category4=new Category("Gift",4,R.drawable.image_1);
        Category category5=new Category("Sanitory",2,R.drawable.image_2);
        Category category6=new Category("Fuel",3,R.drawable.image_3);
        Category category7=new Category("Gift",4,R.drawable.image_1);
        Category category8=new Category("Sanitory",2,R.drawable.image_2);
        Category category9=new Category("Fuel",3,R.drawable.image_3);
        Category category10=new Category("Gift",4,R.drawable.image_1);
        Category[] categories = {category1,category2,category3,category4,category5,category6,category7,category8,category9,category10};
        CategoryAdapter categoryAdapter = new CategoryAdapter(view.getContext(),categories);
        gridView.setAdapter(categoryAdapter);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.category_actionbar_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.action_create_new_category:
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.new_category,new NewCategory()).commit();
                break;
                default:
                    break;
        }

        return super.onOptionsItemSelected(item);
    }

}