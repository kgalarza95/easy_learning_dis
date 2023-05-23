package com.example.proyfragmentmodal.util;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.example.proyfragmentmodal.R;
import com.example.proyfragmentmodal.dao.IDaoService;
import com.example.proyfragmentmodal.entity.EntityMap;
import com.example.proyfragmentmodal.entity.Respuesta;
import com.google.gson.Gson;

import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ListAdapterAsigancionesNoEntregada
        extends RecyclerView.Adapter<ListAdapterAsigancionesNoEntregada.ViewHolder>
        implements IDaoService.DAOCallbackServicio {

    public List<EntityMap> listUsuarios;
    public LayoutInflater layoutInflater;
    public Context context;
    private Gson gson = new Gson();

    public ListAdapterAsigancionesNoEntregada() {
    }

    public ListAdapterAsigancionesNoEntregada(List<EntityMap> listUsuarios, Context context) {
        this.listUsuarios = listUsuarios;
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //vista o card view
        View view = layoutInflater.inflate(R.layout.cv_item_asignacion, null);
        view.findViewById(R.id.textView10).setVisibility(View.GONE);
        view.findViewById(R.id.textView12).setVisibility(View.GONE);
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

    public void setItem(List<EntityMap> listUsuarios) {
        this.listUsuarios = listUsuarios;
    }

    @Override
    public void onSuccess(String response) {
        try {
            Log.i("response============>:  ", response);
            Respuesta data = gson.fromJson(response, Respuesta.class);

            if (data.getCodResponse().equals("00")) {

            } else {
                Toast.makeText(context, data.getMsjResponse(), Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(VolleyError error) {
        try {
            Log.e("response============>:  ", String.valueOf(error));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View manejoVista;
        TextView txtTitulo;
        TextView txtEstudiante;
        TextView txtContenido;
        TextView txtCalificacion;

        @SuppressLint("CutPasteId")
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            manejoVista = itemView;
            txtTitulo = itemView.findViewById(R.id.txt_titulo);
            txtEstudiante = itemView.findViewById(R.id.txt_estudiante);
            txtContenido = itemView.findViewById(R.id.txt_contenido);
            txtCalificacion = itemView.findViewById(R.id.txt_calificacion);
            txtCalificacionG = txtCalificacion;
        }

        void bindData(final EntityMap itemCv) {
            //aqui van los valores a modificar el card view,
            //los textos y demas.

            txtTitulo.setText(itemCv.getTITULO());
            txtEstudiante.setText(itemCv.getNOMBRES());
            txtContenido.setText(itemCv.getINSTRUCCIONES());
            txtCalificacion.setText(itemCv.getCALIFICACION());

        }
    }

    TextView txtCalificacionG;


}
