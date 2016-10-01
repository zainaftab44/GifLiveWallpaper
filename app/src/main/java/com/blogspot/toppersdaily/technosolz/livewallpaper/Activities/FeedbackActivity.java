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

public class FeedbackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        Variables.setContext(this);
        prefs = new EncryptedPreferences.Builder(this).withEncryptionPassword(Functions.generateKey(this)).build();


        GifImageView GifView;

        GifView = (GifImageView) findViewById(R.id.gifImage);
        //asset file
        try {
            Variables.image = new GifDrawable(getAssets(), prefs.getString(Constants.imgName, Constants.gifs[0]));
            GifView.setImageDrawable(Variables.image);
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
         * Bottom menu buttons registered
         */
        Button btn = (Button) findViewById(R.id.btn_apply);
        btn.setVisibility(View.GONE);
        Button btn1 = (Button) findViewById(R.id.btn_back);
        btn1.setVisibility(View.VISIBLE);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                go_back();
            }
        });
        Button btn2 = (Button) findViewById(R.id.btn_settings);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Variables.requestNewInterstitial();
                startActivity(new Intent(FeedbackActivity.this, SettingsActivity.class));
                Variables.image = null;
                FeedbackActivity.this.finish();
            }
        });

        Button btn3 = (Button) findViewById(R.id.btn_feedback);
        btn3.setPressed(true);

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
                startActivity(new Intent(FeedbackActivity.this, MoreActivity.class));
                Variables.image = null;
                FeedbackActivity.this.finish();
            }
        });
    }

    public void go_back() {
        Variables.requestNewInterstitial();
        startActivity(new Intent(FeedbackActivity.this, MainActivity.class));
        Variables.image = null;
        FeedbackActivity.this.finish();
    }

    public void email(View v) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri data = Uri.parse("mailto:" + Constants.developer_email + "?subject=" + Constants.email_subject + "&body=");
        intent.setData(data);
        startActivity(intent);
    }

    public void open_facebook(View v) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.fb_page_address)));
        startActivity(browserIntent);
    }

    public void open_website(View v) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(getResources().getString(R.string.website_link)));
        startActivity(browserIntent);
    }
}
