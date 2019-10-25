package com.itla.blogui.repositorio;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.itla.blogui.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterDatos extends RecyclerView.Adapter<AdapterDatos.ViewHolderDatos> {

    ArrayList<String> listDatos,listFecha, listTitulo, listTags, listDescripcion, listUsuario, listVista,
    listComentarios, listLike;


    public AdapterDatos(ArrayList<String> listDatos,
                        ArrayList<String> listFecha,
                        ArrayList<String> listTitulo,
                        ArrayList<String> listTags,
                        ArrayList<String> listDescripcion,
                        ArrayList<String> listUsuario,
                        ArrayList<String> listVista,
                        ArrayList<String> listComentarios,
                        ArrayList<String> listLike) {

        this.listDatos = listDatos;
        this.listFecha = listFecha;
        this.listTitulo = listTitulo;
        this.listTags = listTags;
        this.listDescripcion = listDescripcion;
        this.listUsuario = listUsuario;
        this.listVista = listVista;
        this.listComentarios = listComentarios;
        this.listLike = listLike;

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
        holder.asignarDatos(listDatos.get(position),listFecha.get(position),listTitulo.get(position),
                listTags.get(position),listDescripcion.get(position), listUsuario.get(position),
                listVista.get(position), listComentarios.get(position), listLike.get(position));
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

        public void asignarDatos(String datos, String fecha,String titulo, String tags,String descripcion,String usuario,String vista,String comentarios,String like) {

            mensaje.setText(datos);
            pifecha.setText(fecha);
            pititulo.setText(titulo);
            pitags.setText(tags);
            pidescripcion.setText(descripcion);
            piusuario.setText(usuario);
            pivista.setText(vista);
            picomentarios.setText(comentarios);
            pilike.setText(like);
        }
    }
}
