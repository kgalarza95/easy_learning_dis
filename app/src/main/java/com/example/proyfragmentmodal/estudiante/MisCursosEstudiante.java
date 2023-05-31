package com.example.proyfragmentmodal.estudiante;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.proyfragmentmodal.R;
import com.example.proyfragmentmodal.dao.IDaoService;
import com.example.proyfragmentmodal.entity.EntityMap;
import com.example.proyfragmentmodal.entity.Respuesta;
import com.example.proyfragmentmodal.util.GlobalAplicacion;
import com.example.proyfragmentmodal.adapter.ListAdapterMisCursos;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MisCursosEstudiante extends Fragment
        implements IDaoService.DAOCallbackServicio {


    private RecyclerView recyclerView;
    private String opcion;
    private Gson gson = new Gson();

    public MisCursosEstudiante() {
        // Required empty public constructor
    }

    public static MisCursosEstudiante newInstance(String param1, String param2) {
        MisCursosEstudiante fragment = new MisCursosEstudiante();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    View vista;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_mis_cursos_estudiante, container, false);

        requestCursos();

        return vista;
    }


    public void requestCursos() {
        GlobalAplicacion global = new GlobalAplicacion();
        opcion = "CX";

        Map<String, String> params = new HashMap<>();
        params.put("opcion", opcion);
        //params.put("id_profesor", String.valueOf(1));
        params.put("id_profesor", String.valueOf(global.getGlobalIdUsuario()));
        params.put("nombre", "");
        params.put("descripcion", "");
        params.put("anio", "");
        params.put("estado", "S");

        Log.i("================================================================================= ", "");
        Log.i("Parámetros enviados ", String.valueOf(params));
        IDaoService dao = new IDaoService(getActivity());
        dao.crudCursosProf(params, MisCursosEstudiante.this);
    }

    public void init(List<EntityMap> listaCursos) {
        ListAdapterMisCursos listAdapter = null;
       // listAdapter = new ListAdapterMisCursos(listaCursos, getActivity(), listAdapter);
        listAdapter = new ListAdapterMisCursos(listaCursos, getActivity());
        RecyclerView recyclerView = (RecyclerView) vista.findViewById(R.id.rv_list_cursos);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(listAdapter);
    }


    @Override
    public void onSuccess(String response) {
        Log.d("===========================================================  ", "");
        Log.d("Respuesta:  ", response);

        try {
            Respuesta data = gson.fromJson(response, Respuesta.class);
            if (data.getCodResponse().equals("00")) {
                if (opcion.equals("CX")) {
                    //  List<String> listFilas = (List<String>) data.getData();
                    String json = gson.toJson(data.getData());
                    Type listType = new TypeToken<List<EntityMap>>() {
                    }.getType();
                    List<EntityMap> listaCursos = gson.fromJson(json, listType);
                    Log.d("Respuesta:  ", String.valueOf(listaCursos));
                    Log.d("Respuesta:  ", listaCursos.get(0).getNOMBRE());
                    /*  Log.d("Respuesta:  ", listaCursos.get(1).getNOMBRE());*/
                    init(listaCursos);

                }
            } else {
                Toast.makeText(getActivity(), data.getMsjResponse(), Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onError(VolleyError error) {
        Log.d("Error:  ", error.toString());
        Toast.makeText(getActivity(), "Error: " + error, Toast.LENGTH_SHORT).show();
    }
}