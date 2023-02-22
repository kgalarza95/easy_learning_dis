package com.example.proyfragmentmodal.estudiante;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.proyfragmentmodal.R;

public class JuegoUno extends Fragment {

    public JuegoUno() {
        // Required empty public constructor
    }

    public static JuegoUno newInstance(String param1, String param2) {
        JuegoUno fragment = new JuegoUno();
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
        vista = inflater.inflate(R.layout.fragment_juego_uno, container, false);

        return vista;
    }
}