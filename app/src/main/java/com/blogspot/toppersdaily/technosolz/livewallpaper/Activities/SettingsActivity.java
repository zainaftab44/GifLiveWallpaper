package com.blogspot.toppersdaily.technosolz.livewallpaper.Activities;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

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
    SeekBar seekBar;
    TextView seekBarValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
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

        prefs = new EncryptedPreferences.Builder(this).withEncryptionPassword(Functions.generateKey(this)).build();


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
        btn2.setPressed(true);


        Button btn3 = (Button) findViewById(R.id.btn_feedback);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettingsActivity.this, FeedbackActivity.class));
                Variables.image = null;
                SettingsActivity.this.finish();
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
                startActivity(new Intent(SettingsActivity.this, MoreActivity.class));
                Variables.image = null;
                SettingsActivity.this.finish();
            }
        });

    }

    public void go_back() {
        Variables.requestNewInterstitial();
        startActivity(new Intent(SettingsActivity.this, MainActivity.class));
        Variables.image = null;
        SettingsActivity.this.finish();
    }

    public void changeScale(View v) {
        // do something
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.size_dialog);
        dialog.setTitle(getResources().getString(R.string.scale));
        ((Button) dialog.findViewById(R.id.dialog_done)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        RadioGroup radioGroup = (RadioGroup) dialog.findViewById(R.id.radioGroup);
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            ((RadioButton) radioGroup.getChildAt(i)).setText(getResources().getStringArray(R.array.scales)[i]);
        }
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Shared preferences for updating the settings

                // DO NOT CHANGE: Below code is for finding the screen size for scaling the wallpaper
                WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
                Display display = wm.getDefaultDisplay();
                Point size = new Point();
                display.getSize(size);

                switch (checkedId) {
                    case R.id.radio1:
                        prefs.edit()
                                .putInt(Constants.txtScale, 1)
                                .putFloat(Constants.scaleX, size.x)
                                .putFloat(Constants.scaleY, size.y).apply();
                        break;
                    case R.id.radio2:
                        prefs.edit()
                                .putInt(Constants.txtScale, 2)
                                .putFloat(Constants.scaleX, size.x)
                                .putFloat(Constants.scaleY, size.y).apply();
                        break;
                    case R.id.radio3:
                        prefs.edit()
                                .putInt(Constants.txtScale, 3)
                                .putFloat(Constants.scaleX, size.x)
                                .putFloat(Constants.scaleY, size.y).apply();
                        break;
                    case R.id.radio4:
                        prefs.edit()
                                .putInt(Constants.txtScale, 4)
                                .putFloat(Constants.scaleX, size.x)
                                .putFloat(Constants.scaleY, size.y).apply();
                        break;
                }
            }
        });

        dialog.show();
    }

    public void changeSpeed(View v) {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.speed_dialog);
        dialog.setTitle(getResources().getString(R.string.speed));
        seekBar = (SeekBar) dialog.findViewById(R.id.seekBar);
        seekBar.setProgress(prefs.getInt(Constants.txtSpeed, 20));
        seekBar.setMax(100);
        seekBarValue = (TextView) dialog.findViewById(R.id.seekBarValue);
        seekBarValue.setText(String.valueOf(prefs.getInt(Constants.txtSpeed, 20)));
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBarValue.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        Button dialogButton = (Button) dialog.findViewById(R.id.customSaveBtn);
        dialogButton.setText(getResources().getString(R.string.apply));
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prefs.edit().putInt(Constants.txtSpeed, seekBar.getProgress()).apply();
                dialog.dismiss();
            }
        });
        dialog.show();

    }

}
