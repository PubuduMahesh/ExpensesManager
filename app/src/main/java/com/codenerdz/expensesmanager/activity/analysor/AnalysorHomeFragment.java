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
import com.highsoft.highcharts.Common.HIChartsClasses.HIResetZoomButton;
import com.highsoft.highcharts.Common.HIChartsClasses.HISubtitle;
import com.highsoft.highcharts.Common.HIChartsClasses.HISunburst;
import com.highsoft.highcharts.Common.HIChartsClasses.HITitle;
import com.highsoft.highcharts.Common.HIChartsClasses.HITooltip;
import com.highsoft.highcharts.Common.HIColor;
import com.highsoft.highcharts.Core.HIChartView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

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
        tooltip.setPointFormat("The expenditure amount of <b>{point.name}</b> is <b>{point.value}</b>");
        options.setTooltip(tooltip);

        chartView.setOptions(options);
        chartView.setHovered(true);
        return view;
    }

    private void fillData(HISunburst series) {
        HashMap<String, Object> map1 = new HashMap<>();
        map1.put("id", "0.0");
        map1.put("parent", "");
        map1.put("name", "Expense");
        map1.put("color","#003300");

        HashMap<String, Object> map2 = new HashMap<>();
        map2.put("id", "1.0");
        map2.put("parent","0.0");
        map2.put("name", "Pubudu");
        map2.put("color", "#ffff381a");

        HashMap<String, Object> map3 = new HashMap<>();
        map3.put("id", "1.1");
        map3.put("parent","0.0");
        map3.put("name", "Aradhana");
        map3.put("color", "#ff1118ff");

        HashMap<String, Object> map4 = new HashMap<>();
        map4.put("id", "2.0");
        map4.put("parent","1.0");
        map4.put("name", "Cash");
        HashMap<String, Object> map5 = new HashMap<>();
        map5.put("id", "2.1");
        map5.put("parent","1.0");
        map5.put("name", "SampathSharedDebitCard");
        HashMap<String, Object> map6 = new HashMap<>();
        map6.put("id", "2.2");
        map6.put("parent","1.0");
        map6.put("name", "CreditCard");

        HashMap<String, Object> map7 = new HashMap<>();
        map7.put("id", "2.3");
        map7.put("parent","1.1");
        map7.put("name", "CreditCard");
        HashMap<String, Object> map8 = new HashMap<>();
        map8.put("id", "2.4");
        map8.put("parent","1.1");
        map8.put("name", "SampathSharedDebitCard");

        HashMap<String, Object> map9 = new HashMap<>();
        map9.put("id", "3.0");
        map9.put("parent","2.0");
        map9.put("name", "Cloth");
        map9.put("value", 3500);
        HashMap<String, Object> map10 = new HashMap<>();
        map10.put("id", "3.1");
        map10.put("parent","2.1");
        map10.put("name", "Food");
        map10.put("value", 350);
        HashMap<String, Object> map11 = new HashMap<>();
        map11.put("id", "3.2");
        map11.put("parent","2.1");
        map11.put("name", "Keels");
        map11.put("value", 1280);
        HashMap<String, Object> map12 = new HashMap<>();
        map12.put("id", "3.3");
        map12.put("parent","2.2");
        map12.put("name", "Petrol");
        map12.put("value", 1400);

        HashMap<String, Object> map13 = new HashMap<>();
        map13.put("id", "3.4");
        map13.put("parent","2.3");
        map13.put("name", "Petrol");
        map13.put("value", 1400);
        HashMap<String, Object> map14 = new HashMap<>();
        map14.put("id", "3.5");
        map14.put("parent","2.4");
        map14.put("name", "Keels");
        map14.put("value", 1280);


        series.setData(new ArrayList<>(Arrays.asList(map1, map2, map3, map4, map5, map6, map7, map8, map9, map10, map11, map12, map13, map14)));
    }


}
