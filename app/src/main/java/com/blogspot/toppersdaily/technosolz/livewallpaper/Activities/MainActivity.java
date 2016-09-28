package com.blogspot.toppersdaily.technosolz.livewallpaper.Activities;

import android.app.WallpaperManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.blogspot.toppersdaily.technosolz.livewallpaper.Globals.Constants;
import com.blogspot.toppersdaily.technosolz.livewallpaper.Globals.Functions;
import com.blogspot.toppersdaily.technosolz.livewallpaper.Globals.Variables;
import com.blogspot.toppersdaily.technosolz.livewallpaper.R;
import com.blogspot.toppersdaily.technosolz.livewallpaper.Services.GIFWallpaperService;
import com.pddstudio.preferences.encrypted.EncryptedPreferences;

import java.io.IOException;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity {
    EncryptedPreferences prefs;

    //    private Swipe swipe;
    private GifImageView GifView;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Variables.setContext(this);
        context = this;

        GifView = (GifImageView) findViewById(R.id.gifImage);
        //asset file
        try {
            Variables.image = new GifDrawable(getAssets(), "hypno.gif");
            GifView.setImageDrawable(Variables.image);
        } catch (IOException e) {
            e.printStackTrace();
        }
        prefs = new EncryptedPreferences.Builder(this).withEncryptionPassword(Functions.generateKey(this)).build();

        if (prefs.getBoolean(Constants.txtFirst, true)) {
            prefs.edit()
                    .putBoolean(Constants.txtFirst, false)
                    .putInt(Constants.txtScale, 0)
                    .putInt(Constants.txtSpeed, 20)
                    .commit();

        }
/*
//for multiple wallpapers
        swipe = new Swipe();
        swipe.addListener(new SwipeListener() {
            @Override
            public void onSwipingLeft(final MotionEvent event) {
            }

            @Override
            public void onSwipedLeft(final MotionEvent event) {
                //TODO: add swipe left code
            }

            @Override
            public void onSwipingRight(final MotionEvent event) {
            }

            @Override
            public void onSwipedRight(final MotionEvent event) {
                //TODO: add swipe right code
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
  */
        Button btn1 = (Button) findViewById(R.id.btn_apply);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                startActivity(new Intent(context, SettingsActivity.class));
//                Variables.image.recycle();
                Variables.image = null;
                MainActivity.this.finish();
            }
        });
        Button btn3 = (Button) findViewById(R.id.btn_feedback);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse(Constants.market_uri + Constants.app_id)));
            }
        });
        Button btn4 = (Button) findViewById(R.id.btn_about);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, AboutActivity.class));
//                Variables.image.recycle();
                Variables.image = null;
                MainActivity.this.finish();
            }
        });
        Button btn5 = (Button) findViewById(R.id.btn_more);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context, MoreActivity.class));
//                Variables.image.recycle();
                Variables.image = null;
                MainActivity.this.finish();
            }
        });
    }

    /*public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_apply:
                Intent intent = new Intent(
                        WallpaperManager.ACTION_CHANGE_LIVE_WALLPAPER);
                intent.putExtra(
                        WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT,
                        new ComponentName(this, GIFWallpaperService.class));
                MainActivity.this.startActivity(intent);
                break;
            case R.id.btn_settings:
                startActivity(new Intent(this, SettingsActivity.class));
                MainActivity.this.finish();
                break;
            case R.id.btn_feedback:
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse(Constants.market_uri + Constants.app_id)));
                break;
            case R.id.btn_about:
                startActivity(new Intent(this, AboutActivity.class));
                MainActivity.this.finish();
                break;
            case R.id.btn_more:
                startActivity(new Intent(this, MoreActivity.class));
                MainActivity.this.finish();
                break;

        }s
   //for multiple wallpapers
@Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        swipe.dispatchTouchEvent(event);
        return super.dispatchTouchEvent(event);
    }*/
}
