package com.example.proyfragmentmodal.adapter;


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


public class ListAdapterAsiganciones
        extends RecyclerView.Adapter<ListAdapterAsiganciones.ViewHolder>
        implements IDaoService.DAOCallbackServicio {

    public List<EntityMap> listUsuarios;
    public LayoutInflater layoutInflater;
    public Context context;
    private Gson gson = new Gson();

    public ListAdapterAsiganciones() {
    }

    public ListAdapterAsiganciones(List<EntityMap> listUsuarios, Context context) {
        this.listUsuarios = listUsuarios;
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //vista o card view
        View view = layoutInflater.inflate(R.layout.cv_item_asignacion, null);
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

    FileOutputStream fos = null;
    String filename;

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
        TextView txtFechaEntrega;

        @SuppressLint("CutPasteId")
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            manejoVista = itemView;
            txtTitulo = itemView.findViewById(R.id.txt_titulo);
            txtEstudiante = itemView.findViewById(R.id.txt_estudiante);
            txtContenido = itemView.findViewById(R.id.txt_contenido);
            txtCalificacion = itemView.findViewById(R.id.txt_calificacion);
            txtFechaEntrega = itemView.findViewById(R.id.txt_fch_entrega);
            txtCalificacionG = txtCalificacion;
        }

        void bindData(final EntityMap itemCv) {
            //aqui van los valores a modificar el card view,
            //los textos y demas.

            txtTitulo.setText(itemCv.getTITULO());
            txtEstudiante.setText(itemCv.getNOMBRES());
            txtContenido.setText(itemCv.getENTREGA());
            txtCalificacion.setText(itemCv.getCALIFICACION());
            txtFechaEntrega.setText(itemCv.getFECHA_ENTREGA());

            manejoVista.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    abrirCuadroDialogo(view, itemCv.getID());
                }
            });
        }
    }

    TextView txtCalificacionG;

    public void abrirCuadroDialogo(View view, String idTarea) {
        // Crea un objeto AlertDialog.Builder
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Calificar");

        // Crea un EditText para que el usuario ingrese la calificación
        final EditText editText = new EditText(context);
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        builder.setView(editText);

        // Agrega botones para "Aceptar" y "Cancelar"
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Obtiene el valor ingresado por el usuario
                String calificacion = editText.getText().toString();

                txtCalificacionG.setText(calificacion);

                Map<String, String> params = new HashMap<>();
                // opcion = "CN";
                params.put("opcion", "CALIF");
                params.put("calificacion", calificacion);
                params.put("id_tarea", idTarea);

                Log.i("Parámetros===>", String.valueOf(params));
                IDaoService dao = new IDaoService(context);
                dao.crudAsignacion(params, ListAdapterAsiganciones.this);
            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Cierra el cuadro de diálogo sin hacer nada
            }
        });

        // Muestra el cuadro de diálogo
        AlertDialog dialog = builder.create();
        dialog.show();
    }


}
