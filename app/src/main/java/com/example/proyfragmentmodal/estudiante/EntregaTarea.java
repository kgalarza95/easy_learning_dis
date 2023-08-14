package com.example.proyfragmentmodal.estudiante;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.proyfragmentmodal.R;
import com.example.proyfragmentmodal.dao.IDaoService;
import com.example.proyfragmentmodal.entity.Respuesta;
import com.example.proyfragmentmodal.util.GlobalAplicacion;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class EntregaTarea extends AppCompatActivity
        implements IDaoService.DAOCallbackServicio {

    private Gson gson = new Gson();
    private String opcion;
    ProgressDialog progressDialog;
    private static final int PICK_FILE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrega_tarea);

        progressDialog = new ProgressDialog(this);

        Bundle bundle = getIntent().getExtras();
        String titulo = bundle.getString("titulo");
        String idTarea = bundle.getString("id_tarea");

        TextView txtTitulo = findViewById(R.id.txt_e_titulo);
        TextView txtFecha = findViewById(R.id.txt_fecha_a_asig);
        EditText txtContenido = findViewById(R.id.edit_text_contenido);
        Button btnGuardar = findViewById(R.id.button_guardar_asig);
        Button btnAdjuntar = findViewById(R.id.btnSubirPdf);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String fechaActual = sdf.format(new Date());
        txtFecha.setText(fechaActual);
        txtTitulo.setText(titulo);

        txtContenido.setText("");
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opcion = "EN";//ENTREGAR ASIGANCION

                Map<String, String> params = new HashMap<>();
                params.put("opcion", opcion);
                params.put("id_tarea", idTarea);
                params.put("id_estudiante", String.valueOf(GlobalAplicacion.getGlobalIdUsuario()));
                params.put("entrega", txtContenido.getText().toString());
                params.put("fecha_entrega", fechaActual);
                params.put("calificacion", "0");

                params.put("nombre", nombre);
                params.put("encodedFile", encodedFile);

                Log.i("Parametros ================================> ", "se omiten parametros");

                Log.i("Parametros ================================> nombre ", nombre);
                Log.i("Parametros ================================> encodedFile ", encodedFile);
                //Log.i("Parametros ================================> ", String.valueOf(params));
                IDaoService dao = new IDaoService(EntregaTarea.this);
                dao.crudAsignacion(params, EntregaTarea.this);
            }
        });

        btnAdjuntar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("application/pdf");
                startActivityForResult(Intent.createChooser(intent, "Select PDF"), 1);


               // Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
               // intent.setType("*/*"); // Puede personalizar el tipo de archivo que deseas permitir
                //startActivityForResult(intent, PICK_FILE_REQUEST);

            }
        });
    }

    @Override
    public void onSuccess(String response) {
        try {
            Log.d("===========================================================  ", "");
            Log.d("Respuesta:  ", response);
            Respuesta data = gson.fromJson(response, Respuesta.class);
            if (data.getCodResponse().equals("00")) {
                finish();
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
            Toast.makeText(this, "Error: " + error, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    String nombre;
    String encodedFile;


  /*  @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String fileName = "";
        Log.i("mensaje de transacción", "INIT CARGA ARCHIVO");
        progressDialog.setMessage("Cargando...");
        progressDialog.setCancelable(false);
        progressDialog.show();


        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_FILE_REQUEST && resultCode == RESULT_OK) {
            //Uri selectedFileUri = data.getData();
            // Aquí puedes manejar la URI del archivo seleccionado

            Uri uri = data.getData();
            File file = new File(uri.getPath());

            Cursor cursor = this.getContentResolver().query(uri, null, null, null, null);
            if (cursor.moveToFirst()) {
                int columnIndex = cursor.getColumnIndex(DocumentsContract.Document.COLUMN_DISPLAY_NAME);
                fileName = cursor.getString(columnIndex);
                System.out.println("fileName ====>:  " + fileName);
            }
            cursor.close();

            try {
                InputStream inputStream = this.getContentResolver().openInputStream(uri);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len;
                while ((len = inputStream.read(buffer)) != -1) {
                    byteArrayOutputStream.write(buffer, 0, len);
                }
                encodedFile = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);


                //==================================================================================
                //Enaviar al servidor.

                //enviar al servidor el pdf
                //sendPDFToServer(encodedFile);// de prueba  original


                Log.i("mensaje de transacción", "ARCHIVO CARGADO CORRECTAMENTE");

                progressDialog.dismiss();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (java.lang.OutOfMemoryError ex) {
                ex.printStackTrace();
                Toast.makeText(this, "El archivo es muy pesado", Toast.LENGTH_SHORT).show();
            }
        }
    }*/


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String fileName = "";
        Log.i("mensaje de transacción", "INIT CARGA ARCHIVO");
        progressDialog.setMessage("Cargando...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        try {
            if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
                Uri uri = data.getData();
                File file = new File(uri.getPath());

                Cursor cursor = this.getContentResolver().query(uri, null, null, null, null);
                if (cursor.moveToFirst()) {
                    int columnIndex = cursor.getColumnIndex(DocumentsContract.Document.COLUMN_DISPLAY_NAME);
                    fileName = cursor.getString(columnIndex);
                    System.out.println("fileName ====>:  " + fileName);
                    nombre= fileName;
                }
                cursor.close();

                try {
                    InputStream inputStream = this.getContentResolver().openInputStream(uri);
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = inputStream.read(buffer)) != -1) {
                        byteArrayOutputStream.write(buffer, 0, len);
                    }
                    encodedFile = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);


                    //==================================================================================
                    //Enaviar al servidor.

                    //enviar al servidor el pdf
                    //sendPDFToServer(encodedFile);// de prueba  original


                    Log.i("mensaje de transacción", "ARCHIVO CARGADO CORRECTAMENTE");

                    Log.i("Parametros ================================> nombre ", nombre);
                    Log.i("Parametros ================================> encodedFile ", encodedFile);

                    progressDialog.dismiss();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (java.lang.OutOfMemoryError ex) {
                    ex.printStackTrace();
                    Toast.makeText(this, "El archivo es muy pesado", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }




}