package com.example.proyfragmentmodal.estudiante;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.proyfragmentmodal.R;

public class MenuJuego extends Fragment {

    public MenuJuego() {
        // Required empty public constructor
    }

    public static MenuJuego newInstance(String param1, String param2) {
        MenuJuego fragment = new MenuJuego();
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
        vista = inflater.inflate(R.layout.fragment_menu_juego, container, false);

        return vista;
    }
}