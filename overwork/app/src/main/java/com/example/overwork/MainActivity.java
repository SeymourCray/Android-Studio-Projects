package com.example.overwork;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    Button input, data_input;
    EditText down, sit;
    String[] sex = {"м","ж"};
    TextView date;
    String current_sex;
    Calendar Date= Calendar.getInstance();
    Spinner spinner;
    int day, month, year, intSex, pulse1, pulse2, result;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        date = findViewById(R.id.date);
        down = findViewById(R.id.down);
        sit = findViewById(R.id.sit);
        input = findViewById(R.id.input);
        input.setOnClickListener(this);
        data_input = findViewById(R.id.input_data);
        data_input.setOnClickListener(this);
        // адаптер
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sex);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner = (Spinner) findViewById(R.id.male);
        spinner.setAdapter(adapter);
        // выделяем элемент
        spinner.setSelection(0);
        // устанавливаем обработчик нажатия
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                current_sex = sex[position];
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
        setInitialDate();

    }


    // установка обработчика выбора даты
    DatePickerDialog.OnDateSetListener d= new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            Date.set(Calendar.YEAR, year);
            Date.set(Calendar.MONTH, monthOfYear);
            Date.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setInitialDate();
        }
    };



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.input:
                if (down.getText().toString().equals("") || sit.getText().toString().equals("")) {
                    Toast.makeText(this, "Заполните все поля!", Toast.LENGTH_SHORT).show();
                }
                else {
                    year = Integer.parseInt(String.valueOf(Date.get(Calendar.YEAR)));
                    month = Integer.parseInt(String.valueOf(Date.get(Calendar.MONTH)));
                    day = Integer.parseInt(String.valueOf(Date.get(Calendar.DAY_OF_MONTH)));
                    switch(current_sex){
                        case "м":
                            intSex = 1;
                            break;
                        case "ж":
                            intSex = 2;
                    }
                    pulse1 = Integer.parseInt(String.valueOf(down.getText()));
                    pulse2 = Integer.parseInt(String.valueOf(sit.getText()));
                    Thread thread = new Thread(task);
                    thread.start();
                    try {
                        thread.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    Intent intent = new Intent(this, ResultActivity.class);
                    intent.putExtra("result", result);
                    startActivity(intent);
                }
                break;
            case R.id.input_data:
                // отображаем диалоговое окно для выбора даты
                new DatePickerDialog(MainActivity.this, d,
                        Date.get(Calendar.YEAR),
                        Date.get(Calendar.MONTH),
                        Date.get(Calendar.DAY_OF_MONTH))
                        .show();
                break;
        }
    }


    // установка начальной даты
    private void setInitialDate() {
        date.setText(DateUtils.formatDateTime(this,
                Date.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR));
    }

    Runnable task = new Runnable() {

        @Override
        public void run() {
            try {
                URL url = new URL("http://abashin.ru/cgi-bin/ru/tests/burnout");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");

                connection.setRequestProperty("Connection", "close");

                connection.setRequestProperty(
                        "Accept-Language",
                        "ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7");

                connection.setDoOutput(true);
                connection.setInstanceFollowRedirects(false);
                String parameters = "day="+day+"&month="+month+"&year="+year+"&sex="+intSex+"&m1="+pulse1+"&m2="+pulse2;
                OutputStream out = connection.getOutputStream();
                out.write(parameters.getBytes("UTF-8"));
                out.close();
                InputStream stream = connection.getInputStream();
                BufferedReader in = new BufferedReader(new InputStreamReader(stream));
                String data = null;
                StringBuffer response = new StringBuffer();
                while ((data = in.readLine()) != null) {
                    response.append(data);
                }
                in.close();


                String parsedResponse = new String(response.toString().getBytes(), "UTF-8");
                Pattern pattern = Pattern.compile("([А-Я].*\\.)");
                Matcher matcher = pattern.matcher(parsedResponse);
                if (matcher.find()) {
                    String answer = matcher.group(1);
                    pattern = Pattern.compile("([т][ ][а-я]*[ ])");
                    matcher = pattern.matcher(answer);
                    matcher.find();
                    String value = matcher.group(1);
                    value = value.substring(2, value.length() - 1);
                    if (value.equals("высокому")) {
                        result =  2;
                    } else if (value.equals("небольшому")) {
                        result =  3;
                    } else {
                        result = 4;
                    }
                } else {
                    result =  1;
                }

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };
}