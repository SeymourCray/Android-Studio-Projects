package com.example.overwork;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    ProgressBar progress;
    TextView res;
    int result;
    ImageView Image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        res = findViewById(R.id.result);
        Image = findViewById(R.id.smile);
        progress = findViewById(R.id.progressBar);
        Bundle arguments = getIntent().getExtras();
        result = (int) arguments.get("result");
        switch (result){
            case 4:
                res.setText(R.string.cool);
                Image.setImageResource(R.drawable.good);
                progress.setProgress(100);
                break;
            case 3:
                res.setText(R.string.normal);
                Image.setImageResource(R.drawable.okey);
                progress.setProgress(66);
                break;
            case 2:
                res.setText(R.string.bad);
                Image.setImageResource(R.drawable.bad);
                progress.setProgress(33);
                break;
            case 1:
                res.setText(R.string.worst);
                progress.setProgress(0);
                break;
        }

    }
}
