package com.itla.blogui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.itla.blogui.EnviarLogin;

public class MainActivity extends AppCompatActivity {

    EnviarLogin elogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //TextView tView = (TextView) findViewById(R.id.login_Registrarse);
        Button bregistrar = (Button) findViewById(R.id.bregistrar);

        bregistrar.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                elogin = new EnviarLogin();
                elogin.doInBackground();
            }


        });
    }





}
