package com.blogspot.toppersdaily.technosolz.livewallpaper.Globals;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings.Secure;

import com.blogspot.toppersdaily.technosolz.livewallpaper.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.InterstitialAd;

/**
 * Created by omoshiroi on 9/22/16.
 */
public class Functions {
    public static void inviteFriends() {
        int applicationNameId = Variables.getContext().getApplicationInfo().labelRes;
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_SUBJECT, Constants.app_id);
        String text = "Install this cool application: " + Variables.getContext().getString(R.string.app_name);
        String link = Constants.marker_url + Constants.app_id;
        i.putExtra(Intent.EXTRA_TEXT, text + " " + link);
        Variables.getContext().startActivity(Intent.createChooser(i, "Share via:"));
    }

    public void rateApp() {
        final Uri uri = Uri.parse(Constants.market_uri + Constants.app_id);
        try {
            Variables.getContext().startActivity(new Intent(Intent.ACTION_VIEW, uri));
        } catch (ActivityNotFoundException ignored) {
            Variables.getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getStoreUrl())));
        }
    }


    private String getStoreUrl() {
        return Constants.marker_url + Constants.app_id;
    }

//    public static InterstitialAd mInterstitialAd;
//
//    public static void loadInterstitialAd(Context context) {
//        mInterstitialAd = new InterstitialAd(context);
//        mInterstitialAd.setAdUnitId(Constants.ad_interstitial_id);
//        mInterstitialAd.setAdListener(new AdListener() {
//            @Override
//            public void onAdLoaded() {
//                if (mInterstitialAd.isLoaded())//displays ad when loaded
//                    mInterstitialAd.show();
//            }
//        });
//    }

    /**
     * get key
     *
     * @param context -> this
     * @return unique
     */
    public static String generateKey(Context context) {
        return Secure.getString(context.getContentResolver(),
                Secure.ANDROID_ID);
    }
}
