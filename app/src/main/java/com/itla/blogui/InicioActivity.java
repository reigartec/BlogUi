package com.itla.blogui;
//import android.support.design.widget.NavigationView;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import  com.google.android.material.navigation.NavigationView;
import com.itla.blogui.entidad.Postui;
import com.itla.blogui.entidad.Users;
import com.itla.blogui.repositorio.AdapterDatos;
import com.itla.blogui.repositorio.RetrofitClient;
import com.itla.blogui.repositorio.Service;
//import android.widget.Toolbar;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class InicioActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, AdapterDatos.OnPostListener {
    private BottomNavigationView bottomNavigationView;
    //private List<Postui> listDatos = new List<Postui>;
    private static final String TAG="POSTUI";

    /*******RECYCLERVIEW*************/
    List<Postui> listDatos;
    RecyclerView recycler;
    /*******RECYCLERVIEW*************/
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        Toolbar toolbar = findViewById(R.id.toolbar_her);
        toolbar.setTitle("Blog Ui");

        setSupportActionBar(toolbar);

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        actionBarDrawerToggle.syncState();
        NavigationView navigationView = findViewById(R.id.menuview);
        navigationView.setNavigationItemSelectedListener(this);


            /*******RECYCLERVIEW*************/
            recycler = findViewById(R.id.recyclerId);
            recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));




        ejecutarRecyclerView(this);


        //recycler.setOnClickListener();


    }

    private void ejecutarRecyclerView(final AdapterDatos.OnPostListener onPostListener){


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Service.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Service service = retrofit.create(Service.class);

        Call <List<Postui>> call = service.getPostui();

//        Call <List<Postui>> call = RetrofitClient.getInstance().getService().getPostui();

        call.enqueue(new Callback<List<Postui>>() {
            @Override
            public void onResponse(Call<List<Postui>> call, Response<List<Postui>> response) {
                    Log.i(TAG, "Codigo de error: "+response.code());
                    List<Postui> list = response.body();
                Collections.reverse(list);//Ordenamos de forma descendente
                for (Postui pi: list){
                    Log.i(TAG, "ID: "+pi.getId());

                    for (String t : pi.getTags() ){
                        Log.i(TAG, " tags : "+t);
                    }
                    Log.i(TAG,"Titulo: "+pi.getTitle());
                    //Log.i(TAG,"TAGS del cuerpo: "+ pi.getTags());
                }
                    Log.i(TAG,"Cuerpo completo: "+list.size());

                try {

                    AdapterDatos adapter = new AdapterDatos(list,onPostListener);
                    recycler.setAdapter(adapter);
                    listDatos = list;
                }catch (Exception e){
                    Log.i(TAG,"Error Adapter S: "+e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<List<Postui>> call, Throwable t) {
                Log.i(TAG,"------------------ No funciona ------------------ "+t.getMessage());
            }
        });




        }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        seleccionarItem(menuItem.getItemId());
        return false;
    }

    public boolean seleccionarItem(int itemid) {


        Activity activity = null;
        Intent it = null;
        String titulo = "Blog Ui";

        if(itemid == R.id.action_Cerrars)
        {

            //Cerrar sesión
            titulo = titulo + " - Cerrando sesión!!! \n ";
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            preferences.edit().remove("id").commit();
            preferences.edit().remove("token").commit();
            it = new Intent(InicioActivity.this,MainActivity.class);

        }
        if(itemid == R.id.action_post)
        {



        }

        if(it != null)
        {startActivity(it);}

        Toast.makeText(this, ""+titulo, Toast.LENGTH_SHORT).show();
        DrawerLayout drawerLayout =  findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;

    }


    @Override
    public void onPostClick(int position) {
        listDatos.get(position);
        Log.d(TAG,"Post Clicado de la posición : "+position+", ID: "+listDatos.get(position).getId());
        Intent intent = new Intent(this, PostComment.class);
        ArrayList<Postui> alistDatos = new ArrayList<>(listDatos);


        intent.putExtra("listDatos",alistDatos);
        Log.i(TAG,"Tamaño de la lista: "+alistDatos.get(position).getBody());
        intent.putExtra("Aposition", String.valueOf(position));


        startActivity(intent);
    }
}
