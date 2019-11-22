package com.itla.blogui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.itla.blogui.entidad.Postui;
import com.itla.blogui.entidad.User;
import com.itla.blogui.entidad.Users;
import com.itla.blogui.repositorio.AdapterDatos;
import com.itla.blogui.repositorio.RetrofitClient;
import com.itla.blogui.repositorio.Service;
import com.itla.blogui.repositorio.Sesion;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MiPerfilActivity extends AppCompatActivity implements AdapterDatos.OnPostListener {

    Sesion sesion = null;
    TextView name,email, post, fecha;
    /*******RECYCLERVIEW*************/
    List<Postui> listDatos;
    RecyclerView recycler;
    /*******RECYCLERVIEW*************/
    Service service;
    int id;
    //@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.miperfil_activity);

        name = findViewById(R.id.tusuario);
        email = findViewById(R.id.temail);
        post = findViewById(R.id.tposts);
        fecha = findViewById(R.id.tdate);

        sesion = new Sesion(getApplicationContext());
        String ids = sesion.get("id");
        id = Integer.valueOf(ids);

        User user = new User();

        service = RetrofitClient.getInstance().getService();
Log.d("Perfil:"," Antes de hacer la llamada");
        Call<User> call = service.buscarUsuario(id);
Log.d("Perfil:"," Despúes de hacer la llamada");
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
Log.d("Perfil","Impresion de la informacion usuario: ;  Codigo de llamado: "+response.code());
                user.setId(response.body().getId());
                user.setName(response.body().getName());
                user.setEmail(response.body().getEmail());
                user.setPosts(response.body().getPosts());
                user.setCreatedAt(response.body().getCreatedAt());


                SimpleDateFormat formatofecha = new SimpleDateFormat("dd-MM-yyyy");
                        //"yyyy-MM-dd HH:mm:ss");

                SimpleDateFormat dateformatMMDDYYYY = new SimpleDateFormat("dd/MM/yyyy");

                StringBuilder nowMMDDYYYY = new StringBuilder( dateformatMMDDYYYY.format( user.getCreatedAt() ) );

                fecha.setText(String.valueOf(nowMMDDYYYY));

                 name.setText(user.getName());
                 email.setText(user.getEmail());
                 post.setText(String.valueOf(user.getPosts()));
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });


        /*******RECYCLERVIEW*************/
        recycler = findViewById(R.id.recyclerIdv);
        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));

        ejecutarRecyclerView(this);
    }

    private void ejecutarRecyclerView(final AdapterDatos.OnPostListener onPostListener) {

        Call <List<Postui>> call = service.getPostui();

        call.enqueue(new Callback<List<Postui>>() {
            @Override
            public void onResponse(Call<List<Postui>> call, Response<List<Postui>> response) {
                Log.i("Perfil", "Codigo de error: "+response.code());
                List<Postui> list = response.body();
                Collections.reverse(list);//Ordenamos de forma descendente
                List<Postui> mispost = new ArrayList<Postui>();
                //Postui postuiml = new Postui();
                for (Postui pi: list){

                    if(pi.getUserId() == id){
                        mispost.add(pi);
                    }

                    //Log.i("Perfil", "ID: "+pi.getId());
                }

                try {

                    listDatos = mispost;
                    AdapterDatos adapter = new AdapterDatos(listDatos,onPostListener);
                    recycler.setAdapter(adapter);

                }catch (Exception e){
                    Log.i("Perfil","Error Adapter S: "+e.getMessage());
                }

            }

            @Override
            public void onFailure(Call<List<Postui>> call, Throwable t) {

            }
        });

    }

    //action_miperfil

    @Override
    public void onPostClick(int position) {
        listDatos.get(position);
        int id = listDatos.get(position).getId();
        Log.d("Perfil","Post Clicado de la posición : "+position+", ID: "+id);
        Intent intent = new Intent(this, PostComment.class);
        ArrayList<Postui> alistDatos = new ArrayList<>(listDatos);


        intent.putExtra("listDatos",alistDatos);
        Log.i("Perfil","Tamaño de la lista: "+alistDatos.get(position).getBody());
        intent.putExtra("Aposition", String.valueOf(position));

        /***********Ver el post y sumar el views*************/
        Call<Void> call = service.verPostSumarViews(id);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.i("Perfil"," Views sumado ");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.i("Perfil","  No funciono la llamada ");
            }
        });
        /***********Ver el post y sumar el views*************/

        startActivity(intent);
    }

}
