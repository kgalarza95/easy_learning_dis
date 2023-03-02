package com.example.proyfragmentmodal.estudiante;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.proyfragmentmodal.R;

import java.util.ArrayList;
import java.util.List;


public class DibujoGame extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public DibujoGame() {
    }


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
    Button btnNext;
    List<Integer> listaLineas = new ArrayList<>();
    int i;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        i = 0;
        listaLineas.add(R.drawable.linea_f);
        listaLineas.add(R.drawable.linea_e);
        listaLineas.add(R.drawable.linea_a);
        listaLineas.add(R.drawable.linea_c);
        listaLineas.add(R.drawable.linea_d);
        listaLineas.add(R.drawable.linea_y);
        listaLineas.add(R.drawable.linea_i);
        listaLineas.add(R.drawable.linea_p);
        listaLineas.add(R.drawable.linea_q);
        listaLineas.add(R.drawable.linea_r);
        listaLineas.add(R.drawable.linea_s);
        listaLineas.add(R.drawable.linea_t);
        listaLineas.add(R.drawable.linea_u);
        listaLineas.add(R.drawable.linea_w);
        listaLineas.add(R.drawable.linea_x);

        vista = inflater.inflate(R.layout.fragment_dibujo_game, container, false);
        CustomView customView = vista.findViewById(R.id.customView);
        Button clearButton = vista.findViewById(R.id.clearButton);
        customView.setClearButton(clearButton);

        btnNext = vista.findViewById(R.id.btn_next);
        FrameLayout fragmentVentana = vista.findViewById(R.id.vista_draw);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // fragmentVentana.setBackgroundResource(R.drawable.linea_d);
                fragmentVentana.setBackgroundResource(listaLineas.get(i));
                i++;
                customView.clearLinea();
                Toast.makeText(getActivity(), "Enviado para calificación.", Toast.LENGTH_SHORT).show();

                if (i == listaLineas.size()) {
                    i = 0;
                    Toast.makeText(getActivity(), "Fin del juego, vuelve a intentarlo.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return vista;
    }
}