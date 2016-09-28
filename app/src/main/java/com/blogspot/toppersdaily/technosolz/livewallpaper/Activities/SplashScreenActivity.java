package com.blogspot.toppersdaily.technosolz.livewallpaper.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.blogspot.toppersdaily.technosolz.livewallpaper.R;

import java.util.Random;

import io.netopen.hotbitmapgg.library.view.RingProgressBar;

public class SplashScreenActivity extends AppCompatActivity {
    Context context = this;
    Handler handler = new Handler();
    //    AnimateHorizontalProgressBar progressBar;
    RingProgressBar progressBar;
    int progress = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);


        progressBar = (RingProgressBar) findViewById(R.id.animate_progress_bar);
        // (AnimateHorizontalProgressBar) findViewById(R.id.animate_progress_bar);
        progressBar.setProgress(0);

        new Thread(new Runnable() {
            public void run() {
                while (progress < 100) {
                    progress += new Random().nextInt(2) + 1;
                    if (progress > 100)
                        progress = 100;
                    handler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(progress);
                        }
                    });
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (progress >= 100) {
                    Intent mainIntent = new Intent(SplashScreenActivity.this, MainActivity.class);
                    SplashScreenActivity.this.startActivity(mainIntent);
                    SplashScreenActivity.this.finish();
                }
            }
        }).start();

    }
}
