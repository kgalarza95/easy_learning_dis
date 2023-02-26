package com.example.proyfragmentmodal.profesor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.proyfragmentmodal.R;
import com.example.proyfragmentmodal.dao.IDaoService;
import com.example.proyfragmentmodal.entity.EntityMap;
import com.example.proyfragmentmodal.entity.Respuesta;
import com.example.proyfragmentmodal.util.GlobalAplicacion;
import com.example.proyfragmentmodal.util.ListAdapterIconText;
import com.example.proyfragmentmodal.util.ListAdapterMisCursos;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MisCursosProfesor extends AppCompatActivity
        implements IDaoService.DAOCallbackServicio {

    private FloatingActionButton fab;
    private RecyclerView recyclerView;
    private String opcion;
    private Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_cursos_profesor);

        fab = findViewById(R.id.fab);
        recyclerView = findViewById(R.id.rv_list_cursos);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Acciones a realizar cuando se hace clic en el botón flotante
                alertDialogForm();
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0 && fab.getVisibility() == View.VISIBLE) {
                    fab.hide();
                } else if (dy < 0 && fab.getVisibility() != View.VISIBLE) {
                    fab.show();
                }
            }
        });

        //init();

        requestCursos();

    }


    public void init() {
        opcion = "CN";

        Map<String, String> params = new HashMap<>();
        params.put("opcion", opcion);
        params.put("id_profesor", String.valueOf(1));
        //params.put("id_profesor", global.getGlobalUsuario());
        params.put("nombre", "");
        params.put("descripcion", "");
        params.put("anio", "");
        params.put("estado", "S");


        IDaoService dao = new IDaoService(MisCursosProfesor.this);
        dao.crudCursosProf(params, MisCursosProfesor.this);

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


        ListAdapterIconText listAdapter = new ListAdapterIconText(litUsuarios, this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_list_cursos);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);
    }

    public void alertDialogSimple() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MisCursosProfesor.this);
        builder.setTitle("Título del modal")
                .setMessage("Mensaje del modal")
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Haz algo cuando se haga clic en Aceptar
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Haz algo cuando se haga clic en Cancelar
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void alertDialogForm() {
        GlobalAplicacion global = new GlobalAplicacion();


        // Crear una instancia de la vista personalizada
        View customView = getLayoutInflater().inflate(R.layout.dialog_frm_cursos_prof, null);

        // Obtener los objetos EditText en la vista personalizada
        EditText txtNombCurso = customView.findViewById(R.id.txt_nom_curso);
        EditText txtDescripcionCurso = customView.findViewById(R.id.txt_descripcion_curso);
        EditText txtAnioLectivo = customView.findViewById(R.id.txt_anio_lectivo);

        // Crear el AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Crear Nuevo Curso")
                .setView(customView)
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Obtener los valores ingresados en los EditText
                        String value1 = txtNombCurso.getText().toString();
                        String value2 = txtDescripcionCurso.getText().toString();
                        String value3 = txtAnioLectivo.getText().toString();
                        //obtener id del usuario
                        // Mostrar los valores en un Toast
                        /*Toast.makeText(getApplicationContext(), "Valor 1: " + value1 +
                                        ", Valor 2: " + value2 +
                                        ",  global.getGlobalUsuario(): " + global.getGlobalUsuario()
                                , Toast.LENGTH_SHORT).show();*/

                        opcion = "IN";

                        Map<String, String> params = new HashMap<>();
                        params.put("opcion", "IN");
                        //params.put("id_profesor", String.valueOf(1));
                        params.put("id_profesor", String.valueOf(global.getGlobalIdUsuario()));
                        params.put("nombre", txtNombCurso.getText().toString());
                        params.put("descripcion", txtDescripcionCurso.getText().toString());
                        params.put("anio", txtAnioLectivo.getText().toString());
                        params.put("estado", "S");


                        IDaoService dao = new IDaoService(MisCursosProfesor.this);
                        dao.crudCursosProf(params, MisCursosProfesor.this);
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Haz algo cuando se haga clic en Cancelar
                    }
                });

        // Mostrar el AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onSuccess(String response) {
        Log.d("===========================================================  ", "");
        Log.d("Respuesta:  ", response);
        Respuesta data = gson.fromJson(response, Respuesta.class);
        if (data.getCodResponse().equals("00")) {
            if (opcion.equals("IN")) {
                requestCursos();
            } else if (opcion.equals("CN")) {
                //  List<String> listFilas = (List<String>) data.getData();
                String json = gson.toJson(data.getData());
                Type listType = new TypeToken<List<EntityMap>>() {
                }.getType();
                List<EntityMap> listaCursos = gson.fromJson(json, listType);
                Log.d("Respuesta:  ", String.valueOf(listaCursos));
                Log.d("Respuesta:  ", listaCursos.get(0).getNOMBRE());
                Log.d("Respuesta:  ", listaCursos.get(1).getNOMBRE());
                init(listaCursos);

            }
        } else {
            Toast.makeText(this, data.getMsjResponse(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onError(VolleyError error) {
        Log.d("Error:  ", error.toString());
        Toast.makeText(this, "Error: " + error, Toast.LENGTH_SHORT).show();
    }

    public void requestCursos() {
        GlobalAplicacion global = new GlobalAplicacion();
        opcion = "CN";

        Map<String, String> params = new HashMap<>();
        params.put("opcion", opcion);
        //params.put("id_profesor", String.valueOf(1));
        params.put("id_profesor", String.valueOf(global.getGlobalIdUsuario()));
        params.put("nombre", "");
        params.put("descripcion", "");
        params.put("anio", "");
        params.put("estado", "S");

        IDaoService dao = new IDaoService(MisCursosProfesor.this);
        dao.crudCursosProf(params, MisCursosProfesor.this);
    }

    public void init(List<EntityMap> listaCursos) {


        ListAdapterMisCursos listAdapter = new ListAdapterMisCursos(listaCursos, this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_list_cursos);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);
    }

    //other code de prueba
/*    FloatingActionButton fab = findViewById(R.id.fab);
fab.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            // Crear una instancia de la vista personalizada
            View customView = getLayoutInflater().inflate(R.layout.custom_dialog, null);

            // Crear el AlertDialog
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Título del modal")
                    .setView(customView)
                    .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // Haz algo cuando se haga clic en Aceptar
                        }
                    })
                    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // Haz algo cuando se haga clic en Cancelar
                        }
                    });

            // Mostrar el AlertDialog
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    });*/


}