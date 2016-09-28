package com.blogspot.toppersdaily.technosolz.livewallpaper.Services;

import android.graphics.Canvas;
import android.graphics.Movie;
import android.os.Handler;
import android.service.wallpaper.WallpaperService;
import android.util.Log;
import android.view.SurfaceHolder;

import com.blogspot.toppersdaily.technosolz.livewallpaper.Globals.Constants;
import com.blogspot.toppersdaily.technosolz.livewallpaper.Globals.Functions;
import com.pddstudio.preferences.encrypted.EncryptedPreferences;

import java.io.IOException;

/**
 * Created by omoshiroi on 9/21/16.
 */
public class GIFWallpaperService extends WallpaperService {
    EncryptedPreferences prefs = null;

    @Override
    public WallpaperService.Engine onCreateEngine() {
        try {
            prefs = new EncryptedPreferences.Builder(this).withEncryptionPassword(Functions.generateKey(this)).build();

            Movie movie = Movie.decodeStream(
                    getResources().getAssets().open("hypno.gif"));
            return new GIFWallpaperEngine(movie);
        } catch (IOException e) {
            Log.d("GIF", "Could not load asset");
            return null;
        }
    }

    private class GIFWallpaperEngine extends WallpaperService.Engine {
        private final int frameDuration = prefs.getInt(Constants.txtSpeed, 20);

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
                float xval = prefs.getFloat(Constants.scaleX, 1f);
                float yval = prefs.getFloat(Constants.scaleY, 1f);
                int width = movie.width();
                int height = movie.height();
                switch (prefs.getInt(Constants.txtScale, 1)) {
                    case 1:
                        canvas.scale(1, 1);
                        movie.draw(canvas,
                                (xval > width ? (xval - width) : (width - xval)) / 2,
                                (yval > height ? (yval - height) : (height - yval)) / 2);
                        break;
                    case 2:
                        canvas.scale(xval / width, yval / height);
                        movie.draw(canvas, 0, 0);
                        break;
                    case 3:
                        canvas.scale(((xval / width) / 2) * 1.5f, (yval / height));
                        movie.draw(canvas, ((xval > width ? (xval / 2 - width / 2) : (width / 2 - xval / 2)) / 4), 0);
                        break;
                    case 4:
                        canvas.scale(xval / width, yval / height);
                        movie.draw(canvas, 0, 0);
                        break;
                }
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