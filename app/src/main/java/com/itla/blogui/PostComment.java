package com.itla.blogui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.itla.blogui.entidad.Postui;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PostComment extends AppCompatActivity {
    private static final String TAG="PCOMMENT";
    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_comment);

        List<Postui> listDatos;
        String position="";
        Intent i = getIntent();
        int p = 0;

        Bundle extras = getIntent().getExtras();
        if(extras == null) {
            position = "";
            listDatos = null;
            p=0;
        } else {
            position= extras.getString("Aposition");
            listDatos = (List<Postui>) i
                    .getSerializableExtra("listDatos");
            p=Integer.valueOf(position);
        }

        Log.d(TAG, " La posición : "+ p);
        Log.i(TAG," La posición : "+ p + " - Id del post: "+ listDatos.get(p).getId()+ " - Cuerpo: "+listDatos.get(p).getBody());

    }
}
