package com.welab.league.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;

public class Utils {

    public static int getColor(Context context, int id) {
        final int version = Build.VERSION.SDK_INT;

        if (version >= Build.VERSION_CODES.M) {
            return context.getColor(id);
        } else {
            return context.getResources().getColor(id);
        }
    }

    public static Drawable getDrawable(Context context, int id) {
        final int version = Build.VERSION.SDK_INT;

        if (version >= Build.VERSION_CODES.LOLLIPOP) {
            return context.getDrawable(id);
        } else {
            return context.getResources().getDrawable(id);
        }
    }
}
