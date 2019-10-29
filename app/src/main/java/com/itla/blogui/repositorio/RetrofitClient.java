package com.itla.blogui.repositorio;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL = "http://itla.hectorvent.com/api/";
    private static RetrofitClient Instancia;
    private Retrofit retrofit;

    private RetrofitClient(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static /*synchronized*/ RetrofitClient getInstance(){
        if(Instancia == null){
            Instancia = new RetrofitClient();
        }
        return Instancia;

    }

    public Service getService(){
        return retrofit.create(Service.class);
    }

}
