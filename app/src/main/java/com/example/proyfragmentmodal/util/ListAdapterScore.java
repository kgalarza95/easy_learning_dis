package com.example.proyfragmentmodal.util;

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

import com.example.proyfragmentmodal.R;
import com.example.proyfragmentmodal.general.ChatGeneral;

import java.util.ArrayList;

public class ListAdapterScore extends
        BaseAdapter {

    private Context context;
    //private ArrayList<ListItem> items;
    private ArrayList<String> listaElementos;

    private ArrayList<String> filteredData;
    private int idListItemVista;

    public ListAdapterScore() {
    }

    public ListAdapterScore(Context context, ArrayList<String> items) {
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

        // ListItem item = items.get(position);
        String item = listaElementos.get(position);

        TextView textView = convertView.findViewById(R.id.txt_titulo_asig);
        textView.setText(item);
        //textView.setText(item.getText());

        //ImageView imageView = convertView.findViewById(R.id.image_view);
        //imageView.setImageResource(item.getImage());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return convertView;
    }




}
