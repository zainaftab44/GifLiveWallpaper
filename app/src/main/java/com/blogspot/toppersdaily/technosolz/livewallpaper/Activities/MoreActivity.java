package com.blogspot.toppersdaily.technosolz.livewallpaper.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.blogspot.toppersdaily.technosolz.livewallpaper.Globals.Constants;
import com.blogspot.toppersdaily.technosolz.livewallpaper.Globals.Variables;
import com.blogspot.toppersdaily.technosolz.livewallpaper.R;

import java.io.IOException;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

//import in.arjsna.swipecardlib.SwipeCardView;

public class MoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        Variables.setContext(this);
        GifImageView GifView;
        GifView = (GifImageView) findViewById(R.id.gifImage);
        //asset file
        try {
            GifDrawable gifFromAssets = new GifDrawable(getAssets(), "hypno.gif");
            GifView.setImageDrawable(gifFromAssets);
        } catch (IOException e) {
            e.printStackTrace();
        }
        View.OnClickListener onClickListener_app1 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("market://details?id=" + Constants.more_apps_1)));
            }

        };
        View.OnClickListener onClickListener_app2 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("market://details?id=" + Constants.more_apps_2)));
            }

        };
        View.OnClickListener onClickListener_app3 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("market://details?id=" + Constants.more_apps_3)));
            }

        };
        View.OnClickListener onClickListener_app4 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("market://details?id=" + Constants.more_apps_4)));
            }

        };
        View.OnClickListener onClickListener_app5 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("market://details?id=" + Constants.more_apps_5)));
            }

        };
        View.OnClickListener onClickListener_app6 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("market://details?id=" + Constants.more_apps_6)));
            }

        };


        ((ImageButton) findViewById(R.id.btn_more_1)).setOnClickListener(onClickListener_app1);
        ((ImageButton) findViewById(R.id.btn_more_2)).setOnClickListener(onClickListener_app2);
        ((ImageButton) findViewById(R.id.btn_more_3)).setOnClickListener(onClickListener_app3);
        ((ImageButton) findViewById(R.id.btn_more_4)).setOnClickListener(onClickListener_app4);
        ((ImageButton) findViewById(R.id.btn_more_5)).setOnClickListener(onClickListener_app5);
        ((ImageButton) findViewById(R.id.btn_more_6)).setOnClickListener(onClickListener_app6);


        View.OnClickListener clicked = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick(v);
            }
        };
        Button btn = (Button) findViewById(R.id.btn_apply);
        btn.setVisibility(View.GONE);
        Button btn1 = (Button) findViewById(R.id.btn_back);
        btn1.setVisibility(View.VISIBLE);
        btn1.setOnClickListener(clicked);
        Button btn2 = (Button) findViewById(R.id.btn_settings);
        btn2.setOnClickListener(clicked);
        Button btn3 = (Button) findViewById(R.id.btn_feedback);
        btn3.setOnClickListener(clicked);
        Button btn4 = (Button) findViewById(R.id.btn_about);
        btn4.setOnClickListener(clicked);
        Button btn5 = (Button) findViewById(R.id.btn_more);
        btn5.setOnClickListener(clicked);

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                startActivity(new Intent(this, MainActivity.class));
                MoreActivity.this.finish();
                break;
            case R.id.btn_settings:
                startActivity(new Intent(this, SettingsActivity.class));
                MoreActivity.this.finish();
                break;
            case R.id.btn_feedback:
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse(Constants.market_uri + Constants.app_id)));
                break;
            case R.id.btn_about:
                startActivity(new Intent(this, AboutActivity.class));
                MoreActivity.this.finish();
                break;
        }
    }
}
