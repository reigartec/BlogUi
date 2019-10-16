package com.itla.blogui;

import android.os.AsyncTask;
import android.print.PrintAttributes;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class EnviarLogin extends AsyncTask<Void,Void,Void> {

    Socket s;
    DataOutputStream dos;
    PrintWriter pw;

    @Override
    protected Void doInBackground(Void... voids) {
        final JSONObject data = new JSONObject();
        try {
            data.put("email","elnovato@itla.com");
            data.put("password","123456");
            String json = data.toString(1);

            s = new Socket("134.209.160.224/login",8080);//itla.hectorvent.com/login
            try (OutputStreamWriter out = new OutputStreamWriter(s.getOutputStream(), StandardCharsets.UTF_8)){out.write(json);}
            //pw = new PrintWriter(s.getOutputStream());


        }catch (Exception e)
        {
        e.printStackTrace();
        }




        return null;
    }
}
