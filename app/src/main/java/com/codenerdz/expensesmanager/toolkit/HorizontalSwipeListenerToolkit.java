package com.codenerdz.expensesmanager.toolkit;

import android.view.MotionEvent;
import android.view.View;

public class HorizontalSwipeListenerToolkit implements View.OnTouchListener
{
    private float firstX;
    private int minDistance = 200;


    @Override
    public boolean onTouch(View view, MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                firstX = event.getX();
                return true;
            case MotionEvent.ACTION_UP:
                float secondX = event.getX();
                if (Math.abs(secondX - firstX) > minDistance) {
                    if (secondX > firstX) {
                        return true;
                    } else {
                        return false;
                    }
                }
                return false;
        }
        return false;
    }
}
