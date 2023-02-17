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
public class ListAdapterIconText extends RecyclerView.Adapter<ListAdapterIconText.ViewHolder> {

    public List<String> listUsuarios;
    public LayoutInflater layoutInflater;
    public Context context;

    public ListAdapterIconText() {
    }

    public ListAdapterIconText(List<String> listUsuarios, Context context) {
        this.listUsuarios = listUsuarios;
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //vista o card view
        View view = layoutInflater.inflate(R.layout.cv_item_icon_text, null);
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
        TextView txtNomPDF;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            manejoVista = itemView;
            iv = itemView.findViewById(R.id.ic_descargar);
            txtNomPDF = itemView.findViewById(R.id.txt_titulo_cr);
        }

        void bindData(final  String itemCv){
            //aqui van los valores a modificar el card view,
            //los textos y demas.

            txtNomPDF.setText(itemCv);
            manejoVista.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "Click.. "+itemCv, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
