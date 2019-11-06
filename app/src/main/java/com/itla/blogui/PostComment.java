package com.itla.blogui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.itla.blogui.entidad.Postui;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PostComment extends AppCompatActivity {
    private static final String TAG="PCOMMENT";
    private TextView pidescription,piusuario, picomentario, pivista,pilike,pititulo,pitags,pifecha,piidmensaje;
    private EditText pccomentar;
    private ImageView liked;
    //private Button bcomentar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_comment);

        List<Postui> listDatos;
        String position="";
        Intent i = getIntent();
        int p = 0;

        Bundle extras = getIntent().getExtras();
        if(extras == null) {
            position = "";
            listDatos = null;
            p=0;
        } else {
            position= extras.getString("Aposition");
            listDatos = (List<Postui>) i
                    .getSerializableExtra("listDatos");
            p=Integer.valueOf(position);
        }

        Log.i(TAG, " La posición : "+ p);
        Log.i(TAG," La posición : "+ p + " - Id del post: "+ listDatos.get(p).getId()+ " - Cuerpo: "+listDatos.get(p).getBody());

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
            piusuario.setText("Por: "+listDatos.get(p).getUserEmail());
            pivista.setText(String.valueOf(listDatos.get(p).getViews())+" Vistas");
            pilike.setText(String.valueOf(listDatos.get(p).getLikes())+" Likes");
            pititulo.setText(listDatos.get(p).getTitle());
           picomentario.setText(String.valueOf(listDatos.get(p).getComments())+" Comentarios");
        String tags = "";
        if(!listDatos.get(p).getTags().equals(null)) {
            for (String tag : listDatos.get(p).getTags()) {
                tags = tags + tag + ", ";
            }
            /***********PROCESO PARA QUITAR ÚLTIMO CARACTER DE EL ARRAY TAGS**********/
            if(!tags.equals("")) {
                char coma = ',';
                int pos = tags.lastIndexOf(coma);
                tags = tags.substring(0,pos);
            }
        }
        pitags.setText(tags);
        Date fecha = new Date(listDatos.get(p).getCreatedAt());
        pifecha.setText(String.valueOf(fecha));
        piidmensaje.setText(String.valueOf(listDatos.get(p).getId()));
        liked = findViewById(R.id.post_item_liked);
        if(listDatos.get(p).isLiked())
        {liked.setImageResource(R.drawable.liked);}else{liked.setImageResource(R.drawable.likeyn);}
        /******************AGREGAR LA INFORMACION EN EL LAYOUT DE POST*************************/


        /******************AGREGAR LA INFORMACION de los comentarios del post*************************/



        /******************AGREGAR LA INFORMACION de los comentarios del post*************************/

    }
}
