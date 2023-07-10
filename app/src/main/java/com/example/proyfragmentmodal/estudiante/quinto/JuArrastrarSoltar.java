package com.example.proyfragmentmodal.estudiante.quinto;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.VolleyError;
import com.example.proyfragmentmodal.R;
import com.example.proyfragmentmodal.dao.IDaoService;


public class JuArrastrarSoltar extends Fragment
        implements IDaoService.DAOCallbackServicio {


    View vista;


    public JuArrastrarSoltar() {
    }

    public static JuArrastrarSoltar newInstance(String param1, String param2) {
        JuArrastrarSoltar fragment = new JuArrastrarSoltar();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_ju_arrastrar_soltar, container, false);


        return vista;
    }

    @Override
    public void onSuccess(String response) {

    }

    @Override
    public void onError(VolleyError error) {

    }
}