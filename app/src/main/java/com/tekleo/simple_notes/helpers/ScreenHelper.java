package com.tekleo.simple_notes.helpers;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Point;
import android.view.Display;

/**
 * Created by Leo on 31-Jan-16.
 */
public abstract class ScreenHelper
{
    public static int WIDTH;
    public static int HEIGHT;
    public static boolean loaded = false;

    public static void initialize(Activity activity) {
        Point sizePoint = new Point();
        Display display = activity.getWindowManager().getDefaultDisplay();

        display.getSize(sizePoint);
        WIDTH = sizePoint.x; HEIGHT = sizePoint.y;
        loaded = true;
    }

    public static int getScreenOrientation(Activity activity)
    {
        int orientation = Configuration.ORIENTATION_SQUARE;
        Display display = activity.getWindowManager().getDefaultDisplay();

        if (display.getWidth() < display.getHeight())
            orientation = Configuration.ORIENTATION_PORTRAIT;
        else if (display.getWidth() > display.getHeight())
            orientation = Configuration.ORIENTATION_LANDSCAPE;

        return orientation;
    }

    public static boolean isPortrait(Activity activity) {
        return getScreenOrientation(activity) == Configuration.ORIENTATION_PORTRAIT;
    }

    public static boolean isLandscape(Activity activity) {
        return getScreenOrientation(activity) == Configuration.ORIENTATION_LANDSCAPE;
    }
}
