package com.example.proyfragmentmodal.general;

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
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.proyfragmentmodal.R;
import com.example.proyfragmentmodal.dao.IDaoService;
import com.example.proyfragmentmodal.entity.Respuesta;
import com.example.proyfragmentmodal.util.GlobalAplicacion;
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
    Button btnBuscarCedula;
    View vista;
    Spinner spTiposUsuarios;
    TextView txtMensaje;

    private String opcion;

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
        btnBuscarCedula = vista.findViewById(R.id.btn_buscar_cedula);
        txtMensaje = (TextView) vista.findViewById(R.id.lbl_inf);

        //Declrar componentes
        spTiposUsuarios = vista.findViewById(R.id.sp_usuarios);
        //Adaptador con layout por defecto
        ArrayAdapter<CharSequence> adaptador = ArrayAdapter.createFromResource(getActivity(), R.array.strs_tip_users,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        //Diseño cuando aparezcan las opciones
        adaptador.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        spTiposUsuarios.setAdapter(adaptador);


        origenPantallaConfig();
        initConsDtos();

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> params = new HashMap<>();

                if (origeenLlamadaPagina == 0) {//admin
                    chSolicitarPass.setVisibility(View.VISIBLE);
                    opcion = "IN";
                    params.put("opcion", "IN");
                    params.put("solicitarPass", chSolicitarPass.isChecked() ? "S" : "N");
                    params.put("id_usuario", "0");
                } else { // otra
                    opcion = "AC";
                    params.put("opcion", "AC");
                    params.put("solicitarPass", "N");
                    params.put("id_usuario", String.valueOf(global.getGlobalIdUsuario()));
                }
                params.put("nombres", txtNombres.getText().toString());
                params.put("apellidos", txtApellidos.getText().toString());
                params.put("edad", txtEdad.getText().toString());
                params.put("usuario", txtUsuario.getText().toString());
                params.put("contrasenia", txtPassword.getText().toString());
                params.put("esMasculino", rbMasculino.isChecked() ? "M" : "F");
                params.put("cedula", txtCedula.getText().toString());
                params.put("rol", String.valueOf(spTiposUsuarios.getSelectedItemPosition() + 1));

                IDaoService dao = new IDaoService(getActivity());
                dao.crudUsuario(params, Usuarios.this);
            }
        });

        btnBuscarCedula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> params = new HashMap<>();

                opcion = "CNC";
                params.put("opcion", opcion);
                params.put("solicitarPass", "N");
                params.put("id_usuario", "");
                params.put("nombres", txtNombres.getText().toString());
                params.put("apellidos", txtApellidos.getText().toString());
                params.put("edad", txtEdad.getText().toString());
                params.put("usuario", txtUsuario.getText().toString());
                params.put("contrasenia", txtPassword.getText().toString());
                params.put("esMasculino", rbMasculino.isChecked() ? "M" : "F");
                params.put("cedula", txtCedula.getText().toString());
                params.put("rol", String.valueOf(spTiposUsuarios.getSelectedItemPosition() + 1));

                IDaoService dao = new IDaoService(getActivity());
                dao.crudUsuario(params, Usuarios.this);
            }
        });

        return vista;
    }

    Gson gson = new Gson();
    GlobalAplicacion global = new GlobalAplicacion();

    @Override
    public void onSuccess(String response) {
        try {
            Log.d("Response==========>  ", response);
            Respuesta data = gson.fromJson(response, Respuesta.class);
            if (data.getCodResponse().equals("00")) {
                if (opcion.equals("IN")) {
                    txtNombres.setText("");
                    txtApellidos.setText("");
                    txtEdad.setText("");
                    txtUsuario.setText("");
                    txtCedula.setText("");
                    txtPassword.setText("");
                } else if (opcion.equals("CN") || opcion.equals("CNC")) {

                    Map<String, Object> listFilas = (Map<String, Object>) data.getData();

                    txtNombres.setText((String) listFilas.get("NOMBRES"));
                    txtApellidos.setText((String) listFilas.get("APELLIDOS"));
                    txtEdad.setText(String.valueOf((String) listFilas.get("EDAD")));
                    txtUsuario.setText((String) listFilas.get("USUARIO"));
                    txtCedula.setText((String) listFilas.get("CEDULA"));
                    //txtPassword.setText((String) "");
                    spTiposUsuarios.setSelection(Integer.parseInt((String) listFilas.get("ID_ROL")) - 1);

                    if (listFilas.get("SEXO").equals("M")) {
                        rbMasculino.setChecked(true);
                    } else {
                        rbFemenino.setChecked(true);
                    }
                }

                Toast.makeText(getActivity(), "TRANSACCIÓN OK", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), "NO SE PROCESARON LOS DATOS", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.e("Error local", e.toString());
        }

    }

    @Override
    public void onError(VolleyError error) {
        Log.d("Error:  ", error.toString());
        Toast.makeText(getActivity(), "Error: " + error, Toast.LENGTH_SHORT).show();
    }

    public void origenPantallaConfig() {
        if (this.origeenLlamadaPagina == 0) {//admin
            chSolicitarPass.setVisibility(View.VISIBLE);
            btnBuscarCedula.setVisibility(View.VISIBLE);
            txtMensaje.setVisibility(View.VISIBLE);
            spTiposUsuarios.setVisibility(View.VISIBLE);
            btnGuardar.setText("GUARDAR");
        } else { // otra
            chSolicitarPass.setVisibility(View.GONE);
            btnBuscarCedula.setVisibility(View.GONE);
            txtMensaje.setVisibility(View.GONE);
            spTiposUsuarios.setVisibility(View.GONE);
            btnGuardar.setText("ACTUALIZAR");
            Log.d(" llega passss ==========>  ", String.valueOf(GlobalAplicacion.getGlobalPassword()));
            txtPassword.setText(String.valueOf(GlobalAplicacion.getGlobalPassword()));
        }
    }

    public void initConsDtos() {
        Map<String, String> params = new HashMap<>();
        GlobalAplicacion global = new GlobalAplicacion();

        if (origeenLlamadaPagina == 0) {//admin
        } else { // otra
            opcion = "CN";
            params.put("opcion", "CN");
            params.put("solicitarPass", "N");
            params.put("id_usuario", String.valueOf(global.getGlobalIdUsuario()));
            params.put("nombres", "");
            params.put("apellidos", "");
            params.put("edad", "");
            params.put("usuario", "");
            params.put("contrasenia", "");
            params.put("esMasculino", "");
            params.put("cedula", "");
            params.put("rol", "");

            IDaoService dao = new IDaoService(getActivity());
            dao.crudUsuario(params, Usuarios.this);
        }

    }


}