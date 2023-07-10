package com.example.proyfragmentmodal.estudiante;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.proyfragmentmodal.R;
import com.example.proyfragmentmodal.estudiante.comun.PantallaJuego;
import com.example.proyfragmentmodal.general.MainActivity;
import com.example.proyfragmentmodal.util.GlobalAplicacion;

public class PreInicioEstudiante extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_inicio_estudiante);

        findViewById(R.id.btn_aula_virtaul).setOnClickListener(new View.OnClickListener() {
            // ((Button)findViewById(R.id.btn_aula_virtaul)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                intent.putExtra("itOrigin", "loginEstudiante");
                startActivity(intent);
            }
        });

        findViewById(R.id.btn_juega_aprende).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(v.getContext(), NivelJuegos.class);
                //intent.putExtra("itOrigin","loginEstudiante");
                //startActivity(intent);

                Intent intent = new Intent(v.getContext(), PantallaJuego.class);
                intent.putExtra("nivel", 60000);
                intent.putExtra("tipoNivel", "F");

                GlobalAplicacion.miliSegundos = 60000;
                GlobalAplicacion.nivel = "F";

                startActivity(intent);
            }
        });
    }
}