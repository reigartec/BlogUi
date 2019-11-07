package com.itla.blogui.repositorio;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.itla.blogui.PostComment;
import com.itla.blogui.R;
import com.itla.blogui.entidad.PostCommentList;
import com.itla.blogui.entidad.Postui;

import org.w3c.dom.Text;

import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterComments extends RecyclerView.Adapter<AdapterComments.ViewHolderDatos> {

    List<Postui> listDatos;

    public AdapterComments(List<Postui> listDatos) {
        this.listDatos = listDatos;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post_comment_list,null,false);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        //holder.asignarDatos(listDatos.get(position));
    }

    @Override
    public int getItemCount() {
        return listDatos.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        TextView mensaje;
        TextView usuario;
        TextView fecha;
        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            mensaje = itemView.findViewById(R.id.post_comment_list_mensaje);
            usuario = itemView.findViewById(R.id.post_comment_list_usuario);
            fecha = itemView.findViewById(R.id.post_comment_list_fecha);

        }

        public void asignarDatos(PostCommentList postComment){
            mensaje.setText(postComment.getBody());
            usuario.setText("Por: "+postComment.getUserName()+" ("+postComment.getUserEmail()+")");

            Date fechad = new Date(postComment.getCreatedAt());

            fecha.setText(String.valueOf(fechad));

        }
    }
}
