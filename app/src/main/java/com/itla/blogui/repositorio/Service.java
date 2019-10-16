package com.itla.blogui.repositorio;

import com.itla.blogui.entidad.Catalogo;
import com.itla.blogui.entidad.Users;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Service {
    public static final String BASE_URL = "http://itla.hectorvent.com/api/";
    public static final String TOKEN = "Bearer 98078dc4-40f3-4ba9-8ddd-7ac7ab8987d6";//usuario : kv@gm.com

    @Headers("Authorization:"+TOKEN)
    @GET("users")
    Call<List<Users>> getUsers();

    @POST("post/{id}/comment")
    Call<List<Users>> getUsers(@Path("id") int id);

}
