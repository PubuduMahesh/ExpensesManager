package com.codenerdz.expensesmanager.activity.balance;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.codenerdz.expensesmanager.R;
import com.highsoft.highcharts.Common.HIChartsClasses.HIColumn;
import com.highsoft.highcharts.Common.HIChartsClasses.HICredits;
import com.highsoft.highcharts.Common.HIChartsClasses.HIOptions;
import com.highsoft.highcharts.Common.HIChartsClasses.HITitle;
import com.highsoft.highcharts.Common.HIChartsClasses.HIXAxis;
import com.highsoft.highcharts.Core.HIChartView;

import java.util.ArrayList;
import java.util.Arrays;

public class BalanceHomeFragment extends Fragment {
    private View view;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = inflater.inflate(R.layout.highchart_balance_bar_chart, container, false);
        calculateBalance();
        HIChartView chartView = view.findViewById(R.id.hc_balance_bar_chart);

        HIOptions options = new HIOptions();

        HITitle title = new HITitle();
        options.setTitle(title);

        HIXAxis xAxis = new HIXAxis();
        String[] categoriesList = new String[] {"Month"};
        xAxis.setCategories(new ArrayList<>(Arrays.asList(categoriesList)));
        options.setXAxis(new ArrayList<HIXAxis>(){{add(xAxis);}});

        HICredits credits = new HICredits();
        credits.setEnabled(false);
        options.setCredits(credits);

        HIColumn series1 = new HIColumn();
        series1.setName("Pubudu");
        Number[] series1_data = new Number[] {3000};
        series1.setData(new ArrayList<>(Arrays.asList(series1_data)));
        HIColumn series2 = new HIColumn();
        series2.setName("Aradhana");
        Number[] series2_data = new Number[] {-1500};
        series2.setData(new ArrayList<>(Arrays.asList(series2_data)));
        options.setSeries(new ArrayList<>(Arrays.asList(series1, series2)));

        chartView.setOptions(options);

        return view;
    }

    public void calculateBalance()
    {
        BalanceDBAdapter.getInstance().fetchAllExpensesForBalance(getContext());
    }
}
