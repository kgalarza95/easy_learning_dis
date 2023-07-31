package com.example.proyfragmentmodal.estudiante;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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
import com.example.proyfragmentmodal.entity.Respuesta;
import com.example.proyfragmentmodal.general.CambiarContrasenia;
import com.example.proyfragmentmodal.general.MenuProfEstud;
import com.example.proyfragmentmodal.R;
import com.example.proyfragmentmodal.util.GlobalAplicacion;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class LoginEstudiante extends AppCompatActivity implements IDaoService.DAOCallbackServicio {


    EditText txtUsuario;
    EditText txtPass;
    TextView lblOlvido;
    Button btnIngresar;
    Button btnCancelar;
    View vista;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_estudiante);


        txtUsuario = findViewById(R.id.txt_usu_estudiante);
        txtPass = findViewById(R.id.txt_pass_estudiante);
        lblOlvido = findViewById(R.id.lbl_olvido);
        btnCancelar = findViewById(R.id.btn_cancelar_est);
        btnIngresar = findViewById(R.id.btn_ingresar_est);

        //solo desarrollo;
        //txtUsuario.setText("alumno");
        //txtPass.setText("1234");

        //txtUsuario.setText("septimo");
        //txtPass.setText("septimo");


        //txtUsuario.setText("sexto");
        //txtPass.setText("sexto");
        progressDialog = new ProgressDialog(this);

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), MenuProfEstud.class));
            }
        });

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //DaoService.getLoginAccess(LoginEstudiante.this);
                //DaoService daoAccess = new DaoService(LoginEstudiante.this);
                //String x = daoAccess.getLoginAccess(txtUsuario.getText().toString(), txtPass.getText().toString());
                //String x = daoAccess.getLoginAccessSincronic(txtUsuario.getText().toString(), txtPass.getText().toString());
                progressDialog.setMessage("Cargando...");
                progressDialog.setCancelable(false);
                progressDialog.show();
                vista = view;


                Map<String, String> params = new HashMap<>();
                params.put("usuario", txtUsuario.getText().toString());
                params.put("password", txtPass.getText().toString());

                IDaoService dao = new IDaoService(LoginEstudiante.this);
                dao.validarUsuario(params, LoginEstudiante.this);


                //solo desarrollo sin conexión.
                //preubaSinConexion();

            }
        });


    }

    Gson gson = new Gson();

    @Override
    public void onSuccess(String response) {
        try {


            progressDialog.dismiss();
            Log.d("Response==========>  ", response);

            Respuesta data = gson.fromJson(response, Respuesta.class);
            if (data.getCodResponse().equals("00")) {

                Map<String, Object> listFilas = (Map<String, Object>) data.getData();
                Intent intent = new Intent(vista.getContext(), PreInicioEstudiante.class);

                if (listFilas.get("ROL").equals("ESTUDIANTE")) {
                    if (listFilas.get("CAMBIAR_CONTRASENIA").equals("N")) {
                        Log.i("llega login", String.valueOf(listFilas));
                        GlobalAplicacion global = new GlobalAplicacion();
                        GlobalAplicacion.setGlobalIdUsuario(Integer.parseInt((String) listFilas.get("ID")));
                        global.setGlobalUsuario((String) listFilas.get("USUARIO"));
                        global.setGlobalPassword(txtPass.getText().toString());
                        intent.putExtra("itOrigin", "loginEstudiante");
                        Log.i("número de curso - login", String.valueOf(listFilas.get("CURSO")));
                        GlobalAplicacion.setGlobalNumCurso(Integer.parseInt((String) listFilas.get("CURSO")));
                        Log.i("número de curso - login", String.valueOf(GlobalAplicacion.getGlobalNumCurso()));
                    } else {
                        intent = new Intent(vista.getContext(), CambiarContrasenia.class);
                        intent.putExtra("usuario", txtUsuario.getText().toString());
                    }
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Usuario sin privilegios de estudiante", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "DATOS INGRESADOS SON INCORRECTOS", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Lo sentimos, ocurrió un error: " + e, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onError(VolleyError error) {
        progressDialog.dismiss();
        Log.d("Error:  ", error.toString());
        Toast.makeText(this, "Error: " + error, Toast.LENGTH_SHORT).show();
    }

    public void preubaSinConexion() {
        Intent intent = new Intent(vista.getContext(), PreInicioEstudiante.class);
        intent.putExtra("itOrigin", "loginEstudiante");
        startActivity(intent);
    }
}