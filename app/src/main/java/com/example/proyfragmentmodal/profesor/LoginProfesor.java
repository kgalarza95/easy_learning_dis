package com.example.proyfragmentmodal.profesor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.proyfragmentmodal.estudiante.MainActivity;
import com.example.proyfragmentmodal.principal.MenuProfEstud;
import com.example.proyfragmentmodal.R;

public class LoginProfesor extends AppCompatActivity {

    EditText txtUsuario;
    EditText txtPass;
    TextView lblOlvido;
    Button btnIngresar;
    Button btnCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_profesor);


        txtUsuario = findViewById(R.id.txt_usu_profesor);
        txtPass = findViewById(R.id.txt_pass_profesor);
        lblOlvido = findViewById(R.id.lbl_olvido_profe);
        btnCancelar = findViewById(R.id.btn_cancelar_prof);
        btnIngresar = findViewById(R.id.btn_ingresar_prof);

        // aquí se coloca los eventos.
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), MenuProfEstud.class));
            }
        });


        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(view.getContext(), PrincipalProfesor.class));
                //solo prueba de recyclerview
                //  startActivity(new Intent(view.getContext(), RecyclerViewLista.class));
                startActivity(new Intent(view.getContext(), MainActivity.class));
            }
        });


    }
}