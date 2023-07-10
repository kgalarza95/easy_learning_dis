package com.example.proyfragmentmodal.estudiante.quinto;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.proyfragmentmodal.R;


public class Tildes extends Fragment {


View vista;
    public Tildes() {
    }

    public static Tildes newInstance(String param1, String param2) {
        Tildes fragment = new Tildes();
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
        vista=  inflater.inflate(R.layout.fragment_tildes, container, false);


        return vista;
    }
}