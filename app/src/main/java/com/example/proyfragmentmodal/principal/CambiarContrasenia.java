package com.example.proyfragmentmodal.principal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.proyfragmentmodal.R;
import com.example.proyfragmentmodal.dao.IDaoService;
import com.example.proyfragmentmodal.entity.Respuesta;
import com.example.proyfragmentmodal.estudiante.LoginEstudiante;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class CambiarContrasenia extends AppCompatActivity
        implements IDaoService.DAOCallbackServicio {

    View vista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiar_contrasenia);

        EditText passActual = findViewById(R.id.txt_pass_actual);
        EditText passNueva = findViewById(R.id.txt_nueva_pass);
        EditText passConfirm = findViewById(R.id.txt_confirmar_pass);

        Bundle extras = getIntent().getExtras();
        String usuario = extras.getString("usuario");

        findViewById(R.id.btn_cancelar_pass).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), MenuProfEstud.class));
            }
        });

        findViewById(R.id.btn_cambir_pass).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (passNueva.getText().toString().equals(passConfirm.getText().toString())) {
                    vista = v;
                    Map<String, String> params = new HashMap<>();
                    params.put("usuario", usuario);
                    params.put("password", passActual.getText().toString());
                    params.put("nueva", passNueva.getText().toString());

                    IDaoService dao = new IDaoService(CambiarContrasenia.this);
                    dao.actualizarPass(params, CambiarContrasenia.this);
                } else {
                    Toast.makeText(CambiarContrasenia.this, "Contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    Gson gson = new Gson();

    @Override
    public void onSuccess(String response) {
        Toast.makeText(this, "Transacción Ok", Toast.LENGTH_SHORT).show();

        Respuesta data = gson.fromJson(response, Respuesta.class);
        if (data.getCodResponse().equals("00")) {
            startActivity(new Intent(vista.getContext(), MenuProfEstud.class));
        } else {
            Toast.makeText(this, "NO SE PUDO ACTUALIZAR LA CONTRASEÑA", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onError(VolleyError error) {
        Log.d("Error:  ", error.toString());
        Toast.makeText(this, "Error: " + error, Toast.LENGTH_SHORT).show();
    }
}