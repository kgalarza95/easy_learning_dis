package com.example.proyfragmentmodal.juego;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.proyfragmentmodal.R;
import com.example.proyfragmentmodal.dao.IDaoService;
import com.example.proyfragmentmodal.entity.Cuento;
import com.example.proyfragmentmodal.entity.EntityMap;
import com.example.proyfragmentmodal.entity.Respuesta;
import com.example.proyfragmentmodal.util.GlobalAplicacion;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CuentosDos extends Fragment
        implements IDaoService.DAOCallbackServicio{

    private View vista;
    private Gson gson = new Gson();
    private String opcion;
    private List<EntityMap> listaRespuesta;
    private List<Cuento> listCuentos;
    private Button btnNext;
    private List<Integer> listaLineas = new ArrayList<>();
    private int i, j;
    private String cual;

    private LinearLayout lyPreguntas;
    RadioGroup opciones1;
    RadioGroup opciones2;

    private TextView pregunta1;
    private TextView pregunta2;

    private RadioButton opcion11;
    private RadioButton opcion12;
    private RadioButton opcion13;

    private RadioButton opcion21;
    private RadioButton opcion22;
    private RadioButton opcion23;
    int score = 0;
    private TextView txtScore;

    public CuentosDos() {
        // Required empty public constructor
    }

    public static CuentosDos newInstance(String param1, String param2) {
        CuentosDos fragment = new CuentosDos();
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
        vista = inflater.inflate(R.layout.fragment_cuentos_dos, container, false);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Información");
        builder.setMessage("Contesta las pregntas de la lectura.");
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        pregunta1 = vista.findViewById(R.id.pregunta1);
        pregunta2 = vista.findViewById(R.id.pregunta2);
        opcion11 = vista.findViewById(R.id.opcion11);
        opcion12 = vista.findViewById(R.id.opcion12);
        opcion13 = vista.findViewById(R.id.opcion13);
        opcion21 = vista.findViewById(R.id.opcion21);
        opcion22 = vista.findViewById(R.id.opcion22);
        opcion23 = vista.findViewById(R.id.opcion23);
        opciones1 = vista.findViewById(R.id.opciones1);
        opciones2 = vista.findViewById(R.id.opciones2);
        txtScore = vista.findViewById(R.id.txt_score);

        llenarList();

        listaLineas = new ArrayList<>();
        listaLineas.add(R.drawable.a_c1);
        listaLineas.add(R.drawable.b_c1);
        listaLineas.add(R.drawable.c_c1);
        listaLineas.add(R.drawable.d_c1);
        listaLineas.add(R.drawable.e_c1);
        listaLineas.add(R.drawable.f_c1);
        listaLineas.add(R.drawable.g_c1);
        listaLineas.add(R.drawable.h_c1);
        listaLineas.add(R.drawable.i_c1);
        listaLineas.add(R.drawable.j_c1);

        i = 0;
        j = 0;
        cual = "cuento";

        LinearLayout linearLayout = vista.findViewById(R.id.ly_preguntas);
        linearLayout.setVisibility(View.GONE);


        FrameLayout fragmentVentana = vista.findViewById(R.id.vista_cuentos);
        btnNext = vista.findViewById(R.id.btn_next);
        fragmentVentana.setBackgroundResource(listaLineas.get(i));// iniciar el cuento a

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cual.equalsIgnoreCase("cuento")) {
                    //sigue pregunta
                    cual = "pregunta";
                    linearLayout.setVisibility(View.VISIBLE);
                    fragmentVentana.setBackground(new ColorDrawable(Color.TRANSPARENT));
                    i++;

                    if (j % 2 == 0) {
                        Cuento cuento = listCuentos.get(j);
                        pregunta1.setText(cuento.getPregunta1());
                        opcion11.setText(cuento.getOp1());
                        opcion13.setText(cuento.getOpCorrecta());

                        pregunta2.setText(cuento.getPregunta2());
                        opcion21.setText(cuento.getOp2_1());
                        opcion23.setText(cuento.getOpCorrecta2());
                    } else {
                        Cuento cuento = listCuentos.get(j);
                        pregunta1.setText(cuento.getPregunta1());
                        opcion11.setText(cuento.getOpCorrecta());
                        opcion13.setText(cuento.getOp2());

                        pregunta2.setText(cuento.getPregunta2());
                        opcion21.setText(cuento.getOpCorrecta2());
                        opcion23.setText(cuento.getOp2_2());
                    }


                    if (i == listaLineas.size()) {
                        insertarScore();
                        i = 0;
                        Toast.makeText(getActivity(), "Fin del juego, vuelve a intentarlo.", Toast.LENGTH_SHORT).show();
                    }
                } else if (cual.equalsIgnoreCase("pregunta")) {
                    //sigue cuento
                    cual = "cuento";
                    //evaluar respuesta y

                    Cuento cuento = listCuentos.get(j);

                    // Obtener el identificador del RadioButton seleccionado en el primer grupo de opciones
                    int idOpcionSeleccionada1 = opciones1.getCheckedRadioButtonId();
                    // Obtener una referencia al RadioButton correspondiente
                    RadioButton radioButtonSeleccionado1 = vista.findViewById(idOpcionSeleccionada1);
                    // Obtener el texto del RadioButton seleccionado
                    String textoOpcionSeleccionada1 = radioButtonSeleccionado1.getText().toString();

                    if (textoOpcionSeleccionada1.trim().equalsIgnoreCase(cuento.getOpCorrecta().trim())) {
                        Toast.makeText(getActivity(), "Pregunta 1: Correcto", Toast.LENGTH_SHORT).show();
                        score += 100;
                        txtScore.setText(String.valueOf(score));
                    } else {
                        Toast.makeText(getActivity(), "Pregunta 1: Incorrecto", Toast.LENGTH_SHORT).show();
                    }

                    //esperar 5 segundos
                    // Obtener el identificador del RadioButton seleccionado en el primer grupo de opciones
                    int idOpcionSeleccionada2 = opciones2.getCheckedRadioButtonId();
                    // Obtener una referencia al RadioButton correspondiente
                    RadioButton radioButtonSeleccionado2 = vista.findViewById(idOpcionSeleccionada2);
                    // Obtener el texto del RadioButton seleccionado
                    String textoOpcionSeleccionada2 = radioButtonSeleccionado2.getText().toString();

                    if (textoOpcionSeleccionada2.trim().equalsIgnoreCase(cuento.getOpCorrecta2().trim())) {
                        Toast.makeText(getActivity(), "Pregunta 2: Correcto", Toast.LENGTH_SHORT).show();
                        score += 100;
                        txtScore.setText(String.valueOf(score));
                    } else {
                        Toast.makeText(getActivity(), "Pregunta 2: Incorrecto", Toast.LENGTH_SHORT).show();
                    }

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            fragmentVentana.setBackgroundResource(listaLineas.get(i));
                            linearLayout.setVisibility(View.GONE);
                            j++;
                            if (j == listCuentos.size()) {
                                j = 0;
                            }
                        }
                    }, 5000);


                }

            }
        });

        return vista;

    }

    private void llenarList() {
        listCuentos = new ArrayList<>();

        listCuentos.add(new Cuento("¿Qué palabras están mal escritas en esta lectura ?",
                "araña, jugando,ahogando", "arañe, fugando,aogando",
                "¿De estas palabras de la lectura  cuales se tildan?",
                "monto, autobus, subio, arbol ","montó, autobús, subió, árbol"));

        listCuentos.add(new Cuento("¿Qué palabras están escritas de manera correcta?",
                "abia,vruja,bolando,vallena", "había, bruja, volando, ballena",
                "¿Qué palabras se tildan?",
                "dio, tenia, cayo, pesco  ","dió, tenía, cayó, pescó"));

        listCuentos.add(new Cuento("¿Identifica las palabras bien escritas?",
                "zo, cielo, coete", "sol, cielo, cohete",
                "¿Qué palabras no llevan acento?",
                "día, tenía, camión ","caracol, campanas, letras"));

        listCuentos.add(new Cuento("¿Identifica qué palabras están mal escritas del texto?",
                "llamaba, beber, barriga", "peces, dinosaurio, agua","yamava, vever, peses",
                "¿Cuántas tildes puedes observar en el texto?",
                "ninguna  ","2 tildes ","10 tildes"));

        listCuentos.add(new Cuento("¿Qué palabras están escritas de manera incorrecta?",
                "noche, volar, elefante, cielo", "erizo, bes, zuelo, estreyas",
                "¿Qué palabras se tildan?",
                "elefante, espejo,bonito ","escorpión, mágico"));

        listCuentos.add(new Cuento("¿Qué palabras están mal escritas en el texto?",
                "fantasma, farola, animales", "bolar, zoológico, bio",
                "¿Qué palabras llevan acento?",
                "animales, casa, niño","zoológico, encontró, había "));

        listCuentos.add(new Cuento("¿Qué palabras están mal escritas?",
                "gusano, vivió, gallo", "gusano, callo, galletas",
                "¿Cuántas tildes observamos en el texto?",
                "ninguna","11 tildes "));

        listCuentos.add(new Cuento("¿Qué palabras están mal escritas según el texto?",
                "ninguna", "avia, uerta, ueyas, ueso, ierba",
                "¿Qué letra les hace falta a las palabras mal escritas?",
                "B","H "));

        listCuentos.add(new Cuento("¿Qué palabras están mal escritas?",
                "indio,hierro,imán ", "hindio, habion, ierro",
                "¿Qué letra les hace falta a las palabras mal escritas?",
                "O","H "));

        listCuentos.add(new Cuento("¿Qué palabras están mal escritas?",
                "manzana, jirafa, bruja", "hojas, izo, asta, ",
                "¿Qué letra le hace falta a las palabras mal escritas?",
                "V","H"));
    }


    public void insertarScore() {
        opcion = "IN";//CONSULTA FRASES

        Map<String, String> params = new HashMap<>();
        params.put("opcion", opcion);
        params.put("id_usuario", String.valueOf(GlobalAplicacion.getGlobalIdUsuario()));
        params.put("score", txtScore.getText().toString());
        // params.put("score", String.valueOf(score));
        params.put("id_juego", String.valueOf(7));

        Log.i("Parametros ================================> ", String.valueOf(params));
        IDaoService dao = new IDaoService(getActivity());
        dao.apiJuegos(params, CuentosDos.this);
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