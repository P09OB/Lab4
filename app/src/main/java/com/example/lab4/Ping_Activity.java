package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class Ping_Activity extends AppCompatActivity implements View.OnClickListener {

    private TextView textPing;
    private Button regresarButton;
    private String recibido, numero1, numero2, numero3,numero4;
    private boolean buscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ping_);

        textPing = findViewById(R.id.textPing);
        regresarButton = findViewById(R.id.regresarButton);
        regresarButton.setOnClickListener(this);
        textPing.setText("");
        buscar = true;
        ping();



    }


    public void ping() {


        new Thread(

                () -> {

                    while (buscar) {

                        for (int i = 0; i < 300; i++) {
                            try {
                                Thread.sleep(2000);
                                try {

                                        InetAddress ine = InetAddress.getByName(numero1 + "." + numero2 + "." + numero3 + "." + numero4);
                                        boolean conectado = ine.isReachable(1000);

                                        if (conectado == true) {
                                            recibido = "Recibido";

                                        }
                                        if (conectado == false) {
                                            recibido = "Perdido";
                                        }
                                        Log.e("prueba1",recibido+"");
                                        runOnUiThread(
                                                ()->{
                                                    textPing.append(recibido+"\n");
                                                }

                                        );


                                } catch (UnknownHostException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                }
        ).start();

    }

    @Override
    public void onClick(View view) {

        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences preferences = getSharedPreferences("dato",MODE_PRIVATE);
        numero4 = preferences.getString("numero", "NO_PING");

        SharedPreferences preferencesnum2 = getSharedPreferences("dato2",MODE_PRIVATE);
        numero2 = preferences.getString("numero2","NO_PING");

        SharedPreferences preferencesnum3 = getSharedPreferences("dato3",MODE_PRIVATE);
        numero3 = preferences.getString("numero3","NO_PING");

        SharedPreferences preferencesnum1 = getSharedPreferences("dato1",MODE_PRIVATE);
        numero1 = preferences.getString("numero1","NO_PING");
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}