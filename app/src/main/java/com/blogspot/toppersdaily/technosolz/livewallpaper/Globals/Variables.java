package com.blogspot.toppersdaily.technosolz.livewallpaper.Globals;

import android.content.Context;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import pl.droidsonroids.gif.GifDrawable;

/**
 * Created by omoshiroi on 9/22/16.
 * All the global variables values are to be stored here for efficient memory usage
 * @author Zain Aftab
 */
public class Variables {
    private static Context context;

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        Variables.context = context;
    }

    public static GifDrawable image;

    //For admob interstitial
    public static InterstitialAd mInterstitialAd;

    // For loading admob interstitials
    public static void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .build();

        mInterstitialAd.loadAd(adRequest);
    }


}
