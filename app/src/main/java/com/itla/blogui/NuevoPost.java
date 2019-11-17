package com.itla.blogui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import com.itla.blogui.repositorio.AdapterChipTags;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.http.Tag;

public class NuevoPost extends AppCompatActivity{
    /*******RECYCLERVIEW*************/
    List<String> listTags;
    RecyclerView recycler;
    boolean asignar;
    /*******RECYCLERVIEW*************/
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_post);

final EditText etags = findViewById(R.id.eTtags);

        /*******RECYCLERVIEW*************/
        recycler = findViewById(R.id.recyclerIdv);
        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
listTags = new ArrayList<>();
etags.setOnKeyListener(new View.OnKeyListener() {
    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        AdapterChipTags adapter = null;
        if(keyCode == KeyEvent.KEYCODE_ENTER){
            if(!etags.getText().toString().equals("")) {
                listTags.add(etags.getText().toString());
                Log.i("Blog - Tag", "Entro en el enter - variable: " + etags.getText().toString());
                adapter = new AdapterChipTags(listTags);
                recycler.setAdapter(adapter);
            }
            etags.setText("");
        return true;
        }
        return false;
    }


});





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
        Intent it = null;
        it = new Intent(NuevoPost.this, InicioActivity.class);
        startActivity(it);
    }


}
