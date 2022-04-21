package com.example.savedata;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;



@RequiresApi(api = Build.VERSION_CODES.N)
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button make_photo;
    ImageView photo;
    EditText name, date;
    Spinner old, group;
    String[] mass = {"17","18","19","20","21","22","23","24","25","26","27","28","29","30"};
    String[] gr = {"PI20-1","PI20-2","PI20-3","PI20-4","PI20-5","PI20-6"};
    int current_old, current_group;
    Bitmap imageBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        date = findViewById(R.id.date);
        make_photo = findViewById(R.id.make_photo);
        photo = findViewById(R.id.photo);
        name = findViewById(R.id.name);
        make_photo.setOnClickListener(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mass);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        old = (Spinner) findViewById(R.id.old);
        old.setAdapter(adapter);
        // выделяем элемент
        old.setSelection(4);
        // устанавливаем обработчик нажатия
        old.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                current_old = position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, gr);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        group = (Spinner) findViewById(R.id.group);
        group.setAdapter(adapter1);
        // выделяем элемент
        group.setSelection(3);
        // устанавливаем обработчик нажатия
        group.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                current_group = position;
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
        SharedPreferences pref = getPreferences(MODE_PRIVATE);
        String a = getValue("name", pref);
        String b = getValue("old", pref);
        String c = getValue("group", pref);
        String d = getValue("date", pref);
        String e = getValue("path", pref);
        Log.d("Values", b+c);
        if (!a.equals("")){
            name.setText(a);
        }
        if (!b.equals("")){
            old.setSelection(Integer.parseInt(b));
        }
        if (!c.equals("")){
            group.setSelection(Integer.parseInt(c));
        }
        if (!d.equals("")){
            date.setText(d);
        }
        loadImageFromStorage(e);
    }





    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.make_photo:
                //интент для фотографии
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 1);
        }
    }



    //получение результата из интента
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Bundle extras = data.getExtras();
        imageBitmap = (Bitmap) extras.get("data");
        photo.setImageBitmap(imageBitmap);
        super.onActivityResult(requestCode, resultCode, data);
    }




    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();
        editor.putString( "name", String.valueOf(name.getText()));
        editor.putString( "old", String.valueOf(current_old));
        editor.putString( "group", String.valueOf(current_group));
        editor.putString( "date", String.valueOf(date.getText()));
        String path = saveToInternalStorage(imageBitmap);
        editor.putString( "path", path);
        editor.commit();
    }





    public String getValue(String i, SharedPreferences pref){
        try {
            return pref.getString(i, "");

        }catch (Exception e){
            return "";
        }
    }




    private String saveToInternalStorage(Bitmap bitmapImage){
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath = new File(directory,"profile.jpg");

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return directory.getAbsolutePath();
    }




    private void loadImageFromStorage(String path) {
        try {
            File f=new File(path, "profile.jpg");
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
            photo.setImageBitmap(b);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }



}