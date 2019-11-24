package com.itla.blogui.repositorio;
import com.itla.blogui.Procesos.ComentarioPost;
import com.itla.blogui.Procesos.LoginData;
import com.itla.blogui.Procesos.PostDataSend;
import com.itla.blogui.Procesos.RegistroData;
import com.itla.blogui.entidad.PostCommentList;
import com.itla.blogui.entidad.Postui;
import com.itla.blogui.entidad.User;
import com.itla.blogui.entidad.Users;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Service {
    public static final String TOKEN = "Bearer 98078dc4-40f3-4ba9-8ddd-7ac7ab8987d6";//usuario : kv@gm.com

    /***METODO RETROFIT REGISTRAR***********/
    @Headers("Authorization:"+TOKEN)
    @POST("users")
    Call<Users> registrarUsuario(@Body RegistroData rd );
    /***METODO RETROFIT REGISTRAR***********/
    /***LLAMAMOS EL METODO PARA REVISAR LAS CREDENCIALES DE LOGIN***********/
    @POST("login")
    Call<Users> loginUsuario( @Body  LoginData ld);//@FormUrlEncoded   //Sustituido por body que enviamos un objeto JSON.
    /***LLAMAMOS EL METODO PARA REVISAR LAS CREDENCIALES DE LOGIN***********/
    /***LLAMANDO LOS POST*******/
    @GET("post")
    Call<List<Postui>> getPostui(@Header("Authorization") String authHeader);
    /***LLAMANDO LOS POST*******/
    /***LLAMANDO LOS COMENTARIOS DE LOS POST*******/
    @GET("post/{id}/comment")
    Call<List<PostCommentList>> getPostCommentList(@Path("id") int id, @Header("Authorization") String authHeader);
    /***LLAMANDO LOS COMENTARIOS DE LOS POST*******/

    /***********DANDO LIKE AL COMENTARIO*************/
    @PUT("post/{id}/like")
    Call<Void> putLike(@Path("id") int id, @Header("Authorization") String authHeader);
    /***********DANDO LIKE AL COMENTARIO*************/

    /***********QUITANDO EL LIKE AL COMENTARIO*************/
    @DELETE("post/{id}/like")
    Call<Void> delLike(@Path("id") int id, @Header("Authorization") String authHeader);
    /***********QUITANDO EL LIKE AL COMENTARIO*************/

    /***********Ver el post y sumar el views*************/
    @GET("post/{id}")
    Call<Void> verPostSumarViews(@Path("id") int id,@Header("Authorization") String authHeader);
    /***********Ver el post y sumar el views*************/
/*************ENVIAR LOS COMENTARIOS DE LOS POST*************/
    @POST("post/{id}/comment")
    Call<Void> enviarComentario(@Path("id") int id,  @Body ComentarioPost cp, @Header("Authorization") String authHeader);
/*************ENVIAR LOS COMENTARIOS DE LOS POST*************/
    /*************ENVIAR LOS POST*************/
    @POST("post")
    Call<Postui> enviarPost(@Body PostDataSend pds, @Header("Authorization") String authHeader);
/*************ENVIAR LOS POST*************/

/*************OBTENER MI INFORMACION DE USUARIO*************/
    /*************ENVIAR LOS POST*************/
    @GET("users/{id}")
    Call<User> buscarUsuario(@Path("id") int id, @Header("Authorization") String authHeader);
/*************OBTENER MI INFORMACION DE USUARIO*************/
}