package com.itla.blogui.repositorio;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.chip.Chip;
import com.itla.blogui.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterChipTags extends RecyclerView.Adapter<AdapterChipTags.viewHolderTags>  {

    List<String> listtags;

    public AdapterChipTags(List<String> listTags){
        this.listtags = listTags;
    }


    @NonNull
    @Override
    public viewHolderTags onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.chip_item_layout,null,false);
        return new viewHolderTags(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolderTags holder, int position) {
        holder.asignarDatos(listtags.get(position));
    }

    @Override
    public int getItemCount() {
        return listtags.size();
    }

    public class viewHolderTags extends RecyclerView.ViewHolder{
        Chip chip;

        public viewHolderTags(@NonNull View itemView) {
            super(itemView);
            chip = itemView.findViewById(R.id.chipchipil);
        }
        public void asignarDatos(String tags){
            chip.setText(tags);
        }
    }
}
