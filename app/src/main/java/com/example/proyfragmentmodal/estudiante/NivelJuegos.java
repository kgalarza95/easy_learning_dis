package com.example.proyfragmentmodal.estudiante;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.proyfragmentmodal.R;
import com.example.proyfragmentmodal.estudiante.comun.PantallaJuego;
import com.example.proyfragmentmodal.util.GlobalAplicacion;

/*
  Esta clase queda obsoleta:  manejaba los niveles de los juegos.

 */
@Deprecated
public class NivelJuegos extends AppCompatActivity {

    Button btnFacil;
    Button btnIntermedio;
    Button btnDificil;
    int idJuego;
    long milisegundos;
    String tipoNivel;


    public NivelJuegos() {
        idJuego = 1;
    }

    public NivelJuegos(int id) {
        idJuego = id;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nivel_juegos);

        btnFacil = (Button) findViewById(R.id.btn_facil);
        btnIntermedio = findViewById(R.id.btn_interm);
        btnDificil = findViewById(R.id.btn_dificil);

        milisegundos = 0;

        btnFacil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                milisegundos = 60000;
                tipoNivel = "F";
                irJuego();
            }
        });

        btnIntermedio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                milisegundos = 30000;
                tipoNivel = "I";
                irJuego();
            }
        });

        btnDificil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                milisegundos = 15000;
                tipoNivel = "D";
                irJuego();
            }
        });


    }

    public void irJuego() {
        Intent intent;

        intent = new Intent(this, PantallaJuego.class);
        Log.i("Se pasa los milisegundos:  ", String.valueOf(milisegundos));
        intent.putExtra("nivel", milisegundos);
        intent.putExtra("tipoNivel", tipoNivel);

        GlobalAplicacion.miliSegundos = milisegundos;
        GlobalAplicacion.nivel = tipoNivel;

        startActivity(intent);
    }
}