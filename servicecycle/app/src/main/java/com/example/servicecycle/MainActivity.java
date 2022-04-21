package com.example.servicecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener {

Button start, bind;
ServiceConnection scon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start = findViewById(R.id.button);
        bind = findViewById(R.id.button2);
        start.setOnClickListener(this);
        bind.setOnClickListener(this);

        scon = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {

            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {

            }
        };
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.button:
                startService( new Intent(this, MyService.class));
                break;
            case R.id.button2:
                Log.i("destroy","i");
                bindService(new Intent(this, MyService.class), scon, BIND_AUTO_CREATE);
                unbindService(scon);
        }
    }
}