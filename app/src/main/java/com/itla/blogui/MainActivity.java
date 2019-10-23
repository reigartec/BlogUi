package com.itla.blogui;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.itla.blogui.Procesos.LoginData;
import com.itla.blogui.entidad.Users;
import com.itla.blogui.repositorio.RetrofitClient;
import com.itla.blogui.repositorio.Sesion;

public class MainActivity extends AppCompatActivity {

    EnviarLogin elogin;
    Sesion sesion = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**MANEJO DE SESIONES********/
        sesion = new Sesion(getApplicationContext());
        String token = sesion.get("token");
        if(!token.equals("")) {
                //iniciar pantalla iniciar, ya que el usuario inicio sesion.
            Toast.makeText(MainActivity.this, "Ya iniciaste sesion!!!", Toast.LENGTH_LONG).show();
            Intent ventana = new Intent(MainActivity.this, InicioActivity.class);
            startActivity(ventana);
        }
        /**MANEJO DE SESIONES********/
        //TextView tView = (TextView) findViewById(R.id.login_Registrarse);
        Button biniciar = (Button) findViewById(R.id.biniciar);

        final EditText etemail = (EditText) findViewById(R.id.eTemail);
        final EditText etpassword = (EditText) findViewById(R.id.eTpassword);


        biniciar.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){

                /***Validar datos antes de enviar***/
                if(etemail.getText().toString().isEmpty()){
                    etemail.setError("Digite el email!");
                    etemail.requestFocus();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(etemail.getText().toString()).matches()){
                    etemail.setError("Email no válido!");
                    etemail.requestFocus();
                    return;
                }
                if (etpassword.getText().toString().isEmpty()){
                    etpassword.setError("Contraseña requerida*");
                    etpassword.requestFocus();
                    return;
                }
                /***Validar datos antes de enviar***/

                String email = etemail.getText().toString();
                String password = etpassword.getText().toString();

                LoginData ld = new LoginData();
                ld.setEmail(email);
                ld.setPassword(password);


                Call<Users> rLogin = RetrofitClient.getInstance().getService().loginUsuario(ld);

                rLogin.enqueue(new Callback<Users>() {
                    @Override
                    public void onResponse(Call<Users> call, Response<Users> response) {

                        if(response.body() == null){
                            etemail.setError("Credenciales no válidas!");
                            etpassword.setError("Credenciales no válidas!");
                            etemail.requestFocus();
                            Toast.makeText(MainActivity.this, "Credenciales erroneas!", Toast.LENGTH_LONG).show();
                            return;
                        }
                            Users user = response.body();

                            Log.i("Login", user.toString());
                            if(!user.token.equals("")){
                                //Proceso de login correcto
                                //debo guardar el usuario
                                //abrir el perfil o pantalla de bienvenida.
                                sesion = new Sesion(getApplicationContext());
                                sesion.set("token",user.token.toString());
                                sesion.set("id",String.valueOf(user.id));
                                //Agregar clase con el layout de los post. para el que ya inicio sesion
                                Toast.makeText(MainActivity.this, user.email.toString()+", tu sesión ha sido iniciada!".toString(),Toast.LENGTH_LONG).show();
                                Intent ventana = new Intent(MainActivity.this, InicioActivity.class);
                                startActivity(ventana);
                            }
                            else {
                                Toast.makeText(MainActivity.this, "Body no enviado o no entendido",Toast.LENGTH_LONG).show();
                            }
                    }

                    @Override
                    public void onFailure(Call<Users> call, Throwable t) {

                    }
                });

            }


        });
    }

    public void registrate( View view) {
        Intent registraser = new Intent(MainActivity.this, RegistrarUsuario.class);
        startActivity(registraser);
    }


}
