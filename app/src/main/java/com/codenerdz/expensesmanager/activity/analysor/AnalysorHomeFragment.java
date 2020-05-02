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

import java.util.ArrayList;
import java.util.HashMap;

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
                    webView.loadUrl("javascript:loadSunburstChart()");
                }
            });

            webView.loadUrl("file:///android_asset/"+"html/sunburst_chart.html");
            webSettings.setDomStorageEnabled(true);

        }
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
