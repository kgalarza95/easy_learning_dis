package com.example.proyfragmentmodal.profesor;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;
import com.example.proyfragmentmodal.R;
import com.example.proyfragmentmodal.dao.IDaoService;
import com.example.proyfragmentmodal.entity.EntityMap;
import com.example.proyfragmentmodal.entity.Respuesta;
import com.example.proyfragmentmodal.util.ListAdapterAsigancionesNoEntregada;
import com.example.proyfragmentmodal.util.ListAdapterIconText;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class VerAsignacionesNoEntregadas extends Fragment
        implements IDaoService.DAOCallbackServicio {

    private String opcion = "";
    private Gson gson = new Gson();
    private ProgressDialog progressDialog;
    private int origenLlamada = 0;
    private RecyclerView recyclerView;
    private View vista;

    public VerAsignacionesNoEntregadas() {
    }

    public static VerAsignacionesNoEntregadas newInstance(String param1, String param2) {
        VerAsignacionesNoEntregadas fragment = new VerAsignacionesNoEntregadas();
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

            consultarAsignaciones();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return vista;
    }


    public void consultarAsignaciones() {
        try {
            Map<String, String> params = new HashMap<>();
            opcion = "CN_ASIGNACIONES";
            params.put("opcion", opcion);
            Log.i("Parametros envío:", String.valueOf(params));
            IDaoService dao = new IDaoService(getActivity());
            dao.crudAsignacionTodas(params, VerAsignacionesNoEntregadas.this);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void llenarAdaptadorView(List<EntityMap> litUsuarios) {
        try {
            ListAdapterAsigancionesNoEntregada listAdapter = new ListAdapterAsigancionesNoEntregada(litUsuarios, getActivity());
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
                if (opcion.equals("CN_ASIGNACIONES")) {
                    //  List<String> listFilas = (List<String>) data.getData();
                    String json = gson.toJson(data.getData());
                    Type listType = new TypeToken<List<EntityMap>>() {
                    }.getType();
                    List<EntityMap> listaCursos = gson.fromJson(json, listType);
                    Log.d("Respuesta:  ", String.valueOf(listaCursos));

                    llenarAdaptadorView(listaCursos);

                } else if (opcion.equals("IN")) {
                    consultarAsignaciones();
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