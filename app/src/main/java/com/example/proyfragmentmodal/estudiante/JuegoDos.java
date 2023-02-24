package com.example.proyfragmentmodal.estudiante;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.proyfragmentmodal.R;
import com.example.proyfragmentmodal.dao.IDaoService;
import com.example.proyfragmentmodal.entity.EntityMap;
import com.example.proyfragmentmodal.entity.Respuesta;
import com.example.proyfragmentmodal.util.GlobalAplicacion;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


public class JuegoDos extends Fragment
        implements IDaoService.DAOCallbackServicio {

    private Gson gson = new Gson();
    private String opcion;
    private List<EntityMap> listaRespuesta;
    int progreso = 0;
    private CountDownTimer countDownTimer;
    private TextView countdownTextView;
    private ProgressBar progressBar;
    private long milliseconds;
    private int score = 0;
    private MediaPlayer sonidoAcierto;
    private MediaPlayer sonidoError;
    private View vista;
    private long timeLeftInMillis;
    private TextView txtScore;
    private GridLayout gridLayout;

    public JuegoDos() {
        // Required empty public constructor
    }

    public static JuegoDos newInstance(String param1, String param2) {
        JuegoDos fragment = new JuegoDos();
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
        vista = inflater.inflate(R.layout.fragment_juego_dos, container, false);

        txtScore = vista.findViewById(R.id.txt_score);

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

        consultarFrases();
        return vista;
    }

    @Override
    public void onPause() {
        super.onPause();
        // Aquí detienes la reproducción del MediaPlayer para detener el sonido
        if (sonidoError != null && sonidoError.isPlaying()) {
            sonidoError.stop();
            sonidoError.release();
            sonidoError = null;
        }

        if (sonidoAcierto != null && sonidoAcierto.isPlaying()) {
            sonidoAcierto.stop();
            sonidoAcierto.release();
            sonidoAcierto = null;
        }

        if (countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer = null;
        }

    }

    public void consultarFrases() {
        opcion = "CF";//CONSULTA FRASES

        Map<String, String> params = new HashMap<>();
        params.put("opcion", opcion);

        Log.i("Parametros ================================> ", String.valueOf(params));
        IDaoService dao = new IDaoService(getActivity());
        dao.apiJuegos(params, JuegoDos.this);
    }

    public void insertarScore() {
        opcion = "IN";//CONSULTA FRASES

        Map<String, String> params = new HashMap<>();
        params.put("opcion", opcion);
        params.put("id_usuario", String.valueOf(GlobalAplicacion.getGlobalIdUsuario()));
        params.put("score", txtScore.getText().toString());

        Log.i("Parametros ================================> ", String.valueOf(params));
        IDaoService dao = new IDaoService(getActivity());
        dao.apiJuegos(params, JuegoDos.this);
    }

    public void ejecutarJuegoPalabras(String respuesta) {
        Log.i("ejecutarJuegoPalabras================================= ", "init");
        try {
            //desbloquearBotones();
            if (progreso < listaRespuesta.size()) {

                progressBar.setMax(listaRespuesta.size());
                progressBar.setProgress(progreso + 1);

                if (progreso != 0) {
                    startTimer(milliseconds);
                }

                // Obtén una referencia al LinearLayout en tu layout XML
                gridLayout = vista.findViewById(R.id.contenedor_palabras);

                // Crea un objeto LayoutParams para los TextViews
                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                params.width = GridLayout.LayoutParams.WRAP_CONTENT;
                params.height = GridLayout.LayoutParams.WRAP_CONTENT;
                //params.setMargins(8, 8, 8, 8); // establece los márgenes deseados

                // Crea una instance de TextView
                Button button;

                String frase = listaRespuesta.get(progreso).getFRASE();
                String[] split = frase.split(" ");

                // Crea un objeto LayoutParams con los márgenes deseados
                LinearLayout.LayoutParams paramsL = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                paramsL.setMargins(0, 0, 0, 0);


                // Crear un objeto de tipo View.OnClickListener() y sobrescribir onClick()
                View.OnClickListener buttonClickListener = new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Obtener el texto del botón seleccionado
                        String buttonText = ((Button) view).getText().toString();
                        pasarNivel(buttonText, ((Button) view));

                        // Definir el comportamiento del botón aquí
                        //Toast.makeText(getActivity(), "Botón " + buttonText + " presionado", Toast.LENGTH_SHORT).show();
                    }
                };

                //LIMPIAR GRID LAYOUT
                gridLayout.removeAllViews();

                for (int i = 0; i < split.length; i++) {
                    button = new Button(getActivity());
                    button.setText(split[i]);
                    button.setTextSize(18);
                    button.setLayoutParams(paramsL);
            /*button.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));*/
                    gridLayout.addView(button);
                    button.setOnClickListener(buttonClickListener);
                }

                progreso++;
            } else {
                Toast.makeText(getActivity(), "Fin del juego", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
                    pasarNivel("xxxxxxxxxxxxxxxx", null);
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

    public void pasarNivel(String resp, Button btn) {
        //bloquearBotones();
        stopTimer();
        //txtPalabra.setText(listaRespuesta.get(progreso - 1).getPALABRA());

        String palabraErrada = listaRespuesta.get(progreso - 1).getPALABRA_ERRADA().trim();
        Log.i("resp: ", resp);
        Log.i("palabra_errada: ", palabraErrada);
        if (resp.trim().equalsIgnoreCase(palabraErrada)) {
            score += 100;
            txtScore.setText(String.valueOf(score));
            sonidoAcierto.start();
        } else {
            sonidoError.start();
        }

        insertarScore();

        if (btn != null) {

            String frase = listaRespuesta.get(progreso-1).getFRASE();
            String[] split = frase.split(" ");
            Button button;
            LinearLayout.LayoutParams paramsL = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            paramsL.setMargins(0, 0, 0, 0);
            //LIMPIAR GRID LAYOUT
            gridLayout.removeAllViews();

            for (int i = 0; i < split.length; i++) {
                button = new Button(getActivity());
                button.setText(split[i]);
                button.setTextSize(18);
                button.setLayoutParams(paramsL);
                button.setEnabled(false);
                if (palabraErrada.equalsIgnoreCase(split[i].trim())){
                    button.setEnabled(true);
                    button.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark)); //establecer el color de fondo
                    button.setTextColor(getResources().getColor(R.color.white)); //
                    button.setText(listaRespuesta.get(progreso - 1).getPALABRA_CORRECTA());
                }
                gridLayout.addView(button);
            }

           /* for (int i = 0; i < gridLayout.getChildCount(); i++) {
                View view = gridLayout.getChildAt(i);
                if (view instanceof Button) {
                    Button button = (Button) view;
                    button.setEnabled(false);
                }
            }
            btn.setEnabled(false);
            btn.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark)); //establecer el color de fondo
            btn.setTextColor(getResources().getColor(R.color.white)); //
            btn.setText(listaRespuesta.get(progreso - 1).getPALABRA_CORRECTA());*/

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
    public void onSuccess(String response) {
        try {
            Log.d("===========================================================  ", "");
            Log.d("Respuesta:  ", response);
            Respuesta data = gson.fromJson(response, Respuesta.class);
            if (data.getCodResponse().equals("00")) {
                if (opcion.equals("CF")) {
                    String json = gson.toJson(data.getData());
                    Type listType = new TypeToken<List<EntityMap>>() {
                    }.getType();
                    listaRespuesta = gson.fromJson(json, listType);
                    Log.d("Respuesta antes de ejecutar:  ", String.valueOf(listaRespuesta));
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