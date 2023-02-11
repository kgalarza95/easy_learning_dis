package com.example.proyfragmentmodal.principal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.proyfragmentmodal.R;
import com.example.proyfragmentmodal.estudiante.LoginEstudiante;
import com.example.proyfragmentmodal.profesor.LoginProfesor;

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
                startActivity(new Intent(view.getContext(), LoginEstudiante.class));
            }
        });
    }
}