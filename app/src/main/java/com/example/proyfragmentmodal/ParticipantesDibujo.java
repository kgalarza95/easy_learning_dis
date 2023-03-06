package com.example.proyfragmentmodal;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.proyfragmentmodal.dao.IDaoService;
import com.example.proyfragmentmodal.entity.EntityMap;
import com.example.proyfragmentmodal.entity.Respuesta;
import com.example.proyfragmentmodal.profesor.ParticipantesFragm;
import com.example.proyfragmentmodal.util.ListAdapterParticipantes;
import com.example.proyfragmentmodal.util.ListAdapterParticipantesDibujo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ParticipantesDibujo extends Fragment
        implements IDaoService.DAOCallbackServicio {

    private ListAdapterParticipantesDibujo adapter;
    private String opcion;
    private Gson gson = new Gson();
    private View vistaG;
    private List<EntityMap> listaEstudiantes;
    private ProgressDialog progressDialog;


    public ParticipantesDibujo() {
    }


    public static ParticipantesDibujo newInstance(String param1, String param2) {
        ParticipantesDibujo fragment = new ParticipantesDibujo();
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

        vista = inflater.inflate(R.layout.fragment_participantes_dibujo, container, false);
        vistaG = vista;
        progressDialog = new ProgressDialog(getActivity());

        ArrayList<String> items = new ArrayList<>();
        items.add("Estudent uno");
        items.add("Estudent dos");
        items.add("Estudent tres");

        ListView listView = vista.findViewById(R.id.lv_lista_participantes_xx);
        adapter = new ListAdapterParticipantesDibujo((Context) getActivity(), (ArrayList<String>) items.clone());

        listView.setAdapter(adapter);

        consultarEstudiantes();
        return vista;
    }

    public void consultarEstudiantes() {
        progressDialog.setMessage("Cargando...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        opcion = "CE";//CONSULTA MENSAJES

        Map<String, String> params = new HashMap<>();
        params.put("opcion", opcion);
        params.put("id_persona1", "");
        params.put("id_persona2", "");

        IDaoService dao = new IDaoService(getActivity());
        dao.apiMensajes(params, ParticipantesDibujo.this);
    }

    public void llenarEsudiantes(ArrayList<String> items) {

        ListView listView = vistaG.findViewById(R.id.lv_lista_participantes_xx);
        adapter = new ListAdapterParticipantesDibujo((Context) getActivity(), (ArrayList<String>) items.clone());

        listView.setAdapter(adapter);
    }

    ArrayList<String> opciones;

    @Override
    public void onSuccess(String response) {
        //apaagar barra de progreso
        progressDialog.dismiss();
        try {
            Log.d("===========================================================  ", "");
            Log.d("Respuesta:  ", response);
            Respuesta data = gson.fromJson(response, Respuesta.class);
            if (data.getCodResponse().equals("00")) {
                if (opcion.equals("CE")) {
                    String json = gson.toJson(data.getData());
                    Type listType = new TypeToken<List<EntityMap>>() {
                    }.getType();
                    listaEstudiantes = gson.fromJson(json, listType);
                    Log.d("Respuesta:  ", String.valueOf(listaEstudiantes));
                    Log.d("Respuesta:  ", listaEstudiantes.get(0).getNOMBRES());

                    opciones = new ArrayList<>();

                    for (EntityMap obj : listaEstudiantes) {
                        opciones.add(obj.getID() + " " + obj.getNOMBRES() + " " + obj.getAPELLIDOS());
                    }

                    llenarEsudiantes(opciones);
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