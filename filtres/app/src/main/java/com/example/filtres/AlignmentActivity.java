package com.example.filtres;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AlignmentActivity extends AppCompatActivity implements View.OnClickListener {

    Button l, c, r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alignment);
        l = findViewById(R.id.left);
        c = findViewById(R.id.center);
        r = findViewById(R.id.right);
        r.setOnClickListener(this);
        l.setOnClickListener(this);
        c.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.left:
                Intent intent = new Intent();
                intent.putExtra("align", "left");
                setResult(RESULT_OK, intent);
                finish();
            case R.id.center:
                Intent intent_2 = new Intent();
                intent_2.putExtra("align", "center");
                setResult(RESULT_OK, intent_2);
                finish();
            case R.id.right:
                Intent intent_3 = new Intent();
                intent_3.putExtra("align", "right");
                setResult(RESULT_OK, intent_3);
                finish();
        }
    }
}