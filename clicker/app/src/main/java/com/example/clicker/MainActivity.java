package com.example.clicker;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    long count = 0;
    ImageButton button1;
    TextView textView;
    ImageButton button2;
    Button button3;
    String text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = (ImageButton) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.textView);
        button2 = (ImageButton) findViewById(R.id.button_2);
        button3 = (Button) findViewById(R.id.button_3);
        OnClick();

    }
    void translate(){
        if (count%10==2 || count%10==3 || count%10==4){
            if (count%100 < 10 || count%100>20) {
                text = " раза";
            }
        }
        else{
            text = " раз";
        }
    }
    @SuppressLint("SetTextI18n")
    void OnClick(){
        button1.setOnClickListener(view -> {
            count ++;
            translate();
            textView.setText(count + text);
        });
        button2.setOnClickListener(view -> {
            if (count > 0){
                count --;
                translate();
                textView.setText(count + text);
            }
        });
        button3.setOnClickListener(view -> {
            count = 0;
            translate();
            textView.setText(count + text);
        });
    }

}
