package com.example.proyfragmentmodal.profesor;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.proyfragmentmodal.R;


public class AsignacionesProfesor extends Fragment {


    public AsignacionesProfesor() {
        // Required empty public constructor
    }

    public static AsignacionesProfesor newInstance(String param1, String param2) {
        AsignacionesProfesor fragment = new AsignacionesProfesor();
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
        View vista = inflater.inflate(R.layout.fragment_asignaciones_profesor, container, false);

        return vista ;
    }
}