package com.itla.blogui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.itla.blogui.Procesos.RegistroData;
import com.itla.blogui.entidad.Users;
import com.itla.blogui.repositorio.RetrofitClient;
import com.itla.blogui.repositorio.Service;

import androidx.appcompat.app.AppCompatActivity;

public class RegistrarUsuario extends AppCompatActivity {

    Service service;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        //Cerrar sesión
        /****HASTA CREAR EL ACTIVITY Y CLASE PARA TENER LA OPCION DE CERRAR SESION****/
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        preferences.edit().remove("token").commit();
        preferences.edit().remove("id").commit();
        /***HASTA CREAR EL ACTIVITY Y CLASE PARA TENER LA OPCION DE CERRAR SESION****/

        Button inciar = (Button) findViewById(R.id.biniciar);

        service = RetrofitClient.getInstance().getService();

        inciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText name = findViewById(R.id.eTusuario);
                final EditText email = findViewById(R.id.eTemail);
                EditText password = findViewById(R.id.eTpassword);
                EditText password2 = findViewById(R.id.eTpasswordb);

                String sname = name.getText().toString();
                String semail = email.getText().toString();
                String spassword = password.getText().toString();
                String spassworddb  = password2.getText().toString();

                /***Validar datos antes de enviar***/
                if(name.getText().toString().isEmpty()){
                    name.setError("Obligatorio(*)");
                    name.requestFocus();
                    return;
                }
                if(email.getText().toString().isEmpty()){
                    email.setError("Obligatorio(*)");
                    email.requestFocus();
                    return;
                }
                if(password.getText().toString().isEmpty()){
                    password.setError("Obligatorio(*)");
                    password.requestFocus();
                    return;
                }
                if(password2.getText().toString().isEmpty()){
                    password2.setError("Obligatorio(*)");
                    password2.requestFocus();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()){
                    email.setError("Email no válido!");
                    email.requestFocus();
                    return;
                }
                if (name.getText().toString().length() < 3){
                    name.setError("Tu usuario debe contener 3 o más caracteres");
                    name.requestFocus();
                    return;
                }
                if (password.getText().toString().length() < 3){
                    password.setError("La contraseña debe contener 3 o más caracteres*");
                    password.requestFocus();
                    return;
                }
                if(!spassword.equals(spassworddb)){
                    password.setError("La contraseña no coincide*");
                    password.requestFocus();
                    return;
                }
                /***Validar datos antes de enviar***/

                RegistroData rd = new RegistroData();
                rd.setEmail(semail);
                rd.setName(sname);
                rd.setPassword(spassword);

            Call<Users> rusuario = service.registrarUsuario(rd);

            rusuario.enqueue(new Callback<Users>() {
                @Override
                public void onResponse(Call<Users> call, Response<Users> response) {
                    if(response.body() == null){
                        email.setError("Credenciales ya registradas!");
                        email.setError("Credenciales no válidas!");
                        email.requestFocus();
                        Toast.makeText(RegistrarUsuario.this, "Credenciales ya registradas!", Toast.LENGTH_LONG).show();
                        return;
                    }

                    Toast.makeText(RegistrarUsuario.this, response.body().name+", Bienvenido!".toString(),Toast.LENGTH_LONG).show();
                    cancela(null);
                }

                @Override
                public void onFailure(Call<Users> call, Throwable t) {

                }
            });


            }
        });

    }

    public void cancela(View view) {
        Intent atras = new Intent(RegistrarUsuario.this, MainActivity.class);
        startActivity(atras);
    }


}
