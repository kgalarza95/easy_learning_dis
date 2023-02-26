package com.example.proyfragmentmodal.util;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.proyfragmentmodal.entity.EntityMap;

import java.util.List;

public class CursoArrayAdapter extends ArrayAdapter<EntityMap> {

    private List<EntityMap> cursos;

    public CursoArrayAdapter(Context context, List<EntityMap> cursos) {
        super(context, android.R.layout.simple_spinner_item, cursos);
        this.cursos = cursos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView = (TextView) super.getView(position, convertView, parent);
        textView.setText(cursos.get(position).getNOMBRE());
        return textView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        TextView textView = (TextView) super.getDropDownView(position, convertView, parent);
        textView.setText(cursos.get(position).getNOMBRE());
        return textView;
    }

    @Override
    public EntityMap getItem(int position) {
        return cursos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return Long.parseLong(cursos.get(position).getID());
    }
}

