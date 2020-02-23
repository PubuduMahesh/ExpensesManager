package com.codenerdz.expensesmanager.activity.category;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.codenerdz.expensesmanager.R;
import com.codenerdz.expensesmanager.activity.common.NextFragment;
import com.codenerdz.expensesmanager.activity.common.ToolbarDetail;
import com.codenerdz.expensesmanager.model.CategoryExpenseViewModel;
import com.codenerdz.expensesmanager.toolkit.EMConstantToolkit;
import com.codenerdz.expensesmanager.toolkit.ToolbarToolkit;

/**
 * Initially display available category list as a grid.
 */
public class CategoryHomeFragment extends Fragment implements ToolbarDetail {


    private GridView gridView;
    private View view;
    private CategoryExpenseViewModel<Category> model;
    private String parentFragment;
    private ActionMode actionMode;
    private Category selectedCategory;
    private CategoryAdapter categoryAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        parentFragment = NextFragment.getInstance().setParentFragment(this);
        view = inflater.inflate(R.layout.content_grid_layout, container, false);
        gridView = (GridView) view.findViewById(R.id.grid_view);
        setTitle(getResources().getString(R.string.menu_category));
        setHasOptionsMenu(true);
        if(parentFragment!=null && parentFragment.equals(EMConstantToolkit.
                EXPENSER_NEW_AS_PARENT_FRAGMENT))
        {
            setSelectedItem();
        }
        else
        {
            setLongClickListener();
        }
        return view;
    }

    private void setLongClickListener() {
        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int position, long arg3) {
                if(actionMode != null)
                {
                    return false;
                }
                selectedCategory = (Category)gridView.getItemAtPosition(position);
                actionMode = ((AppCompatActivity) getActivity()).
                        startSupportActionMode(actionModeCallback);
                return false;
            }
        });
    }

    private ActionMode.Callback actionModeCallback = new ActionMode.Callback()
    {

        @Override public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.item_delete_action,menu);
            mode.setTitle("delete selected item");
            return true;
        }

        @Override public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId())
            {
                case R.id.item_delete:
                        CategoryDBAdapter.getInstance().
                                deleteCategoryById(getContext(),selectedCategory);
                        mode.finish();
                        loadCategory();
                        return true;
                default:
                    return false;
            }
        }

        @Override public void onDestroyActionMode(ActionMode mode) {
            actionMode = null;
        }
    };

    private void setSelectedItem()
    {
        model = ViewModelProviders.of(getActivity()).get(CategoryExpenseViewModel.class);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Category category = (Category)gridView.getItemAtPosition(position);
                model.selectItem(category);
                getFragmentManager().popBackStack();
            }
        });

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        loadCategory();
    }

    private void loadCategory() {
        categoryAdapter = new CategoryAdapter(view.getContext(),
                CategoryDBAdapter.getInstance().fetchAllCategory(view.getContext()));
        gridView.setAdapter(categoryAdapter);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.category_actionbar_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.action_create_new_category:
                getFragmentManager().beginTransaction()
                        .replace(((ViewGroup)getView().getParent()).getId(),
                                new CategoryNewFragment(),"new category fragment")
                        .addToBackStack(null)
                        .commit();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setTitle(String title)
    {
        ToolbarToolkit.getInstance().
                setTitle((Toolbar)getActivity().findViewById(R.id.toolbar),title);
    }
}