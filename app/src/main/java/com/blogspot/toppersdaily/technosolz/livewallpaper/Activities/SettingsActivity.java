package com.blogspot.toppersdaily.technosolz.livewallpaper.Activities;

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
import com.pddstudio.preferences.encrypted.EncryptedPreferences;

import java.io.IOException;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class SettingsActivity extends AppCompatActivity {
    EncryptedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
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
        prefs = new EncryptedPreferences.Builder(this).withEncryptionPassword(Functions.generateKey(this)).build();

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
                SettingsActivity.this.finish();
                break;
            case R.id.btn_feedback:
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse(Constants.market_uri + Constants.app_id)));
                break;
            case R.id.btn_about:
                startActivity(new Intent(this, AboutActivity.class));
                SettingsActivity.this.finish();
                break;
            case R.id.btn_more:
                startActivity(new Intent(this, MoreActivity.class));
                SettingsActivity.this.finish();
                break;
        }
    }

}
