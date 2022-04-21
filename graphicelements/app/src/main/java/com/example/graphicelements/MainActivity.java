package com.example.graphicelements;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button home;
    Button sport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        home = findViewById(R.id.button2);
        sport = findViewById(R.id.button);
        sport.setOnClickListener(this);
        home.setOnClickListener(this);
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button:
                Intent intent = new Intent(this, SportActivity.class);
                startActivity(intent);
                break;
            case R.id.button2:
                Intent intent2 = new Intent(this, HomeActivity.class);
                startActivity(intent2);
                break;
        }
    };
}