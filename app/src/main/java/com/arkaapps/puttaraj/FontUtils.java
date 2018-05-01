package com.arkaapps.puttaraj;

import android.content.Context;
import android.graphics.Typeface;

public class FontUtils {


    public Typeface getBaskervilleFont(Context context) {
        Typeface typeface = null;
        try {
            typeface = Typeface.createFromAsset(context.getAssets(), "font/Baskerville.ttf");
        } catch (Exception e) {
            typeface = Typeface.DEFAULT;
        }
        return typeface;
    }

    public Typeface getCalibriFont(Context context) {
        Typeface typeface = null;
        try {
            typeface = Typeface.createFromAsset(context.getAssets(), "font/Baskerville.ttf");
        } catch (Exception e) {
            typeface = Typeface.DEFAULT;
        }
        return typeface;
    }
}
