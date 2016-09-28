package com.blogspot.toppersdaily.technosolz.livewallpaper.Globals;

import android.content.Context;

/**
 * Created by omoshiroi on 9/22/16.
 */
public class Variables {
    private static Context context;

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        Variables.context = context;
    }
}
