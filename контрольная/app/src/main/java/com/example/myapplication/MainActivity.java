package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Spinner s_day, s_month, s_year,s_pol;
    EditText editText_puls, editText_puls2;
    Button button_next;
    String c_sex, c_month, c_day, c_year;
    int otvet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<String> days = new ArrayList<String>();
        for (int i = 1; i <= 31; i++) {
            days.add(Integer.toString(i));
        }

        ArrayList<String> years = new ArrayList<String>();
        int thisYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = 1900; i <= thisYear; i++) {
            years.add(Integer.toString(i));
        }

        String[] Months = new String[] { "1", "2",
                "3", "4", "5", "6", "7", "8", "9",
                "10", "11", "12" };

        String[] Pol = new String[] { "Male", "Female" };

         s_day = (Spinner) findViewById(R.id.spinner_day);
         s_month = (Spinner) findViewById(R.id.spinner_month);
         s_year = (Spinner) findViewById(R.id.spinner_year);
         s_pol = (Spinner) findViewById(R.id.spinner_pol);

         editText_puls = (EditText) findViewById(R.id.editText_puls);
         editText_puls2 = (EditText) findViewById(R.id.editText_puls2);

        button_next = (Button) findViewById(R.id.button_next);
        button_next.setOnClickListener(this);

        ArrayAdapter<String> adapter_day = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, days);
        adapter_day.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> adapter_month = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, Months);
        adapter_month.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> adapter_year = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, years);
        adapter_year.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<String> adapter_pol = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, Pol);
        adapter_pol.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        s_day.setAdapter(adapter_day);
        s_month.setAdapter(adapter_month);
        s_year.setAdapter(adapter_year);
        s_pol.setAdapter(adapter_pol);

        s_day.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                c_day = days.get(i);

            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });

        s_month.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                c_month = Months[i];

            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });

        s_year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                c_year = years.get(i);

            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });

        s_pol.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                c_sex = Pol[i];

            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });
    }


        public void onClick(View view) {

            /*Object[] SendValues = new Object[]{ editText_puls.getText(), editText_puls2.getText(), s_day.getSelectedItem().toString(),
                    s_month.getSelectedItem().toString(), s_year.getSelectedItem().toString(), s_year.getSelectedItem().toString()
            };*/
            if( editText_puls.getText().toString().equals("") || editText_puls2.getText().toString().equals("")){
                Toast toast =  Toast.makeText(MainActivity.this,
                        "УМЕЮТСЯ ПУСТЫЕ ПОЛЯ!!!", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                }
            else{
                Intent intent = new Intent(this, MainActivity2.class);
                if (c_sex.equals("Male")){
                    c_sex = "1";
                } else{
                    c_sex = "2";
                }
                Thread thread = new Thread(task);
                thread.start();
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                intent.putExtra("answer", otvet);
                startActivity(intent);
            }
        }
    Runnable task = new Runnable() {

        @Override
        public void run() {
            try {URL url = new URL("http://abashin.ru/cgi-bin/ru/tests/burnout");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");

                connection.setRequestProperty("Connection", "close");

                connection.setRequestProperty(
                        "Accept-Language",
                        "ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7");


                connection.setDoOutput(true);
                connection.setInstanceFollowRedirects(false);
                String parameters = "day="+c_day+"&month="+c_month+"&year="+c_year+"&sex="+c_sex+"&m1="+editText_puls.getText().toString()+"&m2="+editText_puls2.getText().toString();

                OutputStream os = connection.getOutputStream();
                os.write(parameters.getBytes("UTF-8"));
                os.close();
                Log.d("answer","1");
                InputStream stream = connection.getInputStream();
                BufferedReader in = new BufferedReader(new InputStreamReader(stream));
                String data = null;
                StringBuffer response = new StringBuffer();
                Log.d("answer","2");
                while ((data = in.readLine()) != null) {
                    response.append(data);
                }
                in.close();
                Log.d("answer","3");
                String parsedResponse = new String(response.toString().getBytes(), "UTF-8");
                Log.d("answer", parsedResponse);
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
                        otvet =  2;
                    } else if (value.equals("небольшому")) {
                        otvet =  3;
                    } else {
                        otvet = 4;
                    }
                } else {
                    otvet =  1;
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