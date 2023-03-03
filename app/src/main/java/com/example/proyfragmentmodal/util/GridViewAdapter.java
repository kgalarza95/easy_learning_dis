package com.example.proyfragmentmodal.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.proyfragmentmodal.R;

import java.util.List;

public class GridViewAdapter extends BaseAdapter {

    private Context context;
    private List<Integer> imagenes;
    private List<String> opcionesSpinner;
    List<String> listNombres;
    private LayoutInflater inflater;

    public GridViewAdapter(Context context, List<Integer> imagenes, List<String> opcionesSpinner, List<String> listNombres) {

        this.context = context;
        this.imagenes = imagenes;
        this.opcionesSpinner = opcionesSpinner;
        this.listNombres = listNombres;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return imagenes.size();
    }

    @Override
    public Object getItem(int position) {
        return imagenes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.grid_item, parent, false);
            holder = new ViewHolder();
            holder.imageView = convertView.findViewById(R.id.image_view);
            holder.spinner = convertView.findViewById(R.id.spinner);
            holder.textView = convertView.findViewById(R.id.txt_palabra_h);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // Configura la imagen
        int imagenId = imagenes.get(position);
        holder.imageView.setImageResource(imagenId);

        String palabra = listNombres.get(position);
        holder.textView.setText(palabra);
        // Configura el Spinner
        //ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, opcionesSpinner);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, opcionesSpinner);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        holder.spinner.setAdapter(spinnerAdapter);

        return convertView;
    }

    static class ViewHolder {
        ImageView imageView;
        Spinner spinner;
        TextView textView;
    }
}
