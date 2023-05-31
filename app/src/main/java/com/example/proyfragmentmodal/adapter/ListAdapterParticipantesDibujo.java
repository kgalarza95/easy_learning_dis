package com.example.proyfragmentmodal.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.proyfragmentmodal.ImagenesBase;
import com.example.proyfragmentmodal.R;
import com.example.proyfragmentmodal.general.ChatGeneral;

import java.util.ArrayList;

public class ListAdapterParticipantesDibujo extends
        BaseAdapter {

    private Context context;
    //private ArrayList<ListItem> items;
    private ArrayList<String> listaElementos;

    private ArrayList<String> filteredData;
    private int idListItemVista;

    public ListAdapterParticipantesDibujo() {
    }

    public ListAdapterParticipantesDibujo(Context context, ArrayList<String> items) {
        this.context = context;
        this.listaElementos = items;
    }

    @Override
    public int getCount() {
        return listaElementos.size();
    }

    @Override
    public Object getItem(int position) {
        return listaElementos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_participante, parent, false);
        }

        String item = listaElementos.get(position);

        TextView textView = convertView.findViewById(R.id.txt_titulo_asig);
        textView.setText(item);


        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ImagenesBase.class);
                String[] split = item.split(" ");
                intent.putExtra("idCurso", item);
                intent.putExtra("idEstudiante", split[0]);
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    // método clear para borrar todos los elementos de la lista
    public void clear() {
        listaElementos.clear(); // llama al método clear de la clase base ArrayAdapter
    }

    // método addAll para agregar varios elementos a la lista al mismo tiempo
    public void addAll(ArrayList<String> items) {
        listaElementos.addAll(items); // llama al método addAll de la clase base ArrayAdapter
    }



}
