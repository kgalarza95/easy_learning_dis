package com.example.proyfragmentmodal.estudiante;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.proyfragmentmodal.R;
import com.example.proyfragmentmodal.dao.IDaoService;
import com.example.proyfragmentmodal.entity.EntityMap;
import com.example.proyfragmentmodal.entity.Respuesta;
import com.example.proyfragmentmodal.principal.MenuProfEstud;
import com.example.proyfragmentmodal.principal.splash;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class JuegoUno extends Fragment
        implements IDaoService.DAOCallbackServicio {


    private long timeLeftInMillis;
    private Gson gson = new Gson();
    private String opcion;
    private List<EntityMap> listaRespuesta;
    private int score = 0;

    private TextView countdownTextView;
    private TextView txtPalabra;
    private TextView txtScore;
    private CountDownTimer countDownTimer;
    private View vista;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private ProgressBar progressBar;
    private long milliseconds;

    private MediaPlayer sonidoAcierto;
    private MediaPlayer sonidoError;

    public JuegoUno() {
        // Required empty public constructor
    }

    public static JuegoUno newInstance(String param1, String param2) {
        JuegoUno fragment = new JuegoUno();
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
        vista = inflater.inflate(R.layout.fragment_juego_uno, container, false);
        Log.i("===========================>", "onCreateView");

        // Cargar el archivo de sonido
        sonidoAcierto = MediaPlayer.create(getActivity(), R.raw.acierto);
        sonidoError = MediaPlayer.create(getActivity(), R.raw.error);

        countdownTextView = vista.findViewById(R.id.countdown_text_view);
        progressBar = vista.findViewById(R.id.progress_bar);
        progressBar.setMax(10);
        progressBar.setProgress(5);
        progreso = 0;
        score = 0;
        milliseconds = 60000; // 60 segundos
        startTimer(milliseconds);

        txtPalabra = vista.findViewById(R.id.txt_palabra);
        txtScore = vista.findViewById(R.id.txt_score);
        btn1 = vista.findViewById(R.id.btn1);
        btn2 = vista.findViewById(R.id.btn2);
        btn3 = vista.findViewById(R.id.btn3);
        btn4 = vista.findViewById(R.id.btn4);

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

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pasarNivel(btn4.getText().toString());
            }
        });

        consultarPalabras();

        return vista;
    }

    public void bloquearBotones(){
        btn1.setEnabled(false);
        btn2.setEnabled(false);
        btn3.setEnabled(false);
        btn4.setEnabled(false);
    }

    public void desbloquearBotones(){
        btn1.setEnabled(true);
        btn2.setEnabled(true);
        btn3.setEnabled(true);
        btn4.setEnabled(true);
    }

    public void pasarNivel(String resp) {
        bloquearBotones();
        stopTimer();
        txtPalabra.setText(listaRespuesta.get(progreso - 1).getPALABRA());

        Log.i("resp: ",resp);
        Log.i("correcta: ",listaRespuesta.get(progreso - 1).getOP_CORRECTA());
        if (resp.equalsIgnoreCase(listaRespuesta.get(progreso - 1).getOP_CORRECTA())) {
            score +=100;
            txtScore.setText(String.valueOf(score));
            sonidoAcierto.start();
        } else {
            sonidoError.start();
        }
        //Thread.sleep(3000);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ejecutarJuegoPalabras(resp);
            }
        }, 5000);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //INICIAR EL CRONOMETRO
        Log.i("===========================>", "onViewCreated");
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
                    pasarNivel("xxxxxxxxxxxxxxxx");
                    Toast.makeText(getContext(), "¡Tiempo terminado!", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private void stopTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    private void updateCountdownText() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        countdownTextView.setText(timeLeftFormatted);
    }

    public void consultarPalabras() {
        opcion = "CP";//CONSULTA Palabras

        Map<String, String> params = new HashMap<>();
        params.put("opcion", opcion);

        Log.i("================================> ", String.valueOf(params));
        IDaoService dao = new IDaoService(getActivity());
        dao.apiJuegos(params, JuegoUno.this);
    }

    public void ejecutarJuegoPalabras(String respuesta) {
        try {
            desbloquearBotones();
            if (progreso < listaRespuesta.size()) {


                progressBar.setMax(listaRespuesta.size());
                progressBar.setProgress(progreso + 1);

                if (progreso != 0) {
                    startTimer(milliseconds);
                }
                btn1.setText(listaRespuesta.get(progreso).getOP1());
                btn2.setText(listaRespuesta.get(progreso).getOP2());
                btn3.setText(listaRespuesta.get(progreso).getOP3());
                btn4.setText(listaRespuesta.get(progreso).getOP4());
                btn4.setText(listaRespuesta.get(progreso).getOP4());
                txtPalabra.setText(listaRespuesta.get(progreso).getPALABRA_INC());
                progreso++;
            } else {
                Toast.makeText(getActivity(), "Fin del juego", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    int progreso = 0;

    @Override
    public void onSuccess(String response) {
        try {
            Log.d("===========================================================  ", "");
            Log.d("Respuesta:  ", response);
            Respuesta data = gson.fromJson(response, Respuesta.class);
            if (data.getCodResponse().equals("00")) {
                if (opcion.equals("CP")) {
                    String json = gson.toJson(data.getData());
                    Type listType = new TypeToken<List<EntityMap>>() {
                    }.getType();
                    listaRespuesta = gson.fromJson(json, listType);
                    Log.d("Respuesta:  ", String.valueOf(listaRespuesta));
                    //Log.d("Respuesta:  ", listaRespuesta.get(0).getNOMBRES());
                    ejecutarJuegoPalabras("");
                }
            } else {
            }
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