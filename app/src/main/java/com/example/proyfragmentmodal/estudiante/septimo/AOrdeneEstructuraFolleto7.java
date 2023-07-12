package com.example.proyfragmentmodal.estudiante.septimo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.proyfragmentmodal.R;


public class AOrdeneEstructuraFolleto7 extends Fragment {

    public AOrdeneEstructuraFolleto7() {
    }

    public static AOrdeneEstructuraFolleto7 newInstance(String param1, String param2) {
        AOrdeneEstructuraFolleto7 fragment = new AOrdeneEstructuraFolleto7();
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
        return inflater.inflate(R.layout.fragment_a_ordene_estructura_folleto7, container, false);
    }
}