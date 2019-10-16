package com.itla.blogui;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.itla.blogui.entidad.Catalogo;
import com.itla.blogui.entidad.Users;
import com.itla.blogui.repositorio.Service;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EnviarLogin elogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //TextView tView = (TextView) findViewById(R.id.login_Registrarse);
        Button biniciar = (Button) findViewById(R.id.biniciar);

        biniciar.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                /*elogin = new EnviarLogin();
                elogin.doInBackground();*/
                Retrofit retrofit = null;
                try
                {
                    retrofit = new Retrofit.Builder().baseUrl(Service.BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create()).build();
                }
                catch (Exception e)
                {
                    Log.i("TAG","Error: "+e.getMessage().toString());
                }


                Service service = retrofit.create(Service.class);

                Call<List<Users>> requestCatalogo = service.getUsers();

                requestCatalogo.enqueue(new Callback<List<Users>>() {
                    @Override
                    public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                        if(!response.isSuccessful()){
                            Log.i("TAG","Error "+ response.code());
                        }
                        else
                        {
                            List<Users> catalogo = response.body();
                            for (Users u : catalogo){
                                Log.i("TAG", String.format("%s: %s", u.id, u.name));
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Users>> call, Throwable t) {
                        Log.e("TAG", "Error: " + t.getMessage());
                    }
                });


            }


        });
    }





}
