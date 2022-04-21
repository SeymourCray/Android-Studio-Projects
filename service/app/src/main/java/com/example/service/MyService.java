package com.example.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;

import java.util.ArrayList;

public class MyService extends Service implements MediaPlayer.OnCompletionListener {


    MediaPlayer song=new MediaPlayer();
    int track = 1;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCompletion(MediaPlayer song) {
        track++;
        loadsong();
        function();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "Служба создана",
                Toast.LENGTH_SHORT).show();
        song=MediaPlayer.create(this,R.raw.stupid);

    }

    void loadsong()
    {

        if(track==1) {
            song=MediaPlayer.create(this,R.raw.stupid);
            song.setOnCompletionListener(this);
            song.start();
        }
        if(track==2) {
            song=MediaPlayer.create(this,R.raw.run);
            song.setOnCompletionListener(this);
            song.start();
        }

        if(track==3) {
            song=MediaPlayer.create(this,R.raw.harder);
            song.setOnCompletionListener(this);
            song.start();
        }
    }

    void function() {
        if (track < 4) song.start();
        else
            song.stop();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Служба запущена",
                Toast.LENGTH_SHORT).show();
        song.setOnCompletionListener(this);
        song.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Служба остановлена",
                Toast.LENGTH_SHORT).show();
        song.stop();
    }
}
