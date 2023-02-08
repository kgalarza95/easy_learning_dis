package com.example.proyfragmentmodal;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class mini_pantalla_dos extends Fragment {



    public mini_pantalla_dos() {
        // Required empty public constructor
    }

    public static mini_pantalla_dos newInstance(String param1, String param2) {
        mini_pantalla_dos fragment = new mini_pantalla_dos();
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

        return inflater.inflate(R.layout.fragment_mini_pantalla_dos, container, false);
    }
}