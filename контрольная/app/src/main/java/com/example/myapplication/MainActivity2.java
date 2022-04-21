package com.example.myapplication;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    TextView statusView;
    ProgressBar indicatorBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        TextView tx = (TextView) findViewById(R.id.textView13);

        Bundle b = this.getIntent().getExtras();
        int answer = (int) b.get("answer");

         statusView =(TextView) findViewById(R.id.textView12);
         indicatorBar = findViewById(R.id.progressBar);

         indicatorBar.setProgress(100);
        if (answer==4){
            statusView.setText("Введенные значения соответствуют отсутствию переутомления.");
            indicatorBar.setProgress(100);}
        else  if (answer==3){
            statusView.setText("Введенные значения соответствуют небольшому переутомления. Рекомендуется снижение нагрузки.");
            indicatorBar.setProgress(66);}
        else  if (answer==2){
            statusView.setText("Введенные значения соответствуют высокому уровню переутомления. Рекомендуется снижение нагрузки или отпуск.");
            indicatorBar.setProgress(33);}
        else if (answer==1){
            statusView.setText("Можно говорить либо о переутомлении, либо о заболевании сердечно-сосудистой системы или других проблемах со здоровьем.");
            indicatorBar.setProgress(0);}
    }
}