package com.codenerdz.expensesmanager.activity.analysor;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.codenerdz.expensesmanager.R;
import com.codenerdz.expensesmanager.toolkit.analysor.AnalysorDBToolkit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnalysorHomeFragment extends Fragment {

    private View view;
    private WebView webView;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.common_graph_layout, container, false);
        initChart();
        return view;
    }

    private void initChart()
    {
        View stub =view.findViewById(R.id.line_chart_stub);

        if (stub instanceof ViewStub)
        {

            ((ViewStub)stub).setVisibility(View.VISIBLE);
            webView = (WebView)view.findViewById(R.id.daily_confirmed_line_chart_webview);

            WebSettings webSettings =
                    webView.getSettings();

            webSettings.setJavaScriptEnabled(true);

            webView.setWebChromeClient(new WebChromeClient());

            webView.setWebViewClient(new WebViewClient()
            {
                @Override
                public void onPageFinished(
                        WebView view,
                        String url)
                {
                    webView.loadUrl("javascript:loadSunburstChart" +
                            "("+createDataArrayForSunburstChart()+")");
                }
            });

            webView.loadUrl("file:///android_asset/"+"html/sunburst_chart.html");
            webSettings.setDomStorageEnabled(true);

        }
    }

    private String createDataArrayForSunburstChart()
    {
        Analysor[] array = AnalysorDBAdapter.getInstance().
                fetchAllExpensesDataForAnalysor(getContext());
        String spenderName;
        String categoryName;
        StringBuilder json = new StringBuilder();
        json.append("{");
        json.append("\"name\": \"Expenses\",\n\t \"children\": [\n");

        int i=0;
        while(i<array.length)
        {
            json.append("\t\t{\"name\": "+"\""+array[i].getSpenderName()+"\",\n");
            json.append("\t\t\t\"children\": [\n");
            spenderName = array[i].getSpenderName();
            while(i<array.length && spenderName.equals(array[i].getSpenderName()))
            {
                json.append("\t\t\t\t{\n");
                json.append("\t\t\t\t\t\"name\": "+"\""+array[i].getCategoryName()+"\",\n");
                json.append("\t\t\t\t\t\t\"children\": [\n");
                categoryName = array[i].getCategoryName();
                while(i<array.length && spenderName.equals(array[i].getSpenderName()) &&
                        categoryName.equals(array[i].getCategoryName()))
                {
                    json.append("\t\t\t\t\t\t\t{\"name\": \"" +
                            array[i].getPaymentMethodName() + "\", \"value\": \"" +
                            array[i].getExpenditureAmount() + "\"},\n");
                    i++;
                }
                json.deleteCharAt(json.length()-2);
                json.append("\t\t\t\t\t\t]");
                json.append("\n\t\t\t\t},\n");
            }
            json.deleteCharAt(json.length()-2);
            json.append("\t\t\t]\n");
            json.append("\t\t},\n");
        }
        json.deleteCharAt(json.length()-2);
        json.append("\t]\n");
        json.append("}");

        return json.toString();
    }





}
