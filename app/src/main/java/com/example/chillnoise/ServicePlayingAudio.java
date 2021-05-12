package com.example.chillnoise;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.security.Provider;

public class ServicePlayingAudio extends Service {
    private MediaPlayer mMediaPlayer;
    int value;
    boolean play=false;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mMediaPlayer = MediaPlayer.create(this, R.raw.rain);
        mMediaPlayer.start();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        value = intent.getIntExtra("key",0);
            System.out.println(value);
            switch (value) {
                case 0:
                    mMediaPlayer.stop();
                    mMediaPlayer = MediaPlayer.create(this, R.raw.fire);
                    break;
                case 1:
                    mMediaPlayer.stop();
                    mMediaPlayer = MediaPlayer.create(this, R.raw.rain);
                    break;
                case 2:
                    mMediaPlayer.stop();
                    mMediaPlayer = MediaPlayer.create(this, R.raw.coffee);
                    break;
                case 3:
                    mMediaPlayer.stop();
                    mMediaPlayer = MediaPlayer.create(this, R.raw.thunder);
                    break;
                case 4:
                    mMediaPlayer.stop();
                    mMediaPlayer = MediaPlayer.create(this, R.raw.tree);
                    break;
                case 5:
                    mMediaPlayer.stop();
                    mMediaPlayer = MediaPlayer.create(this, R.raw.waves);
                    break;
            }
        mMediaPlayer.setLooping(true);
        mMediaPlayer.start();



        return START_STICKY;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mMediaPlayer.stop();
        mMediaPlayer.release();
    }
}
