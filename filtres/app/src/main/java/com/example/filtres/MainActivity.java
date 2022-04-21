package com.example.filtres;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn, btn_2, btn_color, btn_align;
    EditText adr;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.btn);
        btn_2 = findViewById(R.id.btn_2);
        btn_align = findViewById(R.id.btn_4);
        btn_color = findViewById(R.id.btn_3);
        text = findViewById(R.id.text);
        adr = findViewById(R.id.adr);
        btn.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_color.setOnClickListener(this);
        btn_align.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,1,0,"Сделать фото");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(this, PhotoActivity.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        if (requestCode== 1) {
            String color = data.getStringExtra("color");
            text.setTextColor(Color.parseColor(color));
        }
        else{
            String align = data.getStringExtra("align");
            switch (align) {
                case "left":
                    text.setGravity(Gravity.LEFT);
                    break;
                case "center":
                    text.setGravity(Gravity.CENTER);
                    break;
                case "right":
                    text.setGravity(Gravity.RIGHT);
                    break;
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn:
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(adr.getText().toString()));
                startActivity(intent);
                break;
            case R.id.btn_2:
                Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                Log.d("Error", "no");
                startActivity(enableIntent);
                break;
            case R.id.btn_3:
                Intent colorIntent = new Intent(this, ColorActivity.class);
                startActivityForResult(colorIntent, 1);
                break;
            case R.id.btn_4:
                Intent alignIntent = new Intent(this, AlignmentActivity.class);
                startActivityForResult(alignIntent, 2);
                break;
        }
    }

}