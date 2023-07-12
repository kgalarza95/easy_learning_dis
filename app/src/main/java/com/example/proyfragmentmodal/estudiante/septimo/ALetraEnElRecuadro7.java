package com.example.proyfragmentmodal.estudiante.septimo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.proyfragmentmodal.R;


public class ALetraEnElRecuadro7 extends Fragment {


    public ALetraEnElRecuadro7() {
    }

    public static ALetraEnElRecuadro7 newInstance(String param1, String param2) {
        ALetraEnElRecuadro7 fragment = new ALetraEnElRecuadro7();
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
        return inflater.inflate(R.layout.fragment_a_letra_en_el_recuadro7, container, false);
    }
}