package com.example.proyfragmentmodal.estudiante.quinto;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.proyfragmentmodal.R;


public class ArrastrarSaltar5to extends Fragment {

    public ArrastrarSaltar5to() {
    }

    public static ArrastrarSaltar5to newInstance(String param1, String param2) {
        ArrastrarSaltar5to fragment = new ArrastrarSaltar5to();
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
        return inflater.inflate(R.layout.fragment_arrastrar_saltar, container, false);
    }
}