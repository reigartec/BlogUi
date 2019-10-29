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
import java.util.ArrayList;
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


public class InicioActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private BottomNavigationView bottomNavigationView;
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




        ejecutarRecyclerView();


    }

    private void ejecutarRecyclerView(){


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
                for (Postui pi: list){
                    Log.i(TAG, "ID: "+pi.getId());

                    for (String t : pi.getTags() ){
                        Log.i(TAG, " tags : "+t);
                    }
                    Log.i(TAG,"Titulo: "+pi.getTitle());
                    //Log.i(TAG,"TAGS del cuerpo: "+ pi.getTags());
                }
                    Log.i(TAG,"Cuerpo completo: "+list.size());


               // Toast.makeText(this, "Cuerpo completo: "+list.size(), Toast.LENGTH_SHORT).show();
                try {

                    AdapterDatos adapter = new AdapterDatos(list);
                    recycler.setAdapter(adapter);
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

    private void getPost(List<Postui> post) {
        final ArrayList<Postui> list = new ArrayList<>();

        for(Postui postui:post){
            Postui addpostui = new Postui();
            addpostui.setId(postui.getId());
            addpostui.setBody(postui.getBody());
            addpostui.setComments(postui.getComments());
            addpostui.setCreatedAt((Long) postui.getCreatedAt());
            addpostui.setLiked(postui.isLiked());
            addpostui.setLikes(postui.getLikes());
            if(!addpostui.getTags().equals(null)) {
                   addpostui.setTags(postui.getTags());
            }else{
                addpostui.setTags(null);
            }
            addpostui.setTitle(postui.getTitle());
            addpostui.setUserEmail(postui.getUserEmail());
            addpostui.setUserName(postui.getUserName());
            addpostui.setViews(postui.getViews());
            addpostui.setUserId(postui.getUserId());
            list.add(addpostui);
            Log.i(TAG,"El ID Del post es: "+postui.getId());
            Log.i(TAG,"TAGS del cuerpo: "+ postui.getTags());
        }
       // AdapterDatos adapter = new AdapterDatos(list);
       // recycler.setAdapter(adapter);


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
}
