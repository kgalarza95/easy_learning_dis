package com.example.proyfragmentmodal.general;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.proyfragmentmodal.R;
import com.example.proyfragmentmodal.estudiante.LoginEstudiante;
import com.example.proyfragmentmodal.estudiante.NivelJuegos;
import com.example.proyfragmentmodal.profesor.LoginProfesor;
import com.example.proyfragmentmodal.util.GlobalAplicacion;

public class MenuProfEstud extends AppCompatActivity {

    Button btnProfesor;
    Button btnEstudiante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_prof_estud);

        btnProfesor = findViewById(R.id.btn_profesor);
        btnEstudiante = findViewById(R.id.btn_estudiane);


        btnProfesor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), LoginProfesor.class));
            }
        });

        btnEstudiante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GlobalAplicacion.esEstudiante = "S";
                startActivity(new Intent(view.getContext(), LoginEstudiante.class));
            }
        });

        findViewById(R.id.btn_prueba).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GlobalAplicacion.esEstudiante = "N";
                Intent intent = new Intent(view.getContext(), NivelJuegos.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed(){
        //En caso de querer permitir volver atrás usa esta llamada:
        //super.onBackPressed();
    }
}