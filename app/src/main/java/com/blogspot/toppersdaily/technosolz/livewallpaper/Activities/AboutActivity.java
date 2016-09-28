package com.blogspot.toppersdaily.technosolz.livewallpaper.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.blogspot.toppersdaily.technosolz.livewallpaper.Globals.Constants;
import com.blogspot.toppersdaily.technosolz.livewallpaper.Globals.Variables;
import com.blogspot.toppersdaily.technosolz.livewallpaper.R;

import java.io.IOException;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Variables.setContext(this);


        GifImageView GifView;

        GifView = (GifImageView) findViewById(R.id.gifImage);
        //asset file
        try {
            Variables.image = new GifDrawable(getAssets(), "hypno.gif");
            GifView.setImageDrawable(Variables.image);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Button btn = (Button) findViewById(R.id.btn_apply);
        btn.setVisibility(View.GONE);
        Button btn1 = (Button) findViewById(R.id.btn_back);
        btn1.setVisibility(View.VISIBLE);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Variables.requestNewInterstitial();
                startActivity(new Intent(AboutActivity.this, MainActivity.class));
                Variables.image = null;
                AboutActivity.this.finish();
            }
        });
        Button btn2 = (Button) findViewById(R.id.btn_settings);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Variables.requestNewInterstitial();
                startActivity(new Intent(AboutActivity.this, SettingsActivity.class));
                Variables.image = null;
                AboutActivity.this.finish();
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
        btn4.setPressed(true);

        Button btn5 = (Button) findViewById(R.id.btn_more);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Variables.requestNewInterstitial();
                startActivity(new Intent(AboutActivity.this, MoreActivity.class));
                Variables.image = null;
                AboutActivity.this.finish();
            }
        });
    }


/*
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                startActivity(new Intent(this, MainActivity.class));
                AboutActivity.this.finish();
                break;
            case R.id.btn_settings:
                startActivity(new Intent(this, SettingsActivity.class));
                AboutActivity.this.finish();
                break;
            case R.id.btn_feedback:
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse(Constants.market_uri + Constants.app_id)));
                break;
            case R.id.btn_more:
                startActivity(new Intent(this, MoreActivity.class));
                AboutActivity.this.finish();
                break;

        }
    }
*/

}
