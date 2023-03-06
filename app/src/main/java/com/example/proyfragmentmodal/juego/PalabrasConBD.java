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
import com.example.proyfragmentmodal.util.GlobalAplicacion;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


public class PalabrasConBD extends Fragment
        implements IDaoService.DAOCallbackServicio {

    int score = 0;
    private TextView txtScore;


    public PalabrasConBD() {
    }

    public static PalabrasConBD newInstance(String param1, String param2) {
        PalabrasConBD fragment = new PalabrasConBD();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private Gson gson = new Gson();
    private String opcion;
    private List<Integer> listaImg = new ArrayList<>();
    private List<String> listCorrect = new ArrayList<>();
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
    private Button btnB;
    private Button btnD;
    private ImageView imagen;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_palabras_con_b_d, container, false);
        initVal();

        sonidoAcierto = MediaPlayer.create(getActivity(), R.raw.acierto);
        sonidoError = MediaPlayer.create(getActivity(), R.raw.error);
        btnB = vista.findViewById(R.id.btn_b);
        btnD = vista.findViewById(R.id.btn_d);
        imagen = vista.findViewById(R.id.vs_img);
        countdownTextView = vista.findViewById(R.id.countdown_text_view);
        txtScore = vista.findViewById(R.id.txt_score);
        progressBar = vista.findViewById(R.id.progress_bar);
        progressBar.setMax(10);
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
        builder.setMessage("Indique con que letra inicia cada imagen ");
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startTimer(milliseconds);
            }
        });
        builder.show();

        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pasarNivel(btnB.getText().toString());
            }
        });

        btnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pasarNivel(btnD.getText().toString());
            }
        });

        return vista;
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

    public void bloquearBotones() {
        btnB.setEnabled(false);
        btnD.setEnabled(false);

    }

    public void desbloquearBotones() {
        btnB.setEnabled(true);
        btnD.setEnabled(true);

    }

    private void stopTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    private void pasarNivel(String txtBtn) {
        bloquearBotones();
        stopTimer();
        Log.i("index", String.valueOf(index));
        Log.i("txtBtn", txtBtn);
        Log.i("listCorrect.get(index)", listCorrect.get(index));
        if (txtBtn.equalsIgnoreCase(listCorrect.get(index))) {
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

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertarScore() {
        opcion = "IN";//CONSULTA FRASES

        Map<String, String> params = new HashMap<>();
        params.put("opcion", opcion);
        params.put("id_usuario", String.valueOf(GlobalAplicacion.getGlobalIdUsuario()));
        params.put("score", txtScore.getText().toString());
        // params.put("score", String.valueOf(score));
        params.put("id_juego", String.valueOf(3));

        Log.i("Parametros ================================> ", String.valueOf(params));
        IDaoService dao = new IDaoService(getActivity());
        dao.apiJuegos(params, PalabrasConBD.this);
    }

    private void initVal() {
        index = 0;
        listCorrect = new ArrayList<>();
        listCorrect.add("b");
        listCorrect.add("d");
        listCorrect.add("b");
        listCorrect.add("b");
        listCorrect.add("d");
        listCorrect.add("d");
        listCorrect.add("b");
        listCorrect.add("d");
        listCorrect.add("d");

        listaImg = new ArrayList<>();
        listaImg.add(R.drawable.ballena);
        listaImg.add(R.drawable.diamante);
        listaImg.add(R.drawable.bicicleta);
        listaImg.add(R.drawable.bebe);
        listaImg.add(R.drawable.diente);
        listaImg.add(R.drawable.dinosaurio);
        listaImg.add(R.drawable.ducha);
        listaImg.add(R.drawable.dona);
        listaImg.add(R.drawable.doctor);
    }

    @Override
    public void onSuccess(String response) {

    }

    @Override
    public void onError(VolleyError error) {

    }
}