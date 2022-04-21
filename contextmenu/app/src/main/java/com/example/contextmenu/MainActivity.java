package com.example.contextmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{


    TextView tvColor, tvSize;
    final String MENU_COLOR_RED = "#ff0000";
    final String MENU_COLOR_GREEN = "#00ff00";
    final String MENU_COLOR_BLUE = "#0000ff";
    final int MENU_SIZE_22 = 22;
    final int MENU_SIZE_26 = 26;
    final int MENU_SIZE_30 = 30;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvColor = findViewById(R.id.tvColor);
        tvSize = findViewById(R.id.tvSize);

        // для tvColor и tvSize необходимо создавать контекстное меню
        registerForContextMenu(tvColor);
        registerForContextMenu(tvSize);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.tvColor:
                menu.add(0, 1, 0, "Red");
                menu.add(0, 2, 0, "Green");
                menu.add(0, 3, 0, "Blue");
                break;
            case R.id.tvSize:
                menu.add(0, 4, 0, "22");
                menu.add(0, 5, 0, "26");
                menu.add(0, 6, 0, "30");
                break;
        }
    }


    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case 1:
                tvColor.setTextColor(Color.parseColor(MENU_COLOR_RED));
                break;
            case 2:
                tvColor.setTextColor(Color.parseColor(MENU_COLOR_GREEN));
                break;
            case 3:
                tvColor.setTextColor(Color.parseColor(MENU_COLOR_BLUE));
                break;
            case 4:
                tvSize.setTextSize(MENU_SIZE_22);
                break;
            case 5:
                tvSize.setTextSize(MENU_SIZE_26);
                break;
            case 6:
                tvSize.setTextSize(MENU_SIZE_30);
                break;
        }
        return super.onContextItemSelected(item);
    }
}