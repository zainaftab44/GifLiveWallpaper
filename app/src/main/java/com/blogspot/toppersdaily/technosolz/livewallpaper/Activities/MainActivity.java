package com.blogspot.toppersdaily.technosolz.livewallpaper.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.blogspot.toppersdaily.technosolz.livewallpaper.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationBar bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);

        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.ic_home_white_24dp, "Apply"))
                .addItem(new BottomNavigationItem(R.drawable.ic_book_white_24dp, "Settings"))
                .addItem(new BottomNavigationItem(R.drawable.ic_music_note_white_24dp, "Feedback"))
                .addItem(new BottomNavigationItem(R.drawable.ic_tv_white_24dp, "About"))
                .addItem(new BottomNavigationItem(R.drawable.ic_videogame_asset_white_24dp, "More"))
                .initialise();

        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
            }

            @Override
            public void onTabUnselected(int position) {
            }

            @Override
            public void onTabReselected(int position) {
            }
        });
    }
}
