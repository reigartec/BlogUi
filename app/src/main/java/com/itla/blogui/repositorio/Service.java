package com.itla.blogui.repositorio;

import android.app.Application;
import android.content.Context;
import android.media.session.MediaSession;

import com.google.gson.annotations.JsonAdapter;
import com.itla.blogui.Procesos.LoginData;
import com.itla.blogui.Procesos.RegistroData;
import com.itla.blogui.entidad.Users;

import org.json.JSONObject;

import java.util.List;

import androidx.core.content.ContextCompat;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Service {
    public static final String BASE_URL = "http://itla.hectorvent.com/api/";
    public static final String TOKEN = "Bearer 98078dc4-40f3-4ba9-8ddd-7ac7ab8987d6";//usuario : kv@gm.com

    /*
    public static final Sesion sesion = new Sesion(AplicacionContext.getAppContext());
    public static final String tokene = sesion.get("token");*/


    @Headers("Authorization:"+TOKEN)
    @GET("users")
    Call<List<Users>> getUsers();

    /***LLAMAMOS EL METODO PARA REVISAR LAS CREDENCIALES DE LOGIN***********/
    @Headers("Authorization: "+TOKEN)
    //@FormUrlEncoded   //Sustituido por body que enviamos un objeto JSON.
    @POST("login")
    Call<Users> loginUsuario(
            //@Body String body
            @Body  LoginData ld
            //@Field("email") String email,       //Sustituido por body que enviamos un objeto JSON.
            //@Field("password") String password
    );
    /***LLAMAMOS EL METODO PARA REVISAR LAS CREDENCIALES DE LOGIN***********/
    /***METODO RETROFIT REGISTRAR***********/
    @Headers("Authorization: "+TOKEN)
    @POST("users")
    Call<Users> registrarUsuario(
            @Body RegistroData rd
            );
    /***METODO RETROFIT REGISTRAR***********/

    @POST("post/{id}/comment")
    Call<List<Users>> getUsers(@Path("id") int id);

}
