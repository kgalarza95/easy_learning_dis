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

public class FragmentCuentos extends Fragment
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

    public FragmentCuentos() {
    }

    public static FragmentCuentos newInstance(String param1, String param2) {
        FragmentCuentos fragment = new FragmentCuentos();
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
        vista = inflater.inflate(R.layout.fragment_cuentos, container, false);

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
        listaLineas.add(R.drawable.a);
        listaLineas.add(R.drawable.b);
        listaLineas.add(R.drawable.c);
        listaLineas.add(R.drawable.d);
        listaLineas.add(R.drawable.e);
        listaLineas.add(R.drawable.f);
        listaLineas.add(R.drawable.g);
        listaLineas.add(R.drawable.h_);
        listaLineas.add(R.drawable.i);
        listaLineas.add(R.drawable.j);

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
                        opcion12.setText(cuento.getOp2());
                        opcion13.setText(cuento.getOpCorrecta());

                        pregunta2.setText(cuento.getPregunta2());
                        opcion21.setText(cuento.getOp2_1());
                        opcion22.setText(cuento.getOp2_2());
                        opcion23.setText(cuento.getOpCorrecta2());
                    } else {
                        Cuento cuento = listCuentos.get(j);
                        pregunta1.setText(cuento.getPregunta1());
                        opcion11.setText(cuento.getOpCorrecta());
                        opcion12.setText(cuento.getOp1());
                        opcion13.setText(cuento.getOp2());

                        pregunta2.setText(cuento.getPregunta2());
                        opcion21.setText(cuento.getOpCorrecta2());
                        opcion22.setText(cuento.getOp2_1());
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

        listCuentos.add(new Cuento("¿Con qué jugaba la araña en la playa?",
                "Con una pelota", "Con su móvil", "Con una aleta",
                "¿De quién era el anillo que estaba en la lancha?", "No tenia dueño ",
                "De una gata", "De una avispa"));

        listCuentos.add(new Cuento("¿Encima de qué se cayó la bruja?",
                "De un barco", "	De un banco", "De una casa",
                "¿Qué animal pescó la bruja?", "Un pez ",
                "Un pulpo", " Una ballena"));

        listCuentos.add(new Cuento("¿Cómo se llama el caracol?",
                "Juan", "Carlos", "Pedro",
                "¿Cuántas vueltas en el cohete dio el caracol sobre el mundo?", "Dos ",
                "Cuatro", "Cinco "));

        listCuentos.add(new Cuento("¿Cómo se llama el dinosaurio?",
                "Juan", "David", "Pedro",
                "¿Qué se puso hacer el dinosaurio para que se le salgan los peces?", "Cantar ",
                "Correr", "Saltar "));


        listCuentos.add(new Cuento("¿En que se convirtieron el erizo y el escorpión al verse al espejo mágico?",
                "Pumas", "Elefantes", "Jirafas",
                "¿Cómo se llamaba la estrella donde se quedaron a vivir?", "Luna ",
                "Mercurio", "E"));


        listCuentos.add(new Cuento("¿Cómo se llama el fantasma?",
                "Juan", "Fofi", "Pedro",
                "¿Para donde se fue el fantasma con el elefante?", "A la casa ",
                "Al zoológico ", "A la playa "));


        listCuentos.add(new Cuento("¿Cómo se llama el gusano?",
                "Juan", "Gonzalo", "Pedro",
                "¿Qué le vendió el gallo al gusano?", "Una manzana ",
                "Unas gafas de sol ", "Agua "));

        //H

        listCuentos.add(new Cuento("¿De dónde salió el hipopótamo pequeño?",
                "De la hierba", "De la granja", "Del huevo",
                "¿Qué comió el hipopótamo pequeño al nacer?", "Una manzana ",
                "Comió hueso y hierba ", "Agua "));


        listCuentos.add(new Cuento("¿Cómo se llama el indio?",
                "Juan", "Imi", "Pedro",
                "¿En qué llego montado el indio a la isla?", "En bote",
                "En barco ", "En avión "));


        listCuentos.add(new Cuento("¿Qué estaba comiendo la jirafa?",
                "Una manzana", "Agua ", "Hojas de los arboles y jamón ",
                "La bruja que convirtió a la jirafa en pájaro era ", "Bruja buena",
                "Bruja regular", "Bruja mala "));
    }

    int score = 0;
    private TextView txtScore;

    public void insertarScore() {
        opcion = "IN";//CONSULTA FRASES

        Map<String, String> params = new HashMap<>();
        params.put("opcion", opcion);
        params.put("id_usuario", String.valueOf(GlobalAplicacion.getGlobalIdUsuario()));
        params.put("score", txtScore.getText().toString());
        // params.put("score", String.valueOf(score));
        params.put("id_juego", String.valueOf(6));

        Log.i("Parametros ================================> ", String.valueOf(params));
        IDaoService dao = new IDaoService(getActivity());
        dao.apiJuegos(params, FragmentCuentos.this);
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