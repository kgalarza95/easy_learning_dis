package com.example.proyfragmentmodal;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.proyfragmentmodal.util.GridViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UsoDeLaH#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UsoDeLaH extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;


    private GridView gridView;
    private List<Integer> imagenes;
    private List<String> opcionesSpinner;
    private List<String> listNombres;



    public UsoDeLaH() {
        // Required empty public constructor
    }

    public static UsoDeLaH newInstance(String param1, String param2) {
        UsoDeLaH fragment = new UsoDeLaH();
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
        vista = inflater.inflate(R.layout.fragment_uso_de_la_h, container, false);

        listNombres = new ArrayList<>();

        gridView = vista.findViewById(R.id.grid_view);

        // Crea las listas de imágenes y opciones para el spinner
        imagenes = new ArrayList<>();
        imagenes.add(R.drawable.huevo);
        imagenes.add(R.drawable.arbol);
        imagenes.add(R.drawable.esponja);

        listNombres.add("uevo");
        listNombres.add("arbol");
        listNombres.add("esponja");

        imagenes.add(R.drawable.isla);
        imagenes.add(R.drawable.hormiga);
        imagenes.add(R.drawable.avion);

        listNombres.add("isla");
        listNombres.add("ormiga");
        listNombres.add("avión");

        imagenes.add(R.drawable.ordenador);
        imagenes.add(R.drawable.edificio);
        imagenes.add(R.drawable.unicornio);

        listNombres.add("ordenador");
        listNombres.add("ospital");
        listNombres.add("unicornio");

        imagenes.add(R.drawable.hueso);
        imagenes.add(R.drawable.arana);
        imagenes.add(R.drawable.hermana);

        listNombres.add("ueso");
        listNombres.add("araña");
        listNombres.add("ermana");

        imagenes.add(R.drawable.humo);
        imagenes.add(R.drawable.hombre);
        imagenes.add(R.drawable.cesped);//hierba

        listNombres.add("umo");
        listNombres.add("ombre");
        listNombres.add("ierba");

        imagenes.add(R.drawable.hoja);
        imagenes.add(R.drawable.init);//entrada
        imagenes.add(R.drawable.perseguir);//ultimo

        listNombres.add("oja");
        listNombres.add("entrada");
        listNombres.add("último");

        // Agrega todas las imágenes que quieras mostrar

        opcionesSpinner = new ArrayList<>();
        opcionesSpinner.add("H");
        opcionesSpinner.add("-");
        // Agrega todas las opciones que quieras mostrar en el spinner

        // Crea un adaptador personalizado para la cuadrícula
        GridViewAdapter adapter = new GridViewAdapter(getActivity(), imagenes, opcionesSpinner, listNombres);
        gridView.setAdapter(adapter);

        return vista;
    }
}