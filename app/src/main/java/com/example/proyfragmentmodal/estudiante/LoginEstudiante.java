package com.example.proyfragmentmodal.estudiante;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.proyfragmentmodal.principal.MenuProfEstud;
import com.example.proyfragmentmodal.R;

public class LoginEstudiante extends AppCompatActivity {


    EditText txtUsuario;
    EditText txtPass;
    TextView lblOlvido;
    Button btnIngresar;
    Button btnCancelar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_estudiante);


        txtUsuario = findViewById(R.id.txt_usu_estudiante);
        txtPass = findViewById(R.id.txt_pass_estudiante);
        lblOlvido = findViewById(R.id.lbl_olvido);
        btnCancelar = findViewById(R.id.btn_cancelar_est);
        btnIngresar = findViewById(R.id.btn_ingresar_est);

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), MenuProfEstud.class));
            }
        });

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), PreInicioEstudiante.class);
                startActivity(intent);
            }
        });


    }
}