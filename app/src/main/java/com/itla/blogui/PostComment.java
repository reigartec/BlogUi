package com.itla.blogui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.itla.blogui.Procesos.ComentarioPost;
import com.itla.blogui.entidad.PostCommentList;
import com.itla.blogui.entidad.Postui;
import com.itla.blogui.repositorio.AdapterComments;
import com.itla.blogui.repositorio.RetrofitClient;
import com.itla.blogui.repositorio.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostComment extends AppCompatActivity {
    private static final String TAG = "PCOMMENT - PostConet";
    private TextView pidescription, piusuario, picomentario, pivista, pilike, pititulo, pitags, pifecha, piidmensaje;
    private EditText pccomentar;
    private ImageView liked;

    /*******RECYCLERVIEW*************/
    List<PostCommentList> postCommentLists;
    RecyclerView recycler;
    /*******RECYCLERVIEW*************/
    int p, id;
    List<Postui> listDatos;

    Service service;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_comment);

        //List<Postui> listDatos;
        String position = "";
        Intent i = getIntent();
        //int p = 0;

       service = RetrofitClient.getInstance().getService();

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            position = "";
            listDatos = null;
            p = 0;
        } else {
            position = extras.getString("Aposition");
            listDatos = (List<Postui>) i.getSerializableExtra("listDatos");
            p = Integer.valueOf(position);
            id = Integer.valueOf(listDatos.get(p).getId());
        }

        Log.i(TAG, " La posición : " + p);
        Log.i(TAG, " La posición : " + p + " - Id del post: " + listDatos.get(p).getId() + " - Cuerpo: " + listDatos.get(p).getBody());

        getComment(p, listDatos);

        /******************AGREGAR LA INFORMACION de los comentarios del post*************************/

        /*******RECYCLERVIEW*************/
        recycler = findViewById(R.id.recyclerId);
        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        /******************AGREGAR LA INFORMACION de los comentarios del post*************************/
        ejecutarRecyclerView(listDatos.get(p).getId());

/***************************************AGREGANDO O QUITANDO EL LIKE*****************************/
        liked = findViewById(R.id.post_item_liked);

/***************************************AGREGANDO O QUITANDO EL LIKE*****************************/

    }

    private void ejecutarRecyclerView(int id) {

        Call<List<PostCommentList>> call = service.getPostCommentList(id);

        call.enqueue(new Callback<List<PostCommentList>>() {
            @Override
            public void onResponse(Call<List<PostCommentList>> call, Response<List<PostCommentList>> response) {
                Log.i(TAG, "Codigo de error: " + response.code());
                List<PostCommentList> list = response.body();
                Collections.reverse(list);//Ordenamos de forma descendente
                for (PostCommentList pi : list) {
                    Log.i(TAG, "ID: " + pi.getId());
                    Log.i(TAG, "Email: " + pi.getUserEmail());
                    Log.i(TAG, "Comentarios: " + pi.getBody());
                }
                Log.i(TAG, "Cuerpo completo: " + list.size());

                try {

                    AdapterComments adapter = new AdapterComments(list);
                    recycler.setAdapter(adapter);
                    postCommentLists = list;
                } catch (Exception e) {
                    Log.i(TAG, "Error Adapter S: " + e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<List<PostCommentList>> call, Throwable t) {
                Log.i(TAG, "------------------ No funciona ------------------ " + t.getMessage());
            }

        });
    }

    public void likeyn(View view) {
        //identifico si necesito enviar el put para poner el like, sino lo elimino.
        if (!listDatos.get(p).isLiked()) {
            Call<Void> call = service.putLike(id);
            call.enqueue(new Callback<Void>() {
                public void onResponse(Call<Void> call, Response<Void> response) {
                    Log.i(TAG, "Codigo de error en like: " + response.code());
                    liked.setImageResource(R.drawable.liked);
                    listDatos.get(p).setLiked(true);
                    listDatos.get(p).setLikes(listDatos.get(p).getLikes()+1);
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Log.i(TAG, "------------------ No funciona ------------------ " + t.getMessage());
                }
            });
        } else {
            //liked.setImageResource(R.drawable.likeyn);
            Call<Void> call = service.delLike(id);
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    Log.i(TAG, "Codigo de error en like: " + response.code());
                    liked.setImageResource(R.drawable.likeyn);
                    listDatos.get(p).setLiked(false);
                    listDatos.get(p).setLikes(listDatos.get(p).getLikes()-1);
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Log.i(TAG, "------------------ No funciona ------------------ " + t.getMessage());
                }
            });
        }
    }

    public void getComment(int p, List<Postui> listDatos) {
        /******************AGREGAR LA INFORMACION EN EL LAYOUT DE POST*************************/
        pidescription = findViewById(R.id.post_item_descripcion);
        piusuario = findViewById(R.id.post_item_usuario);
        pivista = findViewById(R.id.post_item_vista);
        pilike = findViewById(R.id.post_item_like);
        pititulo = findViewById(R.id.post_item_titulo);
        pitags = findViewById(R.id.post_item_tags);
        pifecha = findViewById(R.id.post_item_fecha);
        piidmensaje = findViewById(R.id.idMensaje);

        picomentario = findViewById(R.id.post_comentarios);

        pidescription.setText(listDatos.get(p).getBody());
        piusuario.setText("Por: " + listDatos.get(p).getUserEmail());
        pivista.setText(String.valueOf(listDatos.get(p).getViews()) + " Vistas");
        pilike.setText(String.valueOf(listDatos.get(p).getLikes()) + " Likes");
        pititulo.setText(listDatos.get(p).getTitle());
        picomentario.setText(String.valueOf(listDatos.get(p).getComments()) + " Comentarios");
        String tags = "";
        if (!listDatos.get(p).getTags().equals(null)) {
            for (String tag : listDatos.get(p).getTags()) {
                tags = tags + tag + ", ";
            }
            /***********PROCESO PARA QUITAR ÚLTIMO CARACTER DE EL ARRAY TAGS**********/
            if (!tags.equals("")) {
                char coma = ',';
                int pos = tags.lastIndexOf(coma);
                tags = tags.substring(0, pos);
            }
        }
        pitags.setText(tags);
        Date fecha = new Date(listDatos.get(p).getCreatedAt());
        pifecha.setText(String.valueOf(fecha));
        piidmensaje.setText(String.valueOf(listDatos.get(p).getId()));
        liked = findViewById(R.id.post_item_liked);
        if (listDatos.get(p).isLiked()) {
            liked.setImageResource(R.drawable.liked);
        } else {
            liked.setImageResource(R.drawable.likeyn);
        }
        /******************AGREGAR LA INFORMACION EN EL LAYOUT DE POST*************************/

    }

    public void comentar(View view){
        Log.i(TAG, "------------------ Clic funcionando ------------------ " );
        final EditText comentar = findViewById(R.id.post_comment_comentar);
        String comentario = comentar.getText().toString();

        if (comentario.length() < 10){
            comentar.setError("Tu comentario debe al menos tener 10 o más caracteres!");
            comentar.requestFocus();
            return;
        }

        ComentarioPost cp = new ComentarioPost();
        cp.setBody(comentario);

        Call<Void> call = service.enviarComentario(id,cp);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                ejecutarRecyclerView(id);
                comentar.setText("");
                Log.i(TAG, "------------------ Comentario enviado ------------------ "+response.body() );
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.i(TAG, "------------------ No funciono! ------------------ " );
            }
        });

    }
}
