package com.example.proyfragmentmodal.estudiante;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.proyfragmentmodal.R;


public class DibujoGame extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DibujoGame() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DibujoGame.
     */
    // TODO: Rename and change types and number of parameters
    public static DibujoGame newInstance(String param1, String param2) {
        DibujoGame fragment = new DibujoGame();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    View vista;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        vista = inflater.inflate(R.layout.fragment_dibujo_game, container, false);
        CustomView customView = vista.findViewById(R.id.customView);
        Button clearButton = vista.findViewById(R.id.clearButton);
        customView.setClearButton(clearButton);
        return vista;
    }
}