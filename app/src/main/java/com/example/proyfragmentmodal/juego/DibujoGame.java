package com.example.proyfragmentmodal.juego;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.proyfragmentmodal.R;
import com.example.proyfragmentmodal.dao.IDaoService;
import com.example.proyfragmentmodal.entity.Respuesta;
import com.example.proyfragmentmodal.util.CustomView;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DibujoGame extends Fragment
        implements IDaoService.DAOCallbackServicio {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;


    private View vista;

    private static final int REQUEST_CODE_PERMISSION = 100;
    private String opcion = "";
    private Gson gson = new Gson();
    ProgressDialog progressDialog;
    private int origenLlamada = 0;

    private Button btnNext;
    private List<Integer> listaLineas = new ArrayList<>();
    private int i;
    List<String> alfabeto = new ArrayList<>(Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"));


    public DibujoGame() {
    }


    public static DibujoGame newInstance(String param1, String param2) {
        DibujoGame fragment = new DibujoGame();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        i = 0;
        listaLineas.add(R.drawable.linea_f);
        listaLineas.add(R.drawable.linea_e);
        listaLineas.add(R.drawable.linea_a);
        listaLineas.add(R.drawable.linea_c);
        listaLineas.add(R.drawable.linea_d);
        listaLineas.add(R.drawable.linea_y);
        listaLineas.add(R.drawable.linea_i);
        listaLineas.add(R.drawable.linea_p);
        listaLineas.add(R.drawable.linea_q);
        listaLineas.add(R.drawable.linea_r);
        listaLineas.add(R.drawable.linea_s);
        listaLineas.add(R.drawable.linea_t);
        listaLineas.add(R.drawable.linea_u);
        listaLineas.add(R.drawable.linea_w);
        listaLineas.add(R.drawable.linea_x);

        vista = inflater.inflate(R.layout.fragment_dibujo_game, container, false);
        CustomView customView = vista.findViewById(R.id.customView);
        Button clearButton = vista.findViewById(R.id.clearButton);
        customView.setClearButton(clearButton);

        btnNext = vista.findViewById(R.id.btn_next);
        FrameLayout fragmentVentana = vista.findViewById(R.id.vista_draw);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Información");
        builder.setMessage("Dibuja sobre las líneas.");
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.show();


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                captureScreen(String.valueOf(i));
                // fragmentVentana.setBackgroundResource(R.drawable.linea_d);
                fragmentVentana.setBackgroundResource(listaLineas.get(i));
                i++;
                customView.clearLinea();
                Toast.makeText(getActivity(), "Enviado para calificación.", Toast.LENGTH_SHORT).show();

                if (i == listaLineas.size()) {
                    i = 0;
                    Toast.makeText(getActivity(), "Fin del juego, vuelve a intentarlo.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return vista;
    }


    public void captureScreen(String nombre) {
        View rootView = getView();

        // Crear un bitmap de la vista
        rootView.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(rootView.getDrawingCache());
        rootView.setDrawingCacheEnabled(false);

        // Guardar el bitmap en el almacenamiento externo
        //String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/screenshot.png";
        try {
            sendTOServer(bitmap, nombre);
            /*FileOutputStream fos = new FileOutputStream(filePath);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendTOServer(Bitmap bitmap, String nombre) {
        progressDialog.setMessage("Cargando...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        // Convierte la imagen a una cadena Base64
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);


        Map<String, String> params = new HashMap<>();
        opcion = "IN";
        params.put("opcion", "IN");
        params.put("pdf", encodedImage);
        params.put("nombre_pdf", nombre+".png");
        IDaoService dao = new IDaoService(getActivity());
        dao.manejoImagenes(params, DibujoGame.this);
    }

    @Override
    public void onSuccess(String response) {
        try {
            progressDialog.dismiss();
            Log.i("response ============>:  ", String.valueOf(response));

            Log.d("===========================================================  ", "");
            Log.d("Respuesta:  ", response);
            Respuesta data = gson.fromJson(response, Respuesta.class);
            if (data.getCodResponse().equals("00")) {
                if (opcion.equals("CN")) {

                }
            } else {
                Toast.makeText(getActivity(), data.getMsjResponse(), Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(VolleyError error) {
        try {
            progressDialog.dismiss();
            Toast.makeText(getActivity(), String.valueOf(error), Toast.LENGTH_SHORT).show();
            Log.i("error ============>:  ", String.valueOf(error));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}