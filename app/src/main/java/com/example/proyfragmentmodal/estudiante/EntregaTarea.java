package com.example.proyfragmentmodal.estudiante;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.proyfragmentmodal.R;
import com.example.proyfragmentmodal.dao.IDaoService;
import com.example.proyfragmentmodal.entity.Respuesta;
import com.example.proyfragmentmodal.util.GlobalAplicacion;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class EntregaTarea extends AppCompatActivity
        implements IDaoService.DAOCallbackServicio {

    private Gson gson = new Gson();
    private String opcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrega_tarea);

        Bundle bundle = getIntent().getExtras();
        String titulo = bundle.getString("titulo");
        String idTarea = bundle.getString("id_tarea");

        TextView txtTitulo = findViewById(R.id.txt_e_titulo);
        TextView txtFecha = findViewById(R.id.txt_fecha_a_asig);
        EditText txtContenido = findViewById(R.id.edit_text_contenido);
        Button btnGuardar = findViewById(R.id.button_guardar_asig);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String fechaActual = sdf.format(new Date());
        txtFecha.setText(fechaActual);
        txtTitulo.setText(titulo);

        txtContenido.setText("");
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opcion = "EN";//ENTREGAR ASIGANCION

                Map<String, String> params = new HashMap<>();
                params.put("opcion", opcion);
                params.put("id_tarea", idTarea);
                params.put("id_estudiante", String.valueOf(GlobalAplicacion.getGlobalIdUsuario()));
                params.put("entrega", txtContenido.getText().toString());
                params.put("fecha_entrega", fechaActual);
                params.put("calificacion", "10");

                Log.i("Parametros ================================> ", String.valueOf(params));
                IDaoService dao = new IDaoService(EntregaTarea.this);
                dao.crudAsignacion(params, EntregaTarea.this);
            }
        });

    }

    @Override
    public void onSuccess(String response) {
        try {
            Log.d("===========================================================  ", "");
            Log.d("Respuesta:  ", response);
            Respuesta data = gson.fromJson(response, Respuesta.class);
            if (data.getCodResponse().equals("00")) {
                finish();
            } else {
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