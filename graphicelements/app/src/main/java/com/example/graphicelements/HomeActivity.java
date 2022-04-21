package com.example.graphicelements;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemLongClickListener {

    Button add;
    EditText info;
    ListView mainListView;
    ArrayAdapter mArrayAdapter;
    ArrayList mNameList = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        add = findViewById(R.id.add1);
        info = (EditText) findViewById(R.id.info1);
        add.setOnClickListener(this);
        mainListView = findViewById(R.id.mylist1);
        mArrayAdapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,
                mNameList);
        mainListView.setAdapter(mArrayAdapter);
        mainListView.setOnItemLongClickListener(this);

    }
    @Override
    public void onClick(View v) {
        mNameList.add(info.getText().toString().trim());
        info.setText("");
        Collections.sort(mNameList);  //сортирую
        Set<String> set = new HashSet<>(mNameList); //убираю повторы
        mNameList.clear();
        mNameList.addAll(set);
        mArrayAdapter.notifyDataSetChanged();

    }


    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        String value = (String) mNameList.get(position);
        mNameList.remove(position); //удаляю значение по индексу
        Toast.makeText(getApplicationContext(), "Удалено: " + value, Toast.LENGTH_LONG).show(); //сообщение об удалении
        mArrayAdapter.notifyDataSetChanged();
        return true;

    };
}