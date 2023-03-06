package com.example.proyfragmentmodal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.proyfragmentmodal.dao.IDaoService;
import com.example.proyfragmentmodal.entity.EntityMap;
import com.example.proyfragmentmodal.entity.Respuesta;
import com.example.proyfragmentmodal.estudiante.MaterialEstudio;
import com.example.proyfragmentmodal.juego.JuegoUno;
import com.example.proyfragmentmodal.util.GlobalAplicacion;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImagenesBase extends AppCompatActivity
        implements IDaoService.DAOCallbackServicio {

    String idEstudiante;
    ImageView imgBase;
    Button btnSiguiente;
    Button btnCalificar;
    EditText txtCalificacion;

    private String opcion = "";
    private Gson gson = new Gson();
    ProgressDialog progressDialog;
    private int origenLlamada = 0;
    List<String> alfabeto = new ArrayList<>(Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"));
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagenes_base);
        i = 0;

        imgBase = findViewById(R.id.img_base);
        btnSiguiente = findViewById(R.id.btn_siguiente);
        btnCalificar = findViewById(R.id.btnCalificacion);
        txtCalificacion = findViewById(R.id.txtCalificacion);
        progressDialog = new ProgressDialog(this);


        btnCalificar.setVisibility(View.GONE);
        txtCalificacion.setVisibility(View.GONE);
        btnSiguiente.setVisibility(View.VISIBLE);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            idEstudiante = extras.getString("idEstudiante");
            Log.d("===========================================================  ", "");
            Log.d("idEstudianteSms:  ", idEstudiante);
        }


        if (idEstudiante.equals("4")) {// buscar iamgenes
            consultarImg();
        }

        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i < alfabeto.size()) {
                    consultarImg();
                } else {
                    i = 0;
                    consultarImg();
                }
            }
        });

        btnCalificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertarScore();
            }
        });
    }

    public void consultarImg() {
        try {
            Map<String, String> params = new HashMap<>();
            opcion = "DE";
            params.put("opcion", opcion);
            params.put("archivo", "");
            params.put("nombre", alfabeto.get(i) + ".png");
            i++;
            Log.i("send parametros ", String.valueOf(params));
            IDaoService dao = new IDaoService(this);
            dao.manejoImagenes(params, ImagenesBase.this);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void insertarScore() {
        opcion = "IN";//CONSULTA FRASES

        Map<String, String> params = new HashMap<>();
        params.put("opcion", opcion);
        params.put("id_usuario", String.valueOf(GlobalAplicacion.getGlobalIdUsuario()));
        params.put("score", txtCalificacion.getText().toString());
        params.put("id_juego", String.valueOf(8));

        Log.i("Parametros ================================> ", String.valueOf(params));
        IDaoService dao = new IDaoService(this);
        dao.apiJuegos(params, ImagenesBase.this);


    }

    @Override
    public void onSuccess(String response) {
        try {
            progressDialog.dismiss();
            Log.i("response ============>:  ", String.valueOf(response));

            Log.d("Respuesta:  ", response);
            Respuesta data = gson.fromJson(response, Respuesta.class);
            if (data.getCodResponse().equals("00")) {
                if (opcion.equals("DE")) {

                    Log.d("data:  ", (String) data.getData());
                    // Obtener la cadena base64 de alguna fuente de datos (por ejemplo, una base de datos o una respuesta de API)
                    String base64String = (String) data.getData();


                    // Decodificar la cadena base64 en un arreglo de bytes
                    byte[] decodedBytes = Base64.decode(base64String, Base64.DEFAULT);

                    // Definir las opciones de escala
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inJustDecodeBounds = true; // Sólo obtener las dimensiones de la imagen
                    BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length, options);
                    int imageWidth = options.outWidth;
                    int imageHeight = options.outHeight;
                    int scaleFactor = Math.min(imageWidth / 100, imageHeight / 100); // Escalar la imagen a 100x100 pixels
                    options.inJustDecodeBounds = false;
                    options.inSampleSize = scaleFactor;

                    // Crear un objeto Bitmap a partir del arreglo de bytes y las opciones de escala
                    Bitmap bitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length, options);

                    // Mostrar el objeto Bitmap en un ImageView
                    imgBase.setImageBitmap(bitmap);
                } else if (opcion.equals("IN")) {
                    //calificar
                    btnCalificar.setVisibility(View.GONE);
                    txtCalificacion.setVisibility(View.GONE);
                    btnSiguiente.setVisibility(View.VISIBLE);
                }
            } else {
                Toast.makeText(this, data.getMsjResponse(), Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            //solicitar calificacion
            btnCalificar.setVisibility(View.VISIBLE);
            txtCalificacion.setVisibility(View.VISIBLE);
            btnSiguiente.setVisibility(View.GONE);
        }
    }

    @Override
    public void onError(VolleyError error) {
        try {
            progressDialog.dismiss();
            Toast.makeText(this, String.valueOf(error), Toast.LENGTH_SHORT).show();
            Log.i("error ============>:  ", String.valueOf(error));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}