package com.example.service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button b1, send_b;
    public static final String WHERE_MY_CAT_ACTION = "ru.alexanderklimov.action.CAT";
    public static final String ALARM_MESSAGE = "Срочно пришлите кота!";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = findViewById(R.id.b_toast);
        b1.setOnClickListener(this);
        send_b = findViewById(R.id.button);
        final Button btnStart = findViewById(R.id.button_start);
        final Button btnStop = findViewById(R.id.button_stop);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // используем явный вызов службы
                startService(
                        new Intent(MainActivity.this, MyService.class));
            }
        });

        // остановка службы
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(
                        new Intent(MainActivity.this, MyService.class));
            }
        });
    }

    @Override
    public void onClick(View view) {
        LayoutInflater inflater = getLayoutInflater();
        // inflate layout file in Layout Inflater
        View v = inflater.inflate(R.layout.toast_image_layout,
                (ViewGroup) findViewById(R.id.relativeLayout1));
        Toast toast = new Toast(getApplicationContext());

        // add view of toast to
        // toast_image_layout file
        toast.setView(v);

        // show toast
        toast.show();
    }

    public void sendMessage(View view) {
        Intent intent = new Intent();
        ComponentName componentName = new ComponentName(this, MyReceiver.class);
        intent.putExtra("ru.alexanderklimov.broadcast.Message", ALARM_MESSAGE);
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        intent.setComponent(componentName);
        sendBroadcast(intent);
    }

}