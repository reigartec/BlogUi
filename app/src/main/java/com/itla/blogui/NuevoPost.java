package com.itla.blogui;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class NuevoPost extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_post);

    }

    public void guardar(View view){
        EditText ettitulo, etcontenido, ettags;
        String titulo="", contenido="", tags ="";
        ettitulo = findViewById(R.id.eTtitulo);
        etcontenido = findViewById(R.id.eTcontenido);
        ettags = findViewById(R.id.eTtags);

        titulo = ettitulo.getText().toString();
        contenido = etcontenido.getText().toString();
        tags = ettags.getText().toString();

        if(titulo.length() < 4){
            ettitulo.setError("EL titulo debe ser mayor o igual a 4 caracteres.");
            ettitulo.requestFocus();
            return;
        }

        if(contenido.length() < 18){
            etcontenido.setError("El contenido debe ser mayor o igual a 18 caracteres.");
            etcontenido.requestFocus();
            return;
        }

        if(tags.length() < 4){
            ettags.setError("Los tags deben ser separados por espacios y mayor o igual a 4 caracteres.");
            ettags.requestFocus();
            return;
        }





    }

    public void cancelar(View view){

    }

}
