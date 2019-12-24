package com.codenerdz.expensesmanager.ui.category;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.codenerdz.expensesmanager.R;
import com.codenerdz.expensesmanager.test.db.CategoryImageList;
import com.codenerdz.expensesmanager.ui.common.ImageAdapter;


public class CategoryNewFragment extends Fragment {

    private View view;
    private GridView gridView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.new_category_layout, container, false);
        gridView = (GridView) view.findViewById(R.id.grid_view);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ImageAdapter categoryAdapter = new ImageAdapter(view.getContext(),
                CategoryImageList.getInstance().getImageList());
        gridView.setAdapter(categoryAdapter);
    }
}
