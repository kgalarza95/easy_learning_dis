package com.example.proyfragmentmodal.util;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyfragmentmodal.R;
import com.example.proyfragmentmodal.entity.EntityMap;
import com.example.proyfragmentmodal.profesor.Participantes;

import java.util.List;


//importante que se llame "ListAdapter" para que funcione el adpatador
//no es necesario llamarle ListAdapter
public class ListAdapterMisCursos extends RecyclerView.Adapter<ListAdapterMisCursos.ViewHolder> {

    public List<EntityMap> listaCursos;
    public LayoutInflater layoutInflater;
    public Context context;

    public ListAdapterMisCursos() {
    }

    public ListAdapterMisCursos(List<EntityMap> listaCursos, Context context) {
        this.listaCursos = listaCursos;
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //vista o card view (layout creado)
        View view = layoutInflater.inflate(R.layout.cv_item_cursos, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindData(listaCursos.get(position));
    }

    @Override
    public int getItemCount() {
        return listaCursos.size();
    }

    public void setItem(List<EntityMap> listUsuarios){
        this.listaCursos = listUsuarios;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View manejoVista;
        ImageView iv;

        TextView txtNomCurso;
        TextView txtDescCurso;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            manejoVista = itemView;
            //iv = itemView.findViewById(R.id.ic);
            txtNomCurso = itemView.findViewById(R.id.txt_titulo_cr);
            txtDescCurso = itemView.findViewById(R.id.txt_descripcion_cr);
        }

        void bindData(final  EntityMap objeto){
            //aqui van los valores a modificar el card view,
            //los textos y demas.

            txtNomCurso.setText(objeto.getNOMBRE());
            txtDescCurso.setText(objeto.getDESCRIPCION());
            manejoVista.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Toast.makeText(context, "ID Clic.. "+objeto.getID(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, Participantes.class);
                    intent.putExtra("idCurso",objeto.getID());
                    context.startActivity(intent);
                }
            });
        }
    }
}
