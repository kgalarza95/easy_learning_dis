package com.example.proyfragmentmodal.estudiante.sexto;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.proyfragmentmodal.R;


public class APuntoSuspensivo extends Fragment {



    public APuntoSuspensivo() {
    }

    public static APuntoSuspensivo newInstance(String param1, String param2) {
        APuntoSuspensivo fragment = new APuntoSuspensivo();
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
        return inflater.inflate(R.layout.fragment_a_punto_suspensivo, container, false);
    }
}