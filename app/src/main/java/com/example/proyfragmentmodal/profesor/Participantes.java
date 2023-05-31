package com.example.proyfragmentmodal.profesor;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.proyfragmentmodal.R;
import com.example.proyfragmentmodal.dao.IDaoService;
import com.example.proyfragmentmodal.entity.EntityMap;
import com.example.proyfragmentmodal.entity.Respuesta;
import com.example.proyfragmentmodal.adapter.ListAdapterParticipantes;
import com.example.proyfragmentmodal.adapter.ListAdapterTareas;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Participantes extends AppCompatActivity
        implements IDaoService.DAOCallbackServicio {

    private FloatingActionButton fab;
    private RecyclerView recyclerView;
    private String opcion;
    private Gson gson = new Gson();
    private Spinner spinner;
    private List<EntityMap> listaParticipantes;
    private View dialogView;
    private ArrayList<String> opciones;
    private ArrayAdapter<String> adapterSpinner;
    private String idCursoPertenece;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participantes);

     /*   ArrayList<ListItem> items = new ArrayList<>();
        items.add(new ListItem("Item 1", R.drawable.image1));
        items.add(new ListItem("Item 2", R.drawable.image2));
        items.add(new ListItem("Item 3", R.drawable.image3));
        */
        /*ArrayList<String> items = new ArrayList<>();
        items.add("Estudent uno");
        items.add("Estudent dos");
        items.add("Estudent tres");*/

       /* ListView listView = findViewById(R.id.lv_lista_participantes);
        ListAdapterParticipantes adapter = new ListAdapterParticipantes(this, items);
        listView.setAdapter(adapter);*/

        fab = findViewById(R.id.fab_new_estudiante);

        /*// Establece el layout que contiene el spinner.
        LayoutInflater inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.dialog_add_participantes, null);

        // Obtén el spinner del layout y establece el adaptador.
        spinner = (Spinner) dialogView.findViewById(R.id.spinner_participantes);*/


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertaDialog();
            }
        });

        // Obtener el Intent que inició esta actividad y extraer el dato de tipo String
        Intent intent = getIntent();
        idCursoPertenece = intent.getStringExtra("idCurso");
        String origenCall = intent.getStringExtra("origenCall");

        Log.d("idCursoPertenece===============================================================> ", idCursoPertenece);
        Log.d("idCursoPertenece: ", idCursoPertenece);

        if (origenCall.equalsIgnoreCase("prof")) {
            consultarParticipantesDisponibles();
            fab.setVisibility(View.VISIBLE);
        } else {
            consultarTareasPorCurso();
            fab.setVisibility(View.GONE);
        }
    }


    AlertDialog.Builder builder;

    public void alertaDialog() {
        //consultarParticipantesDisponibles();
        try {
            // Crea un adaptador de ArrayAdapter para el spinner.
            adapterSpinner =
                    new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opciones);

            // Establece el layout para mostrar las opciones en el spinner.
            adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


            // Crea el diálogo utilizando un objeto AlertDialog.Builder.
            builder = new AlertDialog.Builder(this);
            builder.setTitle("Selecciona un Participante");

            // Establece el layout que contiene el spinner.
            LayoutInflater inflater = getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.dialog_add_participantes, null);

            // Obtén el spinner del layout y establece el adaptador.
            spinner = (Spinner) dialogView.findViewById(R.id.spinner_participantes);
            spinner.setAdapter(adapterSpinner);

            builder.setView(dialogView)
                    .setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            try {
                                String valorSpinner = (String) spinner.getSelectedItem();
                                EntityMap participante = null;
                                for (EntityMap obj : listaParticipantes) {
                                    //obj.getNOMBRES() + " " + obj.getAPELLIDOS()
                                    String nombres = obj.getNOMBRES() + " " + obj.getAPELLIDOS();
                                    if (nombres.equals(valorSpinner)) {
                                        participante = obj;
                                        break;
                                    }
                                }


                                //guardar objeto
                                opcion = "ICE";//CONSULTA PARTICIPANTES

                                Map<String, String> params = new HashMap<>();
                                params.put("opcion", opcion);
                                params.put("id_profesor", "");
                                params.put("nombre", "");
                                params.put("descripcion", "");
                                params.put("anio", "");
                                params.put("estado", "S");

                                params.put("id_curso", idCursoPertenece);
                                params.put("id_estudiante", participante.getID());

                                IDaoService dao = new IDaoService(Participantes.this);
                                dao.crudCursosProf(params, Participantes.this);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                    })
                    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });


            // Crea y muestra el diálogo.
            AlertDialog dialog = builder.create();
            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    //procesos de solicitud  base

    public void consultarParticipantesDisponibles() {
        opcion = "CP";//CONSULTA PARTICIPANTES

        Map<String, String> params = new HashMap<>();
        params.put("opcion", opcion);
        params.put("id_profesor", "");
        params.put("nombre", "");
        params.put("descripcion", "");
        params.put("anio", "");
        params.put("estado", "S");
        params.put("id_curso", idCursoPertenece);

        IDaoService dao = new IDaoService(Participantes.this);
        dao.crudCursosProf(params, Participantes.this);
    }

    public void consultarParticipantesDelCurso() {
        opcion = "CPC";//CONSULTA PARTICIPANTES

        Map<String, String> params = new HashMap<>();
        params.put("opcion", opcion);
        params.put("id_profesor", "");
        params.put("nombre", "");
        params.put("descripcion", "");
        params.put("anio", "");
        params.put("estado", "S");
        params.put("id_curso", idCursoPertenece);

        IDaoService dao = new IDaoService(Participantes.this);
        dao.crudCursosProf(params, Participantes.this);
    }

    public void consultarTareasPorCurso() {
        opcion = "CT";//CONSULTA TAREAS

        Map<String, String> params = new HashMap<>();
        params.put("opcion", opcion);
        params.put("id_profesor", "");
        params.put("nombre", "");
        params.put("descripcion", "");
        params.put("anio", "");
        params.put("estado", "S");
        params.put("id_curso", idCursoPertenece);

        Log.i("consultarTareasPorCurso | parámetros de envío:  ", String.valueOf(params));
        IDaoService dao = new IDaoService(Participantes.this);
        dao.crudCursosProf(params, Participantes.this);
    }

    @Override
    public void onSuccess(String response) {
        try {
            Log.d("===========================================================  ", "");
            Log.d("Respuesta:  ", response);
            Respuesta data = gson.fromJson(response, Respuesta.class);
            if (data.getCodResponse().equals("00")) {
                if (opcion.equals("CP")) {
                    String json = gson.toJson(data.getData());
                    Type listType = new TypeToken<List<EntityMap>>() {
                    }.getType();
                    listaParticipantes = gson.fromJson(json, listType);
                    Log.d("Respuesta:  ", String.valueOf(listaParticipantes));
                    Log.d("Respuesta:  ", listaParticipantes.get(0).getNOMBRES());

                    opciones = new ArrayList<>();

                    for (EntityMap obj : listaParticipantes) {
                        opciones.add(obj.getNOMBRES() + " " + obj.getAPELLIDOS());
                    }

                /*


                // Crea un adaptador de ArrayAdapter para el spinner.
                adapterSpinner =
                        new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opciones);
                // Establece el layout para mostrar las opciones en el spinner.
                adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapterSpinner);

                */
                    consultarParticipantesDelCurso();

                } else if (opcion.equals("ICE")) {
                    consultarParticipantesDisponibles();
                    // consultarParticipantesDelCurso();
                } else if (opcion.equals("CPC")) {
                    String json = gson.toJson(data.getData());
                    Type listType = new TypeToken<List<EntityMap>>() {
                    }.getType();
                    List<EntityMap> listaPartic = gson.fromJson(json, listType);
                    Log.d("Respuesta:  ", String.valueOf(listaPartic));

                    ArrayList<String> items = new ArrayList<>();

                    for (EntityMap obj : listaPartic) {
                        items.add(obj.getNOMBRES() + " " + obj.getAPELLIDOS());
                    }

                    ListView listView = findViewById(R.id.lv_lista_participantes);
                    ListAdapterParticipantes adapter = new ListAdapterParticipantes(this, items);
                    listView.setAdapter(adapter);

                } else if (opcion.equals("CT")) {
                    String json = gson.toJson(data.getData());
                    Type listType = new TypeToken<List<EntityMap>>() {
                    }.getType();
                    List<EntityMap> listaPartic = gson.fromJson(json, listType);
                    Log.d("Respuesta:  ", String.valueOf(listaPartic));

                    ListView listView = findViewById(R.id.lv_lista_participantes);
                    ListAdapterTareas adapter = new ListAdapterTareas(this, listaPartic);
                    listView.setAdapter(adapter);

                }
            } else {
                if (opcion.equals("CP")) {

                    opciones = new ArrayList<>();
                    opciones.add("No hay datos");
                    consultarParticipantesDelCurso();
                } else {
                    Toast.makeText(this, data.getMsjResponse(), Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onError(VolleyError error) {
        try {
            Log.d("Error:  ", error.toString());
            Toast.makeText(this, "Error: " + error, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}