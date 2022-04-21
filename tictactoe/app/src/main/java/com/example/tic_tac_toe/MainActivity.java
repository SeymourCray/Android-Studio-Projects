package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button b11,b12,b13,b21,b22,b23,b31,b32,b33;
    int count = 1;
    String input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b11 = findViewById(R.id.b11);
        b12 = findViewById(R.id.b12);
        b13 = findViewById(R.id.b13);
        b21 = findViewById(R.id.b21);
        b22 = findViewById(R.id.b22);
        b23 = findViewById(R.id.b23);
        b31 = findViewById(R.id.b31);
        b32 = findViewById(R.id.b32);
        b33 = findViewById(R.id.b33);
        b11.setOnClickListener(this);
        b12.setOnClickListener(this);
        b13.setOnClickListener(this);
        b21.setOnClickListener(this);
        b22.setOnClickListener(this);
        b23.setOnClickListener(this);
        b31.setOnClickListener(this);
        b32.setOnClickListener(this);
        b33.setOnClickListener(this);
        SharedPreferences pref = getPreferences(MODE_PRIVATE);
        b11.setText(getValue("b11", pref));
        b12.setText(getValue("b12", pref));
        b13.setText(getValue("b13", pref));
        b21.setText(getValue("b21", pref));
        b22.setText(getValue("b22", pref));
        b23.setText(getValue("b23", pref));
        b31.setText(getValue("b31", pref));
        b32.setText(getValue("b32", pref));
        b33.setText(getValue("b33", pref));
        if (!getValue("count", pref).equals("")){
            count = Integer.parseInt(getValue("count", pref));
        }
    }





    @Override
    public void onClick(View view) {
        input = getSymbol(count);
        switch (view.getId()) {
            case R.id.b11:
                if (b11.getText().equals("")) {
                    b11.setText(input);
                }
                break;
            case R.id.b12:
                if (b12.getText().equals("")) {
                    b12.setText(input);
                }
                break;
            case R.id.b13:
                if (b13.getText().equals("")) {
                    b13.setText(input);
                }
                break;
            case R.id.b21:
                if (b21.getText().equals("")) {
                    b21.setText(input);
                }
                break;
            case R.id.b22:
                if (b22.getText().equals("")) {
                    b22.setText(input);
                }
                break;
            case R.id.b23:
                if (b23.getText().equals("")) {
                    b23.setText(input);
                }
                break;
            case R.id.b31:
                if (b31.getText().equals("")) {
                    b31.setText(input);
                }
                break;
            case R.id.b32:
                if (b32.getText().equals("")) {
                    b32.setText(input);
                }
                break;
            case R.id.b33:
                if (b33.getText().equals("")) {
                    b33.setText(input);
                }
                break;
        }
        count = count + 1;
        checkPositions();
    }



    public String getValue(String i, SharedPreferences pref){
        try {
            return pref.getString(i, "");

        }catch (Exception e){
            return "";
        }
    }




    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();
        editor.putString( "b11", String.valueOf(b11.getText()));
        editor.putString( "b12", String.valueOf(b12.getText()));
        editor.putString( "b13", String.valueOf(b13.getText()));
        editor.putString( "b21", String.valueOf(b21.getText()));
        editor.putString( "b22", String.valueOf(b22.getText()));
        editor.putString( "b23", String.valueOf(b23.getText()));
        editor.putString( "b31", String.valueOf(b31.getText()));
        editor.putString( "b32", String.valueOf(b32.getText()));
        editor.putString( "b33", String.valueOf(b33.getText()));
        editor.putString( "count", String.valueOf(count));
        editor.commit();
    }



    protected String getSymbol(int i){
        if (i%2==0){
            return "○";
        } else if (i%2==1){
            return "×";
        }
        return null;
    }



    protected void checkPositions(){
        if (b11.getText().equals(b12.getText()) && b12.getText().equals(b13.getText()) && !b11.getText().equals("")){
            Toast.makeText(getApplicationContext(), "Игрок " + b11.getText()+ " победил!", Toast.LENGTH_LONG).show();
            clearFields();
        } else if (b21.getText().equals(b22.getText()) && b22.getText().equals(b23.getText()) && !b21.getText().equals("")){
            Toast.makeText(getApplicationContext(), "Игрок " + b21.getText()+ " победил!", Toast.LENGTH_LONG).show();
            clearFields();
        } else if (b31.getText().equals(b32.getText()) && b32.getText().equals(b33.getText()) && !b31.getText().equals("")){
            Toast.makeText(getApplicationContext(), "Игрок " + b31.getText()+ " победил!", Toast.LENGTH_LONG).show();
            clearFields();
        } else if (b11.getText().equals(b21.getText()) && b21.getText().equals(b31.getText()) && !b31.getText().equals("")){
            Toast.makeText(getApplicationContext(), "Игрок " + b31.getText()+ " победил!", Toast.LENGTH_LONG).show();
            clearFields();
        } else if (b12.getText().equals(b22.getText()) && b22.getText().equals(b32.getText()) && !b12.getText().equals("")){
            Toast.makeText(getApplicationContext(), "Игрок " + b12.getText()+ " победил!", Toast.LENGTH_LONG).show();
            clearFields();
        } else if (b13.getText().equals(b23.getText()) && b23.getText().equals(b33.getText()) && !b13.getText().equals("")){
            Toast.makeText(getApplicationContext(), "Игрок " + b13.getText()+ " победил!", Toast.LENGTH_LONG).show();
            clearFields();
        } else if (b11.getText().equals(b22.getText()) && b22.getText().equals(b33.getText()) && !b11.getText().equals("")){
            Toast.makeText(getApplicationContext(), "Игрок " + b11.getText()+ " победил!", Toast.LENGTH_LONG).show();
            clearFields();
        } else if (b13.getText().equals(b22.getText()) && b22.getText().equals(b31.getText()) && !b13.getText().equals("")){
            Toast.makeText(getApplicationContext(), "Игрок " + b13.getText()+ " победил!", Toast.LENGTH_LONG).show();
            clearFields();
        } else if (count == 10){
            Toast.makeText(getApplicationContext(),"Победила дружба!", Toast.LENGTH_LONG).show();
            clearFields();
        }
    }


    protected void clearFields(){
        count = 1;
        b11.setText("");
        b12.setText("");
        b13.setText("");
        b21.setText("");
        b22.setText("");
        b23.setText("");
        b31.setText("");
        b32.setText("");
        b33.setText("");
    }


}