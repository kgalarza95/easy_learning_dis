package com.example.proyfragmentmodal.estudiante;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.proyfragmentmodal.R;
import com.example.proyfragmentmodal.util.ListAdapter;

import java.util.ArrayList;
import java.util.List;


public class Foro extends Fragment {

    // private FragmentHomeBinding binding;
    List<String> litUsuarios;

    public Foro() {
        // Required empty public constructor
    }

    public static Foro newInstance(String param1, String param2) {
        Foro fragment = new Foro();
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
        View vista = inflater.inflate(R.layout.fragment_foro, container, false);
        vista.findViewById(R.id.rv_list_foro);

        init(vista);

        return vista;
    }

    public void init(View vista) {
        litUsuarios = new ArrayList<>();
        litUsuarios.add("nom estudiante");
        litUsuarios.add("nom estudiante2");
        litUsuarios.add("nom estudiante3");
        litUsuarios.add("nom estudiante4");
        litUsuarios.add("nom estudiante5");
        litUsuarios.add("nom estudiante6");
        litUsuarios.add("nom estudiante7");


        ListAdapter listAdapter = new ListAdapter(litUsuarios, getActivity());
        //RecyclerView recyclerView = findViewById(R.id.mySegmentoRecyVi);
        RecyclerView recyclerView = (RecyclerView) vista.findViewById(R.id.rv_list_foro);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(listAdapter);
    }
}