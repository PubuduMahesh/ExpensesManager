package com.codenerdz.expensesmanager.activity.analysor;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.codenerdz.expensesmanager.R;
import com.highsoft.highcharts.Common.HIChartsClasses.HIChart;
import com.highsoft.highcharts.Common.HIChartsClasses.HIColorVariation;
import com.highsoft.highcharts.Common.HIChartsClasses.HIDataLabels;
import com.highsoft.highcharts.Common.HIChartsClasses.HIFilter;
import com.highsoft.highcharts.Common.HIChartsClasses.HILevels;
import com.highsoft.highcharts.Common.HIChartsClasses.HIOptions;
import com.highsoft.highcharts.Common.HIChartsClasses.HISubtitle;
import com.highsoft.highcharts.Common.HIChartsClasses.HISunburst;
import com.highsoft.highcharts.Common.HIChartsClasses.HITitle;
import com.highsoft.highcharts.Common.HIChartsClasses.HITooltip;
import com.highsoft.highcharts.Core.HIChartView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class AnalysorHomeFragment extends Fragment {

    private View view;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.highchart, container, false);

        HIChartView chartView = (HIChartView) view.findViewById(R.id.hc);

        chartView.plugins = new ArrayList<>(Arrays.asList("sunburst"));

        HIOptions options = new HIOptions();

        HIChart chart = new HIChart();
        chart.setZoomType("xy");
        options.setChart(chart);

        HITitle title = new HITitle();
        title.setText("Monthly Expenses");
        options.setTitle(title);

        HISubtitle subtitle = new HISubtitle();
        options.setSubtitle(subtitle);

        HISunburst series = new HISunburst();

        fillData(series);

        series.setAllowDrillToNode(true);
        series.setCursor("pointer");

        HIDataLabels datalabels = new HIDataLabels();
        datalabels.setFormat("{point.name}");
        datalabels.setFilter(new HIFilter());
        datalabels.getFilter().setProperty("innerArcLength");
        datalabels.getFilter().setOperator(">");
        datalabels.getFilter().setValue(64);
        series.setDataLabels(datalabels);

        HILevels levels1 = new HILevels();
        HashMap<String, Object> levelDataLabels = new HashMap<>();
        levelDataLabels.put("rotationMode", "parallel");
        HashMap<String, Object> filter = new HashMap<>();
        filter.put("property", "outerArcLength");
        filter.put("operator", ">");
        filter.put("value", 64);
        levelDataLabels.put("filter", filter);
        levels1.setDataLabels(levelDataLabels);

        HILevels levels2 = new HILevels();
        levels2.setLevel(2);
        HashMap<String, Object> levelDataLabels2 = new HashMap<>();
        levelDataLabels2.put("rotationMode", "parallel");
        levels2.setDataLabels(levelDataLabels2);

        HILevels levels3 = new HILevels();
        levels3.setLevel(3);
        levels3.setColorVariation(new HIColorVariation());
        levels3.getColorVariation().setKey("brightness");
        levels3.getColorVariation().setTo(-0.5);

        HILevels levels4 = new HILevels();
        levels4.setLevel(4);
        levels4.setColorVariation(new HIColorVariation());
        levels4.getColorVariation().setKey("brightness");
        levels4.getColorVariation().setTo(0.5);

        series.setLevels(new ArrayList<>(Arrays.asList(levels1, levels2, levels3, levels4)));

        options.setSeries(new ArrayList<>(Collections.singletonList(series)));

        HITooltip tooltip = new HITooltip();
        tooltip.setHeaderFormat("");
        tooltip.setPointFormat("The expenditure amount of <b>{point.name}" +
                "</b> is <b>{point.value}</b>");
        options.setTooltip(tooltip);

        chartView.setOptions(options);
        chartView.setHovered(true);
        return view;
    }

    private void fillData(HISunburst series) {

        series.setData(new ArrayList<>(createHashMapArrayListForSunburstChart(AnalysorDBAdapter.
                getInstance().fetchAllExpensesDataForAnalysor(getContext()))));
    }

    private ArrayList<HashMap<String,Object>>
    createHashMapArrayListForSunburstChart(Analysor[] analysorArray) {
        HashMap<String, Object> map0 = new HashMap<>();
        map0.put("id", "0.0");
        map0.put("parent","");
        map0.put("name", "Expense");
        ArrayList<HashMap<String,Object>> list = new ArrayList<>();
        list.add(map0);
        double spenderID = 1.0;
        double categoryID = 2.0;
        double paymentMethodID = 3.0;
        for(int i=0; i < analysorArray.length;)
        {
            int currentSpender = analysorArray[i].getExpenser();
            HashMap<String, Object> spenderMap = new HashMap<>();
            spenderMap.put("id",Double.toString(spenderID));
            spenderMap.put("parent","0.0");
            spenderMap.put("name",analysorArray[i].getSpenderName());
            list.add(spenderMap);
            while(i<analysorArray.length && currentSpender == analysorArray[i].getExpenser())
            {
                int currentCategory = analysorArray[i].getExpenditureCategoryID();
                HashMap<String, Object> categoryMap = new HashMap<>();
                categoryMap.put("id",Double.toString(categoryID));
                categoryMap.put("parent",Double.toString(spenderID));
                categoryMap.put("name",analysorArray[i].getCategoryName());
                list.add(categoryMap);
                while(i<analysorArray.length &&currentCategory == analysorArray[i].
                        getExpenditureCategoryID() &&
                        currentSpender == analysorArray[i].getExpenser() )
                {
                    HashMap<String, Object> paymentMethodMap = new HashMap<>();
                    paymentMethodMap.put("id",Double.toString(paymentMethodID));
                    paymentMethodMap.put("parent",Double.toString(categoryID));
                    paymentMethodMap.put("name",analysorArray[i].getPaymentMethodName());
                    paymentMethodMap.put("value",analysorArray[i].getExpenditureAmount());
                    list.add(paymentMethodMap);
                    paymentMethodID+=0.1;
                    i++;

                }
                categoryID+=0.1;
            }
            spenderID+=0.1;

        }
        return list;
    }


}
