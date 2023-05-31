package com.example.proyfragmentmodal.estudiante;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
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

import androidx.fragment.app.Fragment;

import com.android.volley.VolleyError;
import com.example.proyfragmentmodal.R;
import com.example.proyfragmentmodal.dao.IDaoService;
import com.example.proyfragmentmodal.entity.EntityMap;
import com.example.proyfragmentmodal.entity.Respuesta;
import com.example.proyfragmentmodal.adapter.ListAdapterParticipantes;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ParticipantesFragmEstud extends Fragment
        implements IDaoService.DAOCallbackServicio {

    private ListAdapterParticipantes adapter;
    private String opcion;
    private Gson gson = new Gson();
    private View vistaG;
    private List<EntityMap> listaEstudiantes;
    private ProgressDialog progressDialog;

    public ParticipantesFragmEstud() {
    }

    public static ParticipantesFragmEstud newInstance(String param1, String param2) {
        ParticipantesFragmEstud fragment = new ParticipantesFragmEstud();
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
        View vista = inflater.inflate(R.layout.fragment_participantes, container, false);
        vistaG = vista;
        progressDialog = new ProgressDialog(getActivity());

        ArrayList<String> items = new ArrayList<>();
        items.add("Estudent uno");

        ListView listView = vista.findViewById(R.id.lv_lista_participantes_fr);
        adapter = new ListAdapterParticipantes((Context) getActivity(), (ArrayList<String>) items.clone());

        //filtroooooo====================================================================00
        //Filtro filtro = new Filtro(items, adapter);
        listView.setTextFilterEnabled(true);
        listView.setOnTouchListener((v, event) -> {
            listView.clearFocus();
            InputMethodManager in = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            in.hideSoftInputFromWindow(listView.getWindowToken(), 0);
            return false;
        });

        ((EditText) vista.findViewById(R.id.search_field_de_lista)).addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);
                Log.i("=============================================>1. ", "onTextChanged");
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.i("=============================================>2. ", "beforeTextChanged");
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.i("=============================================>3. ", "afterTextChanged");
                Log.i("=============================================>3.s.length() ", String.valueOf(s.length()));
                if (s.length() == 0) {
                    Log.i("=============================================>3.Data ", String.valueOf(opciones));
                    // adapter.resetData((ArrayList<String>) items.clone());
                    //adapter.notifyDataSetChanged();
                    adapter = null;
                    adapter = new ListAdapterParticipantes((Context) getActivity(), (ArrayList<String>) opciones.clone());
                    listView.setAdapter(adapter);
                }
            }
        });
        //filtroooooo================================================

        listView.setAdapter(adapter);

        consultarEstudiantes();
        return vista;
    }

    public void consultarEstudiantes() {
        progressDialog.setMessage("Cargando...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        opcion = "CT";//CONSULTA MENSAJES

        Map<String, String> params = new HashMap<>();
        params.put("opcion", opcion);
        params.put("id_persona1", "");
        params.put("id_persona2", "");

        IDaoService dao = new IDaoService(getActivity());
        dao.apiMensajes(params, ParticipantesFragmEstud.this);
    }

    public void llenarEsudiantes(ArrayList<String> items) {
/*        items.add("Estudent uno");
        items.add("Estudent dos");
        items.add("Estudent tres");*/

        ListView listView = vistaG.findViewById(R.id.lv_lista_participantes_fr);
        adapter = new ListAdapterParticipantes((Context) getActivity(), (ArrayList<String>) items.clone());

        //filtroooooo====================================================================00
        //Filtro filtro = new Filtro(items, adapter);
        listView.setTextFilterEnabled(true);
        listView.setOnTouchListener((v, event) -> {
            listView.clearFocus();
            InputMethodManager in = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            in.hideSoftInputFromWindow(listView.getWindowToken(), 0);
            return false;
        });

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
                if (opcion.equals("CE") || opcion.equals("CT")) {
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