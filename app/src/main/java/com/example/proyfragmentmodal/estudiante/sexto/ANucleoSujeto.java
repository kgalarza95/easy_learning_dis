package com.example.proyfragmentmodal.estudiante.sexto;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.proyfragmentmodal.R;


public class ANucleoSujeto extends Fragment {

    public ANucleoSujeto() {
    }

    public static ANucleoSujeto newInstance(String param1, String param2) {
        ANucleoSujeto fragment = new ANucleoSujeto();
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

        return inflater.inflate(R.layout.fragment_a_nucleo_sujeto, container, false);
    }
}