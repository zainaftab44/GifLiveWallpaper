package com.blogspot.toppersdaily.technosolz.livewallpaper.Services;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.graphics.Point;
import android.os.Handler;
import android.service.wallpaper.WallpaperService;
import android.util.Log;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.WindowManager;

import java.io.IOException;

/**
 * Created by omoshiroi on 9/21/16.
 */
public class GIFWallpaperService extends WallpaperService {

    @Override
    public WallpaperService.Engine onCreateEngine() {
        try {
            Movie movie = Movie.decodeStream(
                    getResources().getAssets().open("hypno.gif"));
            return new GIFWallpaperEngine(movie);
        } catch (IOException e) {
            Log.d("GIF", "Could not load asset");
            return null;
        }
    }

    private class GIFWallpaperEngine extends WallpaperService.Engine {
        private final int frameDuration = 20;

        private SurfaceHolder holder;
        private Movie movie;
        private boolean visible;
        private Handler handler;

        public GIFWallpaperEngine(Movie movie) {
            this.movie = movie;
            handler = new Handler();
        }

        @Override
        public void onCreate(SurfaceHolder surfaceHolder) {
            super.onCreate(surfaceHolder);
            this.holder = surfaceHolder;
        }


        private Runnable drawGIF = new Runnable() {
            public void run() {
                draw();
            }
        };

        private void draw() {
            if (visible) {


                Canvas canvas = holder.lockCanvas();
                canvas.save();
                // Adjust size and position so that
                // the image looks good on your screen
                int width = movie.width();
                int height = movie.height();
                canvas.scale(1.5f, 1.5f);
                movie.draw(canvas, 0, 0);
                canvas.restore();
                holder.unlockCanvasAndPost(canvas);
                movie.setTime((int) (System.currentTimeMillis() % movie.duration()));

                handler.removeCallbacks(drawGIF);
                handler.postDelayed(drawGIF, frameDuration);
            }
        }

        @Override
        public void onVisibilityChanged(boolean visible) {
            this.visible = visible;
            if (visible) {
                handler.post(drawGIF);
            } else {
                handler.removeCallbacks(drawGIF);
            }
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            handler.removeCallbacks(drawGIF);
        }
    }
}