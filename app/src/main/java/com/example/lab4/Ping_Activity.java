package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class Ping_Activity extends AppCompatActivity implements View.OnClickListener {

    private TextView textPing;
    private Button regresarButton;
    private String recibido, numero;
    private boolean buscar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ping_);

        textPing = findViewById(R.id.textPing);
        regresarButton = findViewById(R.id.regresarButton);
        regresarButton.setOnClickListener(this);
        textPing.setText(" ");
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

                                    InetAddress ine = InetAddress.getByName("192"+"."+"168.0."+ numero);
                                    boolean conectado = ine.isReachable(1000);

                                    if (conectado == true) {
                                        recibido = "Recibido";

                                    } if (conectado == false) {
                                        recibido = "Perdido";
                                    }

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
        numero = preferences.getString("numero", "NO_PING");
        Log.e("PRUEBA",""+numero);




    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}