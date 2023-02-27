package com.example.proyfragmentmodal.util;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyfragmentmodal.estudiante.EntregaTarea;
import com.example.proyfragmentmodal.R;
import com.example.proyfragmentmodal.entity.EntityMap;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ListAdapterTareas extends BaseAdapter {


    private Context context;
    private List<EntityMap> listaTareas;
    private List<EntityMap> filteredData;
    private int idListItemVista;

    public ListAdapterTareas() {
    }

    public ListAdapterTareas(Context context, List<EntityMap> listaTareas) {
        this.context = context;
        this.listaTareas = listaTareas;
    }

    @Override
    public int getCount() {
        return listaTareas.size();
    }

    @Override
    public Object getItem(int position) {
        return listaTareas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_tareas, parent, false);
        }

        EntityMap tarea = listaTareas.get(position);

        TextView txtTitulo = convertView.findViewById(R.id.txt_titulo_asig);
        txtTitulo.setText(tarea.getTITULO());

        TextView txtDescripcion = convertView.findViewById(R.id.txt_instrucciones_asig);
        txtDescripcion.setText(tarea.getINSTRUCCIONES());


        TextView txtFecha = convertView.findViewById(R.id.txt_fech_asig);
        txtFecha.setText(tarea.getFECHA_VENCIMIENTO());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             /*   Intent intent = new Intent(context, ChatGeneral.class);
                intent.putExtra("idTarea", tarea.getId());
                context.startActivity(intent);*/

                Date ahora =  new Date();
                Date fechaVence = null;
                try {

                    String fechaString = tarea.getFECHA_VENCIMIENTO(); //"27/02/2023";
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    fechaVence = sdf.parse(fechaString);

                    if (fechaVence.compareTo(ahora) >= 0) {
                        // permitir entrega.
                        Intent intent = new Intent(context, EntregaTarea.class);
                        intent.putExtra("titulo", tarea.getTITULO());
                        intent.putExtra("id_tarea", tarea.getID());
                        context.startActivity(intent);
                    }else {
                        Toast.makeText(context, "Ya no está permitida la entrega de esta tarea", Toast.LENGTH_SHORT).show();
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
        return convertView;
    }



}
