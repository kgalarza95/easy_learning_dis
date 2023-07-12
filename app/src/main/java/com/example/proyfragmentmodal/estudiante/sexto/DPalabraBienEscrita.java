package com.example.proyfragmentmodal.estudiante.sexto;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.proyfragmentmodal.R;


public class DPalabraBienEscrita extends Fragment {

    public DPalabraBienEscrita() {
    }

    public static DPalabraBienEscrita newInstance(String param1, String param2) {
        DPalabraBienEscrita fragment = new DPalabraBienEscrita();
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
        return inflater.inflate(R.layout.fragment_d_palabra_bien_escrita, container, false);
    }
}