package com.itla.blogui;
//import android.support.design.widget.NavigationView;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.KeyEvent;
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
import com.itla.blogui.repositorio.Sesion;
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
    Service service;
    Sesion sesion;
    String token;
    Toolbar toolbar;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        toolbar = findViewById(R.id.toolbar_her);
        toolbar.setTitle("Blog Ui");

        setSupportActionBar(toolbar);

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        actionBarDrawerToggle.syncState();
        NavigationView navigationView = findViewById(R.id.menuview);
        navigationView.setNavigationItemSelectedListener(this);

        sesion = new Sesion(getApplicationContext());
        token = "Bearer "+sesion.get("token");
        Log.d("token: ",token);
        /*******RECYCLERVIEW*************/
        recycler = findViewById(R.id.recyclerId);
        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));



        service = RetrofitClient.getInstance().getService();

        ejecutarRecyclerView(this,"");

    }

    @Override
    public void onBackPressed() {

        setResult(RESULT_CANCELED);
        super.onBackPressed();
         Log.d("TAG","DIeron atras!!!");
    }
@Override
    public void onResume(){
        super.onResume();
        //ejecutarRecyclerView(this);
    Log.d("TAG","Volvimos");
    }

    private void ejecutarRecyclerView(final AdapterDatos.OnPostListener onPostListener, String fav){

        Call <List<Postui>> call = service.getPostui(token);

        call.enqueue(new Callback<List<Postui>>() {
            @Override
            public void onResponse(Call<List<Postui>> call, Response<List<Postui>> response) {
                Log.i(TAG, "Codigo de error: "+response.code());
                List<Postui> list = response.body();
                Collections.reverse(list);//Ordenamos de forma descendente
                List<Postui> misfav = new ArrayList<Postui>();
                for (Postui pi: list){
                    Log.i(TAG, "ID: "+pi.getId());
                    if(pi.isLiked()){
                        misfav.add(pi);//Si les gusta, nos creara una lista con los post que les gustan.
                    }
                }
                    Log.i(TAG,"Cuerpo completo: "+list.size());

                try {

                    if(!fav.equals("favoritos")){listDatos = list;}else{listDatos = misfav;}
                    AdapterDatos adapter = new AdapterDatos(listDatos,onPostListener);
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


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        seleccionarItem(menuItem.getItemId());
        return false;
    }

    public boolean seleccionarItem(int itemid) {


        Activity activity = null;
        Intent it = null;
        String titulo = "Blog Ui";
        String toast="";
        if(itemid == R.id.action_Cerrars)
        {

            //Cerrar sesión
            toast = titulo + " - Cerrando sesión!!! \n ";
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            preferences.edit().remove("id").commit();
            preferences.edit().remove("token").commit();
            it = new Intent(InicioActivity.this,MainActivity.class);

        }
        if(itemid == R.id.action_post)
        {
            titulo = titulo + " - Post \n ";
        ejecutarRecyclerView(this,"");
            toast = titulo;
        }

        if(itemid == R.id.action_npost)
        {
            toast = titulo + " - Nuevo Post!!! \n ";
            it = new Intent(InicioActivity.this, NuevoPost.class);

        }

        if(itemid == R.id.action_miperfil)
        {
            toast = titulo + " - Mi Perfil \n ";
            it = new Intent(InicioActivity.this, MiPerfilActivity.class);
        }

        if(itemid == R.id.action_postmegustan)
        {
            titulo = titulo + " - Favoritos \n ";
            ejecutarRecyclerView(this,"favoritos");
            toast = titulo;
        }
        toolbar.setTitle(titulo);
        if(it != null)
        {startActivity(it);}

        Toast.makeText(this, ""+toast, Toast.LENGTH_SHORT).show();
        DrawerLayout drawerLayout =  findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;

    }


    @Override
        public void onPostClick(int position) {
        listDatos.get(position);
        int id = listDatos.get(position).getId();
        Log.d(TAG,"Post Clicado de la posición : "+position+", ID: "+id);
        Intent intent = new Intent(this, PostComment.class);
        ArrayList<Postui> alistDatos = new ArrayList<>(listDatos);


        intent.putExtra("listDatos",alistDatos);
        Log.i(TAG,"Tamaño de la lista: "+alistDatos.get(position).getBody());
        intent.putExtra("Aposition", String.valueOf(position));

        /***********Ver el post y sumar el views*************/
        Call<Void> call = service.verPostSumarViews(id,token);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.i(TAG," Views sumado ");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.i(TAG,"  No funciono la llamada ");
            }
        });
        /***********Ver el post y sumar el views*************/

        startActivity(intent);
    }
}
