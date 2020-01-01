package com.codenerdz.expensesmanager.toolkit;

import android.graphics.Bitmap;
import android.media.Image;

import java.io.ByteArrayOutputStream;

public class ImageToolkit {
    private static class SingeltonHolder
    {
        public static  ImageToolkit instance = new ImageToolkit();
    }

    private ImageToolkit()
    {

    }

    public static ImageToolkit getInstance()
    {
        return SingeltonHolder.instance;
    }

    public byte[] getBitMapAsByteArray(Bitmap bitMap)
    {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitMap.compress(Bitmap.CompressFormat.PNG,0,outputStream);
        return outputStream.toByteArray();
    }
}
