package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText numero1;
    private EditText numero2;
    private EditText numero3;
    private EditText numero4;
    private Button pingButton;
    private Button hostButton;
    private String num1, num2, num3, num4,num41;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numero1 = findViewById(R.id.numero1);
        numero2 = findViewById(R.id.numero2);
        numero3 = findViewById(R.id.numero3);
        numero4 = findViewById(R.id.numero4);
        pingButton = findViewById(R.id.pingButton);
        hostButton = findViewById(R.id.hostButton);
        pingButton.setOnClickListener(this);
        hostButton.setOnClickListener(this);

        SharedPreferences preferences = getSharedPreferences("dato",MODE_PRIVATE);
        //num41= getString("seleccion", "NO_COLOR");

    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.pingButton:

                num1 = numero1.getText().toString();
                num2 = numero2.getText().toString();
                num3 = numero3.getText().toString();
                num4 = numero4.getText().toString();

                if(num1.equals("192") && num2.equals("168")&& num3.equals("0")) {
                    Intent i = new Intent(this, Ping_Activity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(this,"Siga el formato",Toast.LENGTH_LONG).show();
                }

                break;

            case R.id.hostButton:

                Intent n = new Intent(this, Host_Activity.class);
                startActivity(n);

                break;


        }

        SharedPreferences preferences = getSharedPreferences("dato",MODE_PRIVATE);
        preferences.edit().putString("numero",num4).apply();

        SharedPreferences preferencesnum2 = getSharedPreferences("dato2",MODE_PRIVATE);
        preferences.edit().putString("numero2",num2).apply();

        SharedPreferences preferencesnum3 = getSharedPreferences("dato3",MODE_PRIVATE);
        preferences.edit().putString("numero3",num3).apply();

        SharedPreferences preferencesnum1 = getSharedPreferences("dato1",MODE_PRIVATE);
        preferences.edit().putString("numero1",num1).apply();


    }






}

