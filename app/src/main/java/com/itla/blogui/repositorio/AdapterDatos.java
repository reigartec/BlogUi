package com.itla.blogui.repositorio;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.itla.blogui.R;
import com.itla.blogui.entidad.Postui;

import java.util.ArrayList;
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
                .inflate(R.layout.item_list,null,false);
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

        public void asignarDatos(/*List<Postui>*/Postui postui) {

           // for(Postui postui:Postui) {
                mensaje.setText(String.valueOf(postui.getId()));
                pifecha.setText(String.valueOf(postui.getCreatedAt()));
                pititulo.setText(postui.getTitle());

                String tags = "";
                if(!postui.getTags().equals(null)) {
                    for (String tag : postui.getTags()) {
                        tags = tags + tag + ", ";
                    }
            /*for(int i = 0; i < Postui.getTags().length; i++){
                tags = tags+Postui.getTags()[i]+", ";
            }*/
                    /***********PROCESO PARA QUITAR ÚLTIMO CARACTER DE EL ARRAY TAGS**********/
                    if(!tags.equals("")) {
                        char coma = ',';
                        int pos = tags.lastIndexOf(coma);
                        tags = tags.substring(0,pos);
                    }
                }
                pitags.setText(tags);
                /***********PROCESO PARA QUITAR ÚLTIMO CARACTER DE EL ARRAY TAGS**********/

                pidescripcion.setText(postui.getBody());
                piusuario.setText(postui.getUserEmail());
                pivista.setText(String.valueOf(postui.getViews()));
                picomentarios.setText(String.valueOf(postui.getComments()));
                pilike.setText(String.valueOf(postui.getLikes()));
           // }
        }
    }
}
