package com.example.proyfragmentmodal.estudiante;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.proyfragmentmodal.R;
import com.example.proyfragmentmodal.dao.IDaoService;
import com.example.proyfragmentmodal.entity.Cuento;
import com.example.proyfragmentmodal.entity.EntityMap;
import com.example.proyfragmentmodal.entity.Respuesta;
import com.example.proyfragmentmodal.util.GlobalAplicacion;
import com.example.proyfragmentmodal.util.GridViewAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.IllegalFormatCodePointException;
import java.util.List;
import java.util.Map;

public class UsoDeLaH extends Fragment
        implements IDaoService.DAOCallbackServicio {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;


    private GridView gridView;
    private List<Integer> imagenes;
    private List<String> opcionesSpinner;
    private List<String> listNombres;
    private List<String> listNombresCorrect;
    int score = 0;
    private TextView txtScore;

    public UsoDeLaH() {
    }

    public static UsoDeLaH newInstance(String param1, String param2) {
        UsoDeLaH fragment = new UsoDeLaH();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    View vista;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_uso_de_la_h, container, false);

        try {

            listNombres = new ArrayList<>();
            listNombresCorrect = new ArrayList<>();

            gridView = vista.findViewById(R.id.grid_view);

            // Crea las listas de imágenes y opciones para el spinner
            imagenes = new ArrayList<>();
            imagenes.add(R.drawable.huevo);
            imagenes.add(R.drawable.arbol);
            imagenes.add(R.drawable.esponja);

            listNombres.add("uevo");
            listNombres.add("árbol");
            listNombres.add("esponja");

            listNombresCorrect.add("H");
            listNombresCorrect.add("-");
            listNombresCorrect.add("-");

            imagenes.add(R.drawable.isla);
            imagenes.add(R.drawable.hormiga);
            imagenes.add(R.drawable.avion);

            listNombres.add("isla");
            listNombres.add("ormiga");
            listNombres.add("avión");

            listNombresCorrect.add("-");
            listNombresCorrect.add("H");
            listNombresCorrect.add("-");

            imagenes.add(R.drawable.ola);
            imagenes.add(R.drawable.edificio);
            imagenes.add(R.drawable.unicornio);

            listNombres.add("ola");
            listNombres.add("ospital");
            listNombres.add("unicornio");

            listNombresCorrect.add("-");
            listNombresCorrect.add("H");
            listNombresCorrect.add("-");

            imagenes.add(R.drawable.hueso);
            imagenes.add(R.drawable.arana);
            imagenes.add(R.drawable.hermana);

            listNombres.add("ueso");
            listNombres.add("araña");
            listNombres.add("ermana");

            listNombresCorrect.add("H");
            listNombresCorrect.add("-");
            listNombresCorrect.add("H");

            imagenes.add(R.drawable.humo);
            imagenes.add(R.drawable.hombre);
            imagenes.add(R.drawable.cesped);//hierba

            listNombres.add("umo");
            listNombres.add("ombre");
            listNombres.add("ierba");

            listNombresCorrect.add("H");
            listNombresCorrect.add("H");
            listNombresCorrect.add("H");

            imagenes.add(R.drawable.hoja);
            imagenes.add(R.drawable.init);//entrada
            imagenes.add(R.drawable.perseguir);//ultimo

            listNombres.add("oja");
            listNombres.add("entrada");
            listNombres.add("último");

            listNombresCorrect.add("H");
            listNombresCorrect.add("-");
            listNombresCorrect.add("-");

            // Agrega todas las imágenes que quieras mostrar

            opcionesSpinner = new ArrayList<>();
            opcionesSpinner.add("H");
            opcionesSpinner.add("-");
            // Agrega todas las opciones que quieras mostrar en el spinner

            // Crea un adaptador personalizado para la cuadrícula
            GridViewAdapter adapter = new GridViewAdapter(getActivity(), imagenes, opcionesSpinner, listNombres);
            gridView.setAdapter(adapter);

            Button btnComprobar = vista.findViewById(R.id.btn_comprobar);
            txtScore = vista.findViewById(R.id.txt_score);

            btnComprobar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    comprobarSeleccion();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<Cuento> listCuentos = new ArrayList();

        listCuentos.add(new Cuento("¿Con qué jugaba la araña en la playa?", "Con una pelota","Con su móvil","Con una aleta","¿De quién era el anillo que estaba en la lancha?","No tenia dueño ","De una gata","De una avispa"));


        return vista;
    }


    private void comprobarSeleccion() {
        try {
            // Recorre todas las vistas hijos del GridView
            for (int i = 0; i < gridView.getChildCount(); i++) {
                View gridItem = gridView.getChildAt(i);

                // Accede a los elementos del grid item mediante findViewById()
                ImageView imageView = gridItem.findViewById(R.id.image_view);
                Spinner spinner = gridItem.findViewById(R.id.spinner);
                TextView textView = gridItem.findViewById(R.id.txt_palabra_h);

                // Realiza las operaciones que necesites con los elementos del grid item
                // ...

                String seleccion = (String) spinner.getSelectedItem();
                Log.i("spinner ================> ", seleccion);

                if (listNombresCorrect.get(i).equalsIgnoreCase(seleccion)) {
                    score += 100;
                    txtScore.setText(String.valueOf(score));
                }

                Log.i("score ================> ", String.valueOf(txtScore.getText()));

            }
            insertarScore();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Gson gson = new Gson();
    private String opcion;

    public void insertarScore() {
        opcion = "IN";//CONSULTA FRASES

        Map<String, String> params = new HashMap<>();
        params.put("opcion", opcion);
        params.put("id_usuario", String.valueOf(GlobalAplicacion.getGlobalIdUsuario()));
        params.put("score", txtScore.getText().toString());
        // params.put("score", String.valueOf(score));

        Log.i("Parametros ================================> ", String.valueOf(params));
        IDaoService dao = new IDaoService(getActivity());
        dao.apiJuegos(params, UsoDeLaH.this);
    }


    @Override
    public void onSuccess(String response) {
        try {
            Log.d("===========================================================  ", "");
            Log.d("Respuesta:  ", response);
            Respuesta data = gson.fromJson(response, Respuesta.class);
            if (data.getCodResponse().equals("00")) {

            } else {
            }
            score = 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(VolleyError error) {
        try {
            Log.d("Error:  ", error.toString());
            Toast.makeText(getActivity(), "Error: " + error, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}