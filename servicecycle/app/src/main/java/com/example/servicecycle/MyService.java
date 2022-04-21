package com.example.servicecycle;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class MyService extends Service {

    String str = "MY_SERVICE";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(str, "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(str, "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(str, "onBind");
        return new Binder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(str, "onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.i(str, "onRebind");
    }

    @Override
    public void onDestroy() {
        Log.i(str, "onDestroy");
        super.onDestroy();
    }
}