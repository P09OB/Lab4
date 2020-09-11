package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class Host_Activity extends AppCompatActivity implements View.OnClickListener {

    private TextView hostText;
    private Button regresarButton;
    private ArrayList<String> host;
    private boolean comenzar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_);

        host = new ArrayList<String>();
        comenzar = true;

        hostText = findViewById(R.id.hostText);
        regresarButton = findViewById(R.id.regresarButton);

        regresarButton.setOnClickListener(this);


        generateHost();
    }

    public void generateHost(){
        hostText.setText("");


        new Thread(

                ()-> {

                    while (comenzar) {
                        try {
                            Thread.sleep(500);

                            for (int i = 0; i < 255; i++) {

                                try {
                                    InetAddress ine = InetAddress.getByName("192.186.0." + i);
                                    String ipLocal = ine.getHostAddress();
                                    boolean conectado = ine.isReachable(1000);

                                    if (conectado == true) {

                                        Log.e("prueba", "192.186.0." + i);
                                        host.add(ipLocal + " " + conectado);
                                        String cadena = host.get(0).toString();

                                        runOnUiThread(
                                                () -> {
                                                    hostText.setText(host + "\n");
                                                }
                                        );
                                    }
                                } catch (UnknownHostException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        ).start();


    }



    @Override
    public void onClick(View view) {

        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);



    }
}