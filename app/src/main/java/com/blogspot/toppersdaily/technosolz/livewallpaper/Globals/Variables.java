package com.blogspot.toppersdaily.technosolz.livewallpaper.Globals;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.blogspot.toppersdaily.technosolz.livewallpaper.Billing.IabHelper;
import com.blogspot.toppersdaily.technosolz.livewallpaper.Billing.IabResult;
import com.blogspot.toppersdaily.technosolz.livewallpaper.Billing.Inventory;
import com.blogspot.toppersdaily.technosolz.livewallpaper.Billing.Purchase;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.pddstudio.preferences.encrypted.EncryptedPreferences;

import pl.droidsonroids.gif.GifDrawable;

/**
 * Created by omoshiroi on 9/22/16.
 * All the global variables values are to be stored here for efficient memory usage
 *
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


    public static IabHelper mHelper;

    public static void setupIAB() {
        mHelper = new IabHelper(context, Constants.inapp_base64_public_key);
        mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {
            @Override
            public void onIabSetupFinished(IabResult result) {
                if (!result.isSuccess()) {
                    Log.i(Constants.TAG, "In-app Billing Setup Failed" + result);
                } else {
                    Log.i(Constants.TAG, "In-app Billing Setup Successful");
                }
            }
        });
    }

    public static void purchase(Activity activity) {
        mHelper.launchPurchaseFlow(activity, Constants.ITEM_SKU, 10001, mPurchasedFinisedListener, "mypurchasetoken");
    }

    public static IabHelper.OnIabPurchaseFinishedListener mPurchasedFinisedListener = new IabHelper.OnIabPurchaseFinishedListener() {
        @Override
        public void onIabPurchaseFinished(IabResult result, Purchase info) {
            if (result.isFailure()) {
                return;
            } else if (info.getSku().equals(Constants.ITEM_SKU)) {
                mHelper.queryInventoryAsync(new IabHelper.QueryInventoryFinishedListener() {
                    @Override
                    public void onQueryInventoryFinished(IabResult result, Inventory inv) {
                        if (result.isFailure()) {
//                            Log.i(Constants.TAG, "Purchase failed" + result);
                        } else {
                            mHelper.consumeAsync(inv.getPurchase(Constants.ITEM_SKU), new IabHelper.OnConsumeFinishedListener() {
                                @Override
                                public void onConsumeFinished(Purchase purchase, IabResult result) {
                                    EncryptedPreferences prefs;
                                    prefs = new EncryptedPreferences.Builder(getContext()).withEncryptionPassword(Functions.generateKey(getContext())).build();
                                    prefs.edit().putBoolean(Constants.isPurchased, true).commit();
                                }
                            });
                        }
                    }
                });
            }
        }
    };
}
