package com.example.proyfragmentmodal.estudiante.quinto;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.proyfragmentmodal.R;


public class PuntoComa extends Fragment {


    public PuntoComa() {
    }

    public static PuntoComa newInstance(String param1, String param2) {
        PuntoComa fragment = new PuntoComa();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    View vista;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_punto_coma, container, false);


        return vista;
    }
}