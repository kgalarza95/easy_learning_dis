package com.example.proyfragmentmodal.profesor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.proyfragmentmodal.dao.IDaoService;
import com.example.proyfragmentmodal.entity.EntityMap;
import com.example.proyfragmentmodal.entity.Respuesta;
import com.example.proyfragmentmodal.estudiante.LoginEstudiante;
import com.example.proyfragmentmodal.estudiante.PreInicioEstudiante;
import com.example.proyfragmentmodal.principal.CambiarContrasenia;
import com.example.proyfragmentmodal.principal.MainActivity;
import com.example.proyfragmentmodal.principal.MenuProfEstud;
import com.example.proyfragmentmodal.R;
import com.example.proyfragmentmodal.util.GlobalAplicacion;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class LoginProfesor extends AppCompatActivity
        implements IDaoService.DAOCallbackServicio {

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


                vista = view;
                Map<String, String> params = new HashMap<>();
                params.put("usuario", txtUsuario.getText().toString());
                params.put("password", txtPass.getText().toString());

                IDaoService dao = new IDaoService(LoginProfesor.this);
                dao.postData(params, LoginProfesor.this);


                //solo desarrollo sin conexión.
               // preubaSinConexion();
            }
        });


    }

    Gson gson = new Gson();
    View vista;

    @Override
    public void onSuccess(String response) {
        Log.d("Response==========>  ", response);

        Respuesta data = gson.fromJson(response, Respuesta.class);

        if (data.getCodResponse().equals("00")) {
            //List<EntityMap> listRoles = (List<EntityMap>) data.getData();
            Map<String, Object> listFilas = (Map<String, Object>) data.getData();
            //EntityMap obj = listRoles.get(0);
            Intent intent = new Intent(vista.getContext(), MainActivity.class);
            //if (obj.getROL().equals("ADMINISTRADOR")) {
            if (listFilas.get("ROL").equals("ADMINISTRADOR")) {
                intent.putExtra("itOrigin", "loginAdmin");
                startActivity(intent);
            } else if (listFilas.get("ROL").equals("PROFESOR")) {
                //if (obj.getCAMBIAR_CONTRASENIA().equals("N")){
                if (listFilas.get("CAMBIAR_CONTRASENIA").equals("N")) {
                    Log.d(" listFilas.get(\"ID\")==========>  ", String.valueOf(listFilas.get("ID")));
                    GlobalAplicacion global = new GlobalAplicacion();
                   // global.setGlobalUsuario(String.valueOf((Double) listFilas.get("ID")));
                    GlobalAplicacion.setGlobalIdUsuario(Integer.parseInt((String) listFilas.get("ID")));
                    global.setGlobalUsuario((String) listFilas.get("USUARIO"));
                    Log.d(" guardando passss ==========>  ", txtPass.getText().toString());
                    global.setGlobalPassword(txtPass.getText().toString());
                    Log.d(" getGlobalUsuario==========>  ", global.getGlobalUsuario());
                    intent.putExtra("itOrigin", "loginProfesor");
                } else {
                    intent = new Intent(vista.getContext(), CambiarContrasenia.class);
                    intent.putExtra("usuario", txtUsuario.getText().toString());
                }
                startActivity(intent);
            } else {
                Toast.makeText(this, "Usuario sin privilegios", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "DATOS INGRESADOS SON INCORRECTOS", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onError(VolleyError error) {
        Log.d("Error:  ", error.toString());
        Toast.makeText(this, "Error: " + error, Toast.LENGTH_SHORT).show();
    }

    public void preubaSinConexion(){
        Intent intent = new Intent(vista.getContext(), MainActivity.class);
        intent.putExtra("itOrigin", "loginProfesor");
        startActivity(intent);
    }
}