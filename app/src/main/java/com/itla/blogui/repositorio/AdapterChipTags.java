package com.itla.blogui.repositorio;

import android.content.Context;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.itla.blogui.NuevoPost;
import com.itla.blogui.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterChipTags extends RecyclerView.Adapter<AdapterChipTags.viewHolderTags>  {

    List<String> listtags;
    RecyclerView recyclerView;

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

    public class viewHolderTags extends RecyclerView.ViewHolder {
        Chip chip;
        LinearLayout linearLayout;

        public viewHolderTags(@NonNull View itemView) {
            super(itemView);
            chip = itemView.findViewById(R.id.chipchipil);
            linearLayout = itemView.findViewById(R.id.linearchip);

        }
        public void asignarDatos(String tags){
            chip.setText(tags);
            //chip.setCloseIconResource(R.drawable.liked);
            chip.setCloseIconEnabled(true);
            for (String ch : listtags){
                Log.d("chips restantes: ",ch);
            }
            //Added click listener on close icon to remove tag from ChipGroup
            chip.setOnCloseIconClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("prueba chips","funciona en el chip :"+chip.getText()+"posicion : "+getAdapterPosition());
                    listtags.remove(getAdapterPosition());
                    //linearLayout.removeViewAt(getAdapterPosition());
                    //chip.setVisibility(itemView.GONE);
                    chip.setVisibility(getAdapterPosition());
                    for (String ch : listtags){
                        Log.d("chips restantes: ",ch);
                    }

                }
            });


        }

    }


}
