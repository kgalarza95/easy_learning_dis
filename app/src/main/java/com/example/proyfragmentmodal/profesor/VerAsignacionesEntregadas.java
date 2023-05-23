package com.example.proyfragmentmodal.profesor;

import android.app.ProgressDialog;
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
import com.example.proyfragmentmodal.util.ListAdapterAsiganciones;
import com.example.proyfragmentmodal.util.ListAdapterIconText;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class VerAsignacionesEntregadas extends Fragment
        implements IDaoService.DAOCallbackServicio {

    private String opcion = "";
    private Gson gson = new Gson();
    private ProgressDialog progressDialog;
    private int origenLlamada = 0;
    private RecyclerView recyclerView;
    private View vista;

    public VerAsignacionesEntregadas() {
    }

    public static VerAsignacionesEntregadas newInstance(String param1, String param2) {
        VerAsignacionesEntregadas fragment = new VerAsignacionesEntregadas();
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
        vista = inflater.inflate(R.layout.fragment_ver_asignaciones, container, false);

        try {

            progressDialog = new ProgressDialog(getActivity());


            recyclerView = (RecyclerView) vista.findViewById(R.id.rv_lista);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

            consultarTareasPorEstudiante();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return vista;
    }


    public void init(View vista) {
        try {
            List<String> litUsuarios = new ArrayList<>();
            int i = 1;
            litUsuarios.add("nobre pdf_" + (i++) + ".pdf");
            litUsuarios.add("nobre pdf_" + (i++) + ".pdf");
            litUsuarios.add("nobre pdf_" + (i++) + ".pdf");
            litUsuarios.add("nobre pdf_" + (i++) + ".pdf");
            litUsuarios.add("nobre pdf_" + (i++) + ".pdf");
            litUsuarios.add("nobre pdf_" + (i++) + ".pdf");
            litUsuarios.add("nobre pdf_" + (i++) + ".pdf");
            litUsuarios.add("nobre pdf_" + (i++) + ".pdf");
            litUsuarios.add("nobre pdf_" + (i++) + ".pdf");
            litUsuarios.add("nobre pdf_" + (i++) + ".pdf");


            ListAdapterIconText listAdapter = new ListAdapterIconText(litUsuarios, getActivity());
            RecyclerView recyclerView = (RecyclerView) vista.findViewById(R.id.rv_list_material_estudio);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(listAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void consultarTareasPorEstudiante() {
        try {
            Map<String, String> params = new HashMap<>();
            opcion = "CNA";
            params.put("opcion", opcion);
            params.put("xxx", "");
            Log.i("Parametros envío:", String.valueOf(params));
            IDaoService dao = new IDaoService(getActivity());
            dao.crudAsignacion(params, VerAsignacionesEntregadas.this);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void initConsulta(List<EntityMap> litUsuarios) {
        try {
            ListAdapterAsiganciones listAdapter = new ListAdapterAsiganciones(litUsuarios, getActivity());
            recyclerView.setAdapter(listAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public void onSuccess(String response) {
        try {
            progressDialog.dismiss();
            Log.i("response ============>:  ", String.valueOf(response));

            Log.d("===========================================================  ", "");
            Log.d("Respuesta:  ", response);
            Respuesta data = gson.fromJson(response, Respuesta.class);

            if (data.getCodResponse().equals("00")) {
                if (opcion.equals("CNA")) {
                    //  List<String> listFilas = (List<String>) data.getData();
                    String json = gson.toJson(data.getData());
                    Type listType = new TypeToken<List<EntityMap>>() {
                    }.getType();
                    List<EntityMap> listaCursos = gson.fromJson(json, listType);
                    Log.d("Respuesta:  ", String.valueOf(listaCursos));

                    initConsulta(listaCursos);

                } else if (opcion.equals("IN")) {
                    consultarTareasPorEstudiante();
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
            progressDialog.dismiss();
            Toast.makeText(getActivity(), String.valueOf(error), Toast.LENGTH_SHORT).show();
            Log.i("error ============>:  ", String.valueOf(error));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}