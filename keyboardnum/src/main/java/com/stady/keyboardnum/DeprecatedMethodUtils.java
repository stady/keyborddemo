package com.stady.keyboardnum;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;

/**
 * Created by stady on 2017/3/1.
 */

public class DeprecatedMethodUtils {

    @SuppressWarnings("deprecation")
    public static Drawable deResources_getDrawable(Resources res, int resID, Resources.Theme theme){
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            return  res.getDrawable(resID,theme);
        }else{
            return  res.getDrawable(resID);
        }
    }
}
