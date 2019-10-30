package com.itla.blogui.repositorio;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.itla.blogui.R;
import com.itla.blogui.entidad.Postui;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterDatos extends RecyclerView.Adapter<AdapterDatos.ViewHolderDatos> {

   List<Postui> listDatos;


    public AdapterDatos(List<Postui> listDatos) {

        this.listDatos = listDatos;
   }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.new_item_list,null,false);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder.asignarDatos(listDatos.get(position));
    }

    @Override
    public int getItemCount() {
        return listDatos.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        TextView mensaje;
        TextView pifecha;
        TextView pititulo;
        TextView pitags;
        TextView pidescripcion;
        TextView piusuario;
        TextView pivista;
        TextView picomentarios;
        TextView pilike;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            mensaje = itemView.findViewById(R.id.idMensaje);
            pifecha = itemView.findViewById(R.id.post_item_fecha);
            pititulo = itemView.findViewById(R.id.post_item_titulo);
            pitags = itemView.findViewById(R.id.post_item_tags);
            pidescripcion = itemView.findViewById(R.id.post_item_descripcion);
            piusuario = itemView.findViewById(R.id.post_item_usuario);
            pivista = itemView.findViewById(R.id.post_item_vista);
            picomentarios = itemView.findViewById(R.id.post_item_comentarios);
            pilike = itemView.findViewById(R.id.post_item_like);
        }

        public void asignarDatos(Postui postui) {

                mensaje.setText(String.valueOf(postui.getId()));

            Date fecha = new Date(postui.getCreatedAt());

                pifecha.setText(String.valueOf(fecha));

                String titulo = postui.getTitle();
                if(postui.getTitle().length() > 18){
                    titulo=titulo.substring(0,18)+"...";
                }else {titulo=postui.getTitle();}
                pititulo.setText(titulo);

                String tags = "";
                int cuenta = 0;
                if(!postui.getTags().equals(null)) {
                    for (String tag : postui.getTags()) {
                        tags = tags + tag + ", ";
                        cuenta++;
                        if(cuenta>2){
                            break;
                        }
                    }

                    /***********PROCESO PARA QUITAR ÚLTIMO CARACTER DE EL ARRAY TAGS**********/
                    if(!tags.equals("")) {
                        char coma = ',';
                        int pos = tags.lastIndexOf(coma);
                        tags = tags.substring(0,pos);
                    }
                }
                pitags.setText(tags);
                /***********PROCESO PARA QUITAR ÚLTIMO CARACTER DE EL ARRAY TAGS**********/

                String body = postui.getBody();
                if(postui.getBody().length() > 290) {
                    body = body.substring(0, 290)+"...";
                }else{
                    body = postui.getBody();
                }
                pidescripcion.setText(body);
                piusuario.setText(postui.getUserEmail());
                pivista.setText(String.valueOf(postui.getViews())+" Vistas");
                picomentarios.setText(String.valueOf(postui.getComments())+" Comentarios");
                pilike.setText(String.valueOf(postui.getLikes())+" Likes");
           // }
        }
    }
}
