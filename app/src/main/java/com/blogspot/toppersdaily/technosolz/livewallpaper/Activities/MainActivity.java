package com.blogspot.toppersdaily.technosolz.livewallpaper.Activities;

import android.app.WallpaperManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.blogspot.toppersdaily.technosolz.livewallpaper.Globals.Constants;
import com.blogspot.toppersdaily.technosolz.livewallpaper.Globals.Functions;
import com.blogspot.toppersdaily.technosolz.livewallpaper.Globals.Variables;
import com.blogspot.toppersdaily.technosolz.livewallpaper.R;
import com.blogspot.toppersdaily.technosolz.livewallpaper.Services.GIFWallpaperService;
import com.github.pwittchen.swipe.library.Swipe;
import com.github.pwittchen.swipe.library.SwipeListener;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.InterstitialAd;
import com.pddstudio.preferences.encrypted.EncryptedPreferences;

import java.io.IOException;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity {
    EncryptedPreferences prefs;

    private Swipe swipe;
    private GifImageView GifView;
    private Context context;

    private boolean locked = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Important Initializations
        Variables.setContext(this);
        prefs = new EncryptedPreferences.Builder(this).withEncryptionPassword(Functions.generateKey(this)).build();
        context = this;
        GifView = (GifImageView) findViewById(R.id.gifImage);


        // Custom action bar for center title
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.title_main_activity);


        //asset file
        try {
            Variables.image = new GifDrawable(getAssets(), prefs.getString(Constants.imgName, Constants.gifs[0]));
            GifView.setImageDrawable(Variables.image);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Admob interstitial initialization code
        Variables.mInterstitialAd = new InterstitialAd(getApplicationContext());
        Variables.mInterstitialAd.setAdUnitId(Constants.ad_interstitial_id);
        Variables.mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Call displayInterstitial() function
                if (Variables.mInterstitialAd.isLoaded())//displays ad when loaded
                    runOnUiThread(new Runnable() {
                        public void run() {
                            Variables.mInterstitialAd.show();
                        }
                    });
            }

            @Override
            public void onAdClosed() {
                super.onAdClosed();
            }
        });

        /**
         * First time preferences setup
         */
        if (prefs.getBoolean(Constants.txtFirst, true)) {
            WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            prefs.edit()
                    .putBoolean(Constants.txtFirst, false)
                    .putInt(Constants.txtScale, 1)
                    .putInt(Constants.txtSpeed, 20)
                    .putFloat(Constants.scaleX, size.x)
                    .putFloat(Constants.scaleY, size.y)
                    .putInt(Constants.imgNo, 0)
                    .putString(Constants.imgName, Constants.gifs[0])
                    .commit();
        }

        /**
         * Bottom menu initializations
         */
        Button btn1 = (Button) findViewById(R.id.btn_apply);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Variables.requestNewInterstitial();
                Intent intent = new Intent(
                        WallpaperManager.ACTION_CHANGE_LIVE_WALLPAPER);
                intent.putExtra(
                        WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT,
                        new ComponentName(context, GIFWallpaperService.class));
                MainActivity.this.startActivity(intent);
            }
        });

        Button btn2 = (Button) findViewById(R.id.btn_settings);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Variables.requestNewInterstitial();
                startActivity(new Intent(context, SettingsActivity.class));
                Variables.image = null;
                MainActivity.this.finish();
            }
        });

        Button btn3 = (Button) findViewById(R.id.btn_feedback);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, FeedbackActivity.class));
                Variables.image = null;
                MainActivity.this.finish();
            }
        });

        Button btn4 = (Button) findViewById(R.id.btn_review);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse(Constants.market_uri + Constants.app_id)));
            }
        });

        Button btn5 = (Button) findViewById(R.id.btn_more);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Variables.requestNewInterstitial();
                startActivity(new Intent(context, MoreActivity.class));
                Variables.image = null;
                MainActivity.this.finish();
            }
        });


        swipe = new Swipe();
        swipe.addListener(new SwipeListener() {
            @Override
            public void onSwipingLeft(final MotionEvent event) {
            }

            @Override
            public void onSwipedLeft(final MotionEvent event) {
            }

            @Override
            public void onSwipingRight(final MotionEvent event) {
            }

            @Override
            public void onSwipedRight(final MotionEvent event) {
            }

            @Override
            public void onSwipingUp(final MotionEvent event) {
            }

            @Override
            public void onSwipedUp(final MotionEvent event) {
            }

            @Override
            public void onSwipingDown(final MotionEvent event) {
            }

            @Override
            public void onSwipedDown(final MotionEvent event) {
            }
        });


    }

    public void load_prev(View view) {
        swipedleft();
    }

    public void load_next(View view) {
        swipedRight();
    }

    public void swipedleft() {
        int num = prefs.getInt(Constants.imgNo, 0);
        if (num < Constants.gifs.length - 1) {
            num += 1;
            if (locked) {
                if (num % 2 == 1) {
                    ((Button) findViewById(R.id.button_upgrade)).setVisibility(View.VISIBLE);
                } else {
                    ((Button) findViewById(R.id.button_upgrade)).setVisibility(View.GONE);
                }
            } else
                ((Button) findViewById(R.id.button_upgrade)).setVisibility(View.GONE);
            prefs.edit()
                    .putInt(Constants.imgNo, num)
                    .putString(Constants.imgName, Constants.gifs[num])
                    .commit();
        } else if (num >= Constants.gifs.length - 1) {
            num = 0;
            ((Button) findViewById(R.id.button_upgrade)).setVisibility(View.GONE);
            prefs.edit()
                    .putInt(Constants.imgNo, 0)
                    .putString(Constants.imgName, Constants.gifs[num])
                    .commit();
        }
        //Load file
        try {
            Variables.image = new GifDrawable(getAssets(), prefs.getString(Constants.imgName, Constants.gifs[num]));
            GifView.setImageDrawable(Variables.image);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void swipedRight() {
        int num = prefs.getInt(Constants.imgNo, 0);
        if (num > 0) {
            num -= 1;
            if (locked) {
                if (num % 2 == 1) {
                    ((Button) findViewById(R.id.button_upgrade)).setVisibility(View.VISIBLE);
                } else {
                    ((Button) findViewById(R.id.button_upgrade)).setVisibility(View.GONE);
                }
            } else
                ((Button) findViewById(R.id.button_upgrade)).setVisibility(View.GONE);
            prefs.edit()
                    .putInt(Constants.imgNo, num)
                    .putString(Constants.imgName, Constants.gifs[num])
                    .commit();
        } else if (num <= 0) {
            num = Constants.gifs.length - 1;
            if (locked) {
                if (num % 2 == 1) {
                    ((Button) findViewById(R.id.button_upgrade)).setVisibility(View.VISIBLE);
                } else {
                    ((Button) findViewById(R.id.button_upgrade)).setVisibility(View.GONE);
                }
            } else
                ((Button) findViewById(R.id.button_upgrade)).setVisibility(View.GONE);
            prefs.edit()
                    .putInt(Constants.imgNo, num)
                    .putString(Constants.imgName, Constants.gifs[num])
                    .commit();
        }
        //Load file
        try {
            Variables.image = new GifDrawable(getAssets(), prefs.getString(Constants.imgName, Constants.gifs[num]));
            GifView.setImageDrawable(Variables.image);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        swipe.dispatchTouchEvent(event);
        return super.dispatchTouchEvent(event);
    }

}
