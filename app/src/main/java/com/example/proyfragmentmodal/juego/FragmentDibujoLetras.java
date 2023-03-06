package com.example.proyfragmentmodal.juego;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.os.Environment;
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
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FragmentDibujoLetras extends Fragment
        implements IDaoService.DAOCallbackServicio {

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

    public FragmentDibujoLetras() {
    }


    public static FragmentDibujoLetras newInstance(String param1, String param2) {
        FragmentDibujoLetras fragment = new FragmentDibujoLetras();
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
        vista = inflater.inflate(R.layout.fragment_dibujo_letras, container, false);

        i = 0;
        listaLineas.add(R.drawable.img_a);
        listaLineas.add(R.drawable.img_b);
        listaLineas.add(R.drawable.img_c);
        listaLineas.add(R.drawable.img_d);
        listaLineas.add(R.drawable.img_e);
        listaLineas.add(R.drawable.img_f);
        listaLineas.add(R.drawable.img_g);
        listaLineas.add(R.drawable.img_h);
        listaLineas.add(R.drawable.img_i);
        listaLineas.add(R.drawable.img_j);
        listaLineas.add(R.drawable.img_k);
        listaLineas.add(R.drawable.img_l);
        listaLineas.add(R.drawable.img_m);
        listaLineas.add(R.drawable.img_n);
        listaLineas.add(R.drawable.img_o);
        listaLineas.add(R.drawable.img_p);
        listaLineas.add(R.drawable.img_q);
        listaLineas.add(R.drawable.img_r);
        listaLineas.add(R.drawable.img_s);
        listaLineas.add(R.drawable.img_t);
        listaLineas.add(R.drawable.img_u);
        listaLineas.add(R.drawable.img_v);
        listaLineas.add(R.drawable.img_w);
        listaLineas.add(R.drawable.img_x);
        listaLineas.add(R.drawable.img_y);
        listaLineas.add(R.drawable.img_z);


        progressDialog = new ProgressDialog(getActivity());

        //DibujoMove dibujoMove = vista.findViewById(R.id.vista_dibujo);

        CustomView customView = vista.findViewById(R.id.customView);
        Button clearButton = vista.findViewById(R.id.clearButton);
        customView.setClearButton(clearButton);


        btnNext = vista.findViewById(R.id.btn_next);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Información");
        builder.setMessage("Dibuja sobre las líneas.");
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.show();

        FrameLayout fragmentVentana = vista.findViewById(R.id.vista_dibujo_img);

        vista.findViewById(R.id.clearButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //dibujoMove.clearLinea();
               /* if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
                }*/
                //captureScreen(v);
                //captureScreen();

                customView.clearLinea();

            }
        });

        fragmentVentana.setBackgroundResource(listaLineas.get(i));
        i++;

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                captureScreen(alfabeto.get(i-1));

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

    // Agregar el código al método onClick del botón
    public void captureScreen(View v) {
        // Obtener la vista actual
        View rootView = getActivity().getWindow().getDecorView().findViewById(android.R.id.content);

        // Crear un bitmap de la vista
        rootView.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(rootView.getDrawingCache());
        rootView.setDrawingCacheEnabled(false);

        // Guardar el bitmap en el almacenamiento externo
        String filePath = Environment.getExternalStorageDirectory() + "/screenshot.png";
        try {
            FileOutputStream fos = new FileOutputStream(filePath);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        dao.manejoImagenes(params, FragmentDibujoLetras.this);
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