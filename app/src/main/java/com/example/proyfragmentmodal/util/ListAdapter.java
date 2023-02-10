package com.example.proyfragmentmodal.util;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyfragmentmodal.R;

import java.util.List;


//importante que se llame "ListAdapter" para que funcione el adpatador
//no es necesario llamarle ListAdapter
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    public List<String> listUsuarios;
    public LayoutInflater layoutInflater;
    public Context context;

    public ListAdapter() {
    }

    public ListAdapter(List<String> listUsuarios, Context context) {
        this.listUsuarios = listUsuarios;
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.cv_item_foro, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindData(listUsuarios.get(position));
    }

    @Override
    public int getItemCount() {
        return listUsuarios.size();
    }

    public void setItem(List<String> listUsuarios){
        this.listUsuarios = listUsuarios;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        View manejoVista;
        TextView txtTitulo;
        TextView txtMensaje;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            manejoVista = itemView;
            //iv = itemView.findViewById(R.id.imageView3);
            txtTitulo = itemView.findViewById(R.id.txt_titulo_cv);
            txtMensaje = itemView.findViewById(R.id.txt_mensaje_cv);
        }

        void bindData(final  String itemCv){
            //iv
            //aqui van los valores a modificar el card view,
            //los texttos y demas.

            txtTitulo.setText(itemCv);
            manejoVista.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "Click.. "+itemCv, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
