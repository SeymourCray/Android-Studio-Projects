package com.example.playservice;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class MyService extends Service implements MediaPlayer.OnCompletionListener {
    public MyService() {
    }

    MediaPlayer pl=new MediaPlayer();

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    @Override
    public void onCompletion(MediaPlayer song) {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        pl=MediaPlayer.create(this,R.raw.harder);
        pl.setOnCompletionListener(this);
        pl.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        pl.stop();
    }


}