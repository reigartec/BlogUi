package com.itla.blogui;
//import android.support.design.widget.NavigationView;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import  com.google.android.material.navigation.NavigationView;
import com.itla.blogui.repositorio.AdapterDatos;
//import android.widget.Toolbar;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class InicioActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private BottomNavigationView bottomNavigationView;

    /*******RECYCLERVIEW*************/
    ArrayList<String> listDatos;
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
        listDatos = new ArrayList<String>();
        for (int i=0; i<50; i++){
            listDatos.add("Dato # "+i+": Si aún no tienes claro qué es Node.js, puedes revisar este artículo sobre qué es Node.js, ");
        }
        AdapterDatos adapter = new AdapterDatos(listDatos);
        recycler.setAdapter(adapter);
        /*******RECYCLERVIEW*************/
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
