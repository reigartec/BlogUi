package com.itla.blogui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.itla.blogui.Procesos.PostDataSend;
import com.itla.blogui.entidad.Postui;
import com.itla.blogui.repositorio.AdapterChipTags;
import com.itla.blogui.repositorio.RetrofitClient;
import com.itla.blogui.repositorio.Service;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Tag;

public class NuevoPost extends AppCompatActivity{
    /*******RECYCLERVIEW*************/
    List<String> listTags;
    RecyclerView recycler;
    AdapterChipTags adapter;
    boolean asignar;
    /*******RECYCLERVIEW*************/
    Service service;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_post);

final EditText etags = findViewById(R.id.eTtags);
        service = RetrofitClient.getInstance().getService();
        /*******RECYCLERVIEW*************/
        recycler = findViewById(R.id.recyclerIdv);
        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
        listTags = new ArrayList<>();
        /*****************EVENTO ENTER DE LOS TAGS***********************/
        etags.setOnKeyListener(new View.OnKeyListener() {
    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        adapter = null;
        if(keyCode == KeyEvent.KEYCODE_ENTER){
            if(!etags.getText().toString().equals("")) {
                etags.setText(etags.getText().toString().toLowerCase().replace(" ",""));
               /***************VALIDACION DE TAGS*********************/
                if(listTags.size()>0){
                    for (String tag : listTags){
                        if(tag.equals(etags.getText().toString())){
                            etags.setError("Ya agregaste este tag!");
                            etags.setText("");
                            return false;
                        }
                    }
                }
                /***************VALIDACION DE TAGS*********************/
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
        /*****************EVENTO ENTER DE LOS TAGS***********************/


    }

    @RequiresApi(api = Build.VERSION_CODES.N)
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

        if(!(listTags.size() >0)) {

            if (tags.length() < 4) {
                ettags.setError("Los tags deben ser separados por espacios y mayor o igual a 4 caracteres.");
                ettags.requestFocus();
                return;
            }
        }

        String[] as = listTags.toArray(new String[0]);
        PostDataSend pds = new PostDataSend();
        pds.setBody(contenido);
        pds.setTags(as);
        pds.setTitle(titulo);

        Call<Postui> call = service.enviarPost(pds);

        call.enqueue(new Callback<Postui>() {
            @Override
            public void onResponse(Call<Postui> call, Response<Postui> response) {
                Log.i("nPost", "Codigo de error: "+response.code());
                Postui postui = response.body();
                Log.d("nResponse",postui.toString());
                etcontenido.setText("");
                ettags.setText("");
                ettitulo.setText("");
                listTags.clear();
                adapter = new AdapterChipTags(listTags);
                recycler.setAdapter(adapter);
               // Toast.makeText(this,"Post enviado!",Toast.LENGTH_LONG,);
            }

            @Override
            public void onFailure(Call<Postui> call, Throwable t) {
                Log.i("nError","------------------ No funciona ------------------ "+t.getMessage());
            }
        });


    }

    public void cancelar(View view){
        Intent it = null;
        it = new Intent(NuevoPost.this, InicioActivity.class);
        startActivity(it);
    }


}
