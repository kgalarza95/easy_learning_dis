package com.example.proyfragmentmodal.principal;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.proyfragmentmodal.R;
import com.example.proyfragmentmodal.dao.IDaoService;
import com.example.proyfragmentmodal.entity.Respuesta;
import com.example.proyfragmentmodal.estudiante.LoginEstudiante;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class Usuarios extends Fragment
        implements IDaoService.DAOCallbackServicio {

    private int origeenLlamadaPagina = 0; // 0: admin,  1:otra

    EditText txtNombres;
    EditText txtApellidos;
    EditText txtEdad;
    EditText txtUsuario;
    EditText txtCedula;
    EditText txtPassword;
    RadioButton rbMasculino;
    RadioButton rbFemenino;
    CheckBox chSolicitarPass;
    Button btnGuardar;
    View vista;

    public Usuarios() {
    }
    public Usuarios(int destino) {
        origeenLlamadaPagina = destino;
    }

    public static Usuarios newInstance(String param1, String param2) {
        Usuarios fragment = new Usuarios();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_usuarios, container, false);

        txtNombres = vista.findViewById(R.id.txt_nombre_a);
        txtApellidos = vista.findViewById(R.id.txt_apellido_a);
        txtEdad = vista.findViewById(R.id.txt_edad_a);
        txtUsuario = vista.findViewById(R.id.txt_i_usuario_a);
        txtCedula = vista.findViewById(R.id.txt_cedula_a);
        txtPassword = vista.findViewById(R.id.txt_pass_a);
        rbMasculino = vista.findViewById(R.id.rd_masculino);
        rbFemenino = vista.findViewById(R.id.rd_femenino);
        chSolicitarPass = vista.findViewById(R.id.checkBox);
        btnGuardar = vista.findViewById(R.id.btn_save_user);

        //Declrar componentes
        Spinner spTiposUsuarios = vista.findViewById(R.id.sp_usuarios);
        //Adaptador con layout por defecto
        ArrayAdapter<CharSequence> adaptador = ArrayAdapter.createFromResource(getActivity(), R.array.strs_tip_users,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        //Diseño cuando aparezcan las opciones
        adaptador.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        spTiposUsuarios.setAdapter(adaptador);

        origenPantallaConfig();

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> params = new HashMap<>();
                params.put("nombres", txtUsuario.getText().toString());
                params.put("apellidos", txtApellidos.getText().toString());
                params.put("edad", txtEdad.getText().toString());
                params.put("usuario", txtUsuario.getText().toString());
                params.put("contrasenia", txtPassword.getText().toString());
                params.put("esMasculino", rbMasculino.isChecked() ? "M" : "F");
                params.put("solicitarPass", chSolicitarPass.isChecked() ? "S" : "N");
                params.put("cedula", txtCedula.getText().toString());
                params.put("rol", String.valueOf(spTiposUsuarios.getSelectedItemPosition()+1));

                IDaoService dao = new IDaoService(getActivity());
                dao.guardarUsuario(params, Usuarios.this);
            }
        });


        return vista;
    }

    Gson gson = new Gson();

    @Override
    public void onSuccess(String response) {
        Log.d("Response==========>  ", response);
        Respuesta data = gson.fromJson(response, Respuesta.class);
        if (data.getCodResponse().equals("00")) {
            txtNombres.setText("");
            txtApellidos.setText("");
            txtEdad.setText("");
            txtUsuario.setText("");
            txtCedula.setText("");
            txtPassword.setText("");

            Toast.makeText(getActivity(), "TRANSACCIÓN OK", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity(), "NO SE GUARDARON LOS DATOS", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onError(VolleyError error) {
        Log.d("Error:  ", error.toString());
        Toast.makeText(getActivity(), "Error: " + error, Toast.LENGTH_SHORT).show();
    }

    public void origenPantallaConfig(){
        if (this.origeenLlamadaPagina == 0){//admin
            chSolicitarPass.setVisibility(View.VISIBLE);
        }else{ // otra
            chSolicitarPass.setVisibility(View.GONE);
        }
    }
}