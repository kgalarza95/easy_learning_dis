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
import com.example.proyfragmentmodal.util.ListAdapterIconText;

import java.util.ArrayList;
import java.util.List;


public class MaterialEstudio extends Fragment {


    public MaterialEstudio() {
        // Required empty public constructor
    }

    public static MaterialEstudio newInstance(String param1, String param2) {
        MaterialEstudio fragment = new MaterialEstudio();
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
        View vista = inflater.inflate(R.layout.fragment_material_estudio, container, false);
        //vista.findViewById(R.id.rv_list_material_estudio);

        init(vista);
        return vista;
    }

    public void init(View vista) {
        List<String>  litUsuarios = new ArrayList<>();
        int i = 1;
        litUsuarios.add("nobre pdf_"+(i++)+".pdf");
        litUsuarios.add("nobre pdf_"+(i++)+".pdf");
        litUsuarios.add("nobre pdf_"+(i++)+".pdf");
        litUsuarios.add("nobre pdf_"+(i++)+".pdf");
        litUsuarios.add("nobre pdf_"+(i++)+".pdf");
        litUsuarios.add("nobre pdf_"+(i++)+".pdf");
        litUsuarios.add("nobre pdf_"+(i++)+".pdf");
        litUsuarios.add("nobre pdf_"+(i++)+".pdf");
        litUsuarios.add("nobre pdf_"+(i++)+".pdf");
        litUsuarios.add("nobre pdf_"+(i++)+".pdf");



        ListAdapterIconText listAdapter = new ListAdapterIconText(litUsuarios, getActivity());
        RecyclerView recyclerView = (RecyclerView) vista.findViewById(R.id.rv_list_material_estudio);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(listAdapter);
    }
}