package com.example.proyfragmentmodal.estudiante.septimo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.proyfragmentmodal.R;


public class ASeleccioneLeyenda7 extends Fragment {

    public ASeleccioneLeyenda7() {
    }

    public static ASeleccioneLeyenda7 newInstance(String param1, String param2) {
        ASeleccioneLeyenda7 fragment = new ASeleccioneLeyenda7();
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
        return inflater.inflate(R.layout.fragment_a_seleccione_leyenda7, container, false);
    }
}