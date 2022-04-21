package com.example.filtres;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ColorActivity extends AppCompatActivity  implements View.OnClickListener {

    Button r,g,b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);
        r = findViewById(R.id.red);
        g = findViewById(R.id.green);
        b = findViewById(R.id.blue);
        r.setOnClickListener(this);
        g.setOnClickListener(this);
        b.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.red:
                Intent intent = new Intent();
                intent.putExtra("color", "#ff0000");
                setResult(RESULT_OK, intent);
                finish();
            case R.id.green:
                Intent intent_2 = new Intent();
                intent_2.putExtra("color", "#00ff00");
                setResult(RESULT_OK, intent_2);
                finish();
            case R.id.blue:
                Intent intent_3 = new Intent();
                intent_3.putExtra("color", "#0000ff");
                setResult(RESULT_OK, intent_3);
                finish();
        }
    }
}