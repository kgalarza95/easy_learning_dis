package com.example.proyfragmentmodal.estudiante;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.proyfragmentmodal.R;
import com.example.proyfragmentmodal.dao.IDaoService;
import com.example.proyfragmentmodal.entity.EntityMap;
import com.example.proyfragmentmodal.entity.Respuesta;
import com.example.proyfragmentmodal.adapter.ListAdapterScore;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HistorialAcademico extends Fragment
        implements IDaoService.DAOCallbackServicio {

    private ListAdapterScore adapter;
    private String opcion;
    private Gson gson = new Gson();
    private View vistaG;
    private List<EntityMap> listaEstudiantes;
    private ProgressDialog progressDialog;


    public HistorialAcademico() {
    }

    public static HistorialAcademico newInstance(String param1, String param2) {
        HistorialAcademico fragment = new HistorialAcademico();
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
        View vista = inflater.inflate(R.layout.fragment_historial_academico, container, false);
        vistaG = vista;
        progressDialog = new ProgressDialog(getActivity());

        ListView listView = vista.findViewById(R.id.lv_lista);
        consultar();

        return vista;
    }

    public void consultar() {
        progressDialog.setMessage("Cargando...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        opcion = "SC";//CONSULTA MENSAJES

        Map<String, String> params = new HashMap<>();
        params.put("opcion", opcion);

        IDaoService dao = new IDaoService(getActivity());
        dao.apiJuegos(params, HistorialAcademico.this);
    }

    public void llenarInf(ArrayList<String> items) {

        ListView listView = vistaG.findViewById(R.id.lv_lista);
        adapter = new ListAdapterScore((Context) getActivity(), (ArrayList<String>) items.clone());

        listView.setAdapter(adapter);
    }


    private ArrayList<String> opciones;

    @Override
    public void onSuccess(String response) {
        //apaagar barra de progreso
        progressDialog.dismiss();
        try {
            Log.d("===========================================================  ", "");
            Log.d("Respuesta:  ", response);
            Respuesta data = gson.fromJson(response, Respuesta.class);
            if (data.getCodResponse().equals("00")) {
                if (opcion.equals("SC")) {
                    String json = gson.toJson(data.getData());
                    Type listType = new TypeToken<List<EntityMap>>() {
                    }.getType();
                    listaEstudiantes = gson.fromJson(json, listType);
                    Log.d("Respuesta:  ", String.valueOf(listaEstudiantes));

                    opciones = new ArrayList<>();

                    for (EntityMap obj : listaEstudiantes) {
                        String datosPer = "Estud: " + obj.getNOMBRES() + " - puntos: " + obj.getSCORE() + "\n";
                        String juego = obj.getNOMBRE_JUEGO() + "\n";
                        opciones.add(datosPer + juego);
                    }

                    llenarInf(opciones);
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
        try {
            Log.d("Error:  ", error.toString());
            Toast.makeText(getActivity(), "Error: " + error, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}