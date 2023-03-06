package com.example.proyfragmentmodal.juego;

import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.proyfragmentmodal.R;
import com.example.proyfragmentmodal.dao.IDaoService;
import com.example.proyfragmentmodal.entity.Palabra;
import com.example.proyfragmentmodal.util.GlobalAplicacion;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


public class PalabraCorrecta extends Fragment
        implements IDaoService.DAOCallbackServicio {


    int score = 0;
    private TextView txtScore;
    private Gson gson = new Gson();
    private String opcion;
    private List<Integer> listaImg = new ArrayList<>();
    private int index = 0;
    private long timeLeftInMillis;
    private long milliseconds;
    private CountDownTimer countDownTimer;
    private TextView countdownTextView;
    private int puntaje = 100;
    int miliSegundos = 0;
    String tipoNivel = "";
    private MediaPlayer sonidoAcierto;
    private MediaPlayer sonidoError;
    private ProgressBar progressBar;

    private View vista;
    private Button btn1, btn2, btn3;
    private ImageView imagen;

    List<Palabra> listPalabras;


    public PalabraCorrecta() {
        // Required empty public constructor
    }


    public static PalabraCorrecta newInstance(String param1, String param2) {
        PalabraCorrecta fragment = new PalabraCorrecta();
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

        vista = inflater.inflate(R.layout.fragment_palabra_correcta, container, false);
        initVal();

        sonidoAcierto = MediaPlayer.create(getActivity(), R.raw.acierto);
        sonidoError = MediaPlayer.create(getActivity(), R.raw.error);
        btn1 = vista.findViewById(R.id.btn_op1);
        btn2 = vista.findViewById(R.id.btn_op2);
        btn3 = vista.findViewById(R.id.btn_op3);
        imagen = vista.findViewById(R.id.vs_img);
        countdownTextView = vista.findViewById(R.id.countdown_text_view);
        txtScore = vista.findViewById(R.id.txt_score);
        progressBar = vista.findViewById(R.id.progress_bar);
        progressBar.setMax(listPalabras.size());
        progressBar.setProgress(5);

        imagen.setBackgroundResource(listaImg.get(index));

        tipoNivel = GlobalAplicacion.nivel;
        switch (tipoNivel) {
            case "F":
                puntaje = 100;
                break;
            case "I":
                puntaje = 150;
                break;
            case "D":
                puntaje = 200;
                break;
            default:
        }

        milliseconds = (int) GlobalAplicacion.miliSegundos;

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Información");
        builder.setMessage("Seleccione la palabra correcta");
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startTimer(milliseconds);
            }
        });
        builder.show();

        initTextBtn();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pasarNivel(btn1.getText().toString());
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pasarNivel(btn2.getText().toString());
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pasarNivel(btn3.getText().toString());
            }
        });

        return vista;

    }

    public void initTextBtn() {
        btn1.setText(listPalabras.get(index).getPalabra1());
        btn2.setText(listPalabras.get(index).getPalabra2());
        btn3.setText(listPalabras.get(index).getPalabra3());

    }

    private void initVal() {
        index = 0;

        listPalabras = new ArrayList<>();
        listPalabras.add(new Palabra("Pisicina", "Piscina", "Picina", "Piscina"));
        listPalabras.add(new Palabra("Pediente", "Penbiente", "Pendiente", "Pendiente"));
        listPalabras.add(new Palabra("Camiseta", "Casimeta", "Catisema ", "Camiseta"));
        listPalabras.add(new Palabra("Peirnas", "Pieremas", "Piernas", "Piernas"));
        listPalabras.add(new Palabra("Tijeras", "Tijares ", "Tirejas", "Tijeras"));
        listPalabras.add(new Palabra("Pitimiento", "Pimiento", "Pimeinto", "Pimiento"));

        listaImg = new ArrayList<>();
        listaImg.add(R.drawable.piscina);
        listaImg.add(R.drawable.pendiente);
        listaImg.add(R.drawable.camiseta);
        listaImg.add(R.drawable.piernas);
        listaImg.add(R.drawable.tijera);
        listaImg.add(R.drawable.pimiento);
    }

    public void bloquearBotones() {
        btn1.setEnabled(false);
        btn2.setEnabled(false);
        btn3.setEnabled(false);

    }

    public void desbloquearBotones() {
        btn1.setEnabled(true);
        btn2.setEnabled(true);
        btn3.setEnabled(true);

    }

    private void startTimer(long milliseconds) {
        timeLeftInMillis = milliseconds;
        countDownTimer = new CountDownTimer(milliseconds, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                try {
                    timeLeftInMillis = millisUntilFinished;
                    updateCountdownText();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFinish() {
                try {
                    timeLeftInMillis = 0;
                    updateCountdownText();
                    Toast.makeText(getContext(), "¡Tiempo terminado!", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private void updateCountdownText() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        countdownTextView.setText(timeLeftFormatted);
    }

    private void stopTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    public void insertarScore() {
        opcion = "IN";//CONSULTA FRASES

        Map<String, String> params = new HashMap<>();
        params.put("opcion", opcion);
        params.put("id_usuario", String.valueOf(GlobalAplicacion.getGlobalIdUsuario()));
        params.put("score", txtScore.getText().toString());
        // params.put("score", String.valueOf(score));
        params.put("id_juego", String.valueOf(5));

        Log.i("Parametros ================================> ", String.valueOf(params));
        IDaoService dao = new IDaoService(getActivity());
        dao.apiJuegos(params, PalabraCorrecta.this);
    }

    private void pasarNivel(String txtBtn) {
        bloquearBotones();
        stopTimer();
        Log.i("index", String.valueOf(index));
        Log.i("txtBtn", txtBtn);
        Log.i("listCorrect.get(index)", listPalabras.get(index).getPalabraCorrecta());

        if (txtBtn.trim().equalsIgnoreCase(listPalabras.get(index).getPalabraCorrecta().trim())) {
            score += puntaje;
            txtScore.setText(String.valueOf(score));
            sonidoAcierto.start();
        } else {
            sonidoError.start();
        }

        if (!GlobalAplicacion.esEstudiante.equalsIgnoreCase("N")) {
            insertarScore();
        }

        //Thread.sleep(3000);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ejecutarJuegoPalabras();
            }
        }, 5000);
    }

    public void ejecutarJuegoPalabras() {
        try {
            index++;

            Log.i("listaImg.get(index)", String.valueOf(index));
            Log.i("listaImg.get(index)", String.valueOf(listaImg.size()));
            desbloquearBotones();
            if (index < listaImg.size()) {


                progressBar.setMax(listaImg.size());
                progressBar.setProgress(index + 1);

                if (index != 0) {
                    startTimer(milliseconds);
                }

            } else {
                index = 0;
                progressBar.setProgress(index + 1);
                score = 0;
                txtScore.setText(String.valueOf(score));
                Toast.makeText(getActivity(), "Fin del juego", Toast.LENGTH_SHORT).show();
            }
            imagen.setBackgroundResource(listaImg.get(index));
            initTextBtn();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onSuccess(String response) {

    }

    @Override
    public void onError(VolleyError error) {

    }


}