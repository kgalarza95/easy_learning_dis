package com.example.proyfragmentmodal.estudiante;

import static android.app.Activity.RESULT_OK;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.DocumentsContract;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.proyfragmentmodal.R;
import com.example.proyfragmentmodal.dao.IDaoService;
import com.example.proyfragmentmodal.entity.EntityMap;
import com.example.proyfragmentmodal.entity.Respuesta;
import com.example.proyfragmentmodal.util.ListAdapterIconText;
import com.example.proyfragmentmodal.util.ListAdapterIconTextObject;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MaterialEstudio extends Fragment
        implements IDaoService.DAOCallbackServicio {

    private FloatingActionButton fab;
    private String opcion = "";
    private Gson gson = new Gson();
    ProgressDialog progressDialog;
    private int origenLlamada = 0;

    public MaterialEstudio() {
        // Required empty public constructor
    }

    public MaterialEstudio(int origen) {
        origenLlamada = origen;
    }

    public static MaterialEstudio newInstance(String param1, String param2) {
        MaterialEstudio fragment = new MaterialEstudio();
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
        View vista = inflater.inflate(R.layout.fragment_material_estudio, container, false);
        //vista.findViewById(R.id.rv_list_material_estudio);

        try {
            fab = vista.findViewById(R.id.fab_material);

            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("application/pdf");
                    startActivityForResult(Intent.createChooser(intent, "Select PDF"), 1);

                }
            });

            if (origenLlamada != 0) {
                fab.setVisibility(View.GONE);
            }

            progressDialog = new ProgressDialog(getActivity());


            recyclerView = (RecyclerView) vista.findViewById(R.id.rv_list_material_estudio);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

            consultarPdfsPorCurso();
            //prueba, eliminar
            //init(vista);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return vista;
    }

    public void init(View vista) {
        try {
            List<String> litUsuarios = new ArrayList<>();
            int i = 1;
            litUsuarios.add("nobre pdf_" + (i++) + ".pdf");
            litUsuarios.add("nobre pdf_" + (i++) + ".pdf");
            litUsuarios.add("nobre pdf_" + (i++) + ".pdf");
            litUsuarios.add("nobre pdf_" + (i++) + ".pdf");
            litUsuarios.add("nobre pdf_" + (i++) + ".pdf");
            litUsuarios.add("nobre pdf_" + (i++) + ".pdf");
            litUsuarios.add("nobre pdf_" + (i++) + ".pdf");
            litUsuarios.add("nobre pdf_" + (i++) + ".pdf");
            litUsuarios.add("nobre pdf_" + (i++) + ".pdf");
            litUsuarios.add("nobre pdf_" + (i++) + ".pdf");


            ListAdapterIconText listAdapter = new ListAdapterIconText(litUsuarios, getActivity());
            RecyclerView recyclerView = (RecyclerView) vista.findViewById(R.id.rv_list_material_estudio);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(listAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    RecyclerView recyclerView;

    public void consultarPdfsPorCurso() {
        try {
            Map<String, String> params = new HashMap<>();
            opcion = "CN";
            params.put("opcion", opcion);
            params.put("pdf", "");
            params.put("nombre_pdf", "");
            IDaoService dao = new IDaoService(getActivity());
            dao.manejoPDF(params, MaterialEstudio.this);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void initConsulta(List<EntityMap> litUsuarios) {
        try {
            ListAdapterIconTextObject listAdapter = new ListAdapterIconTextObject(litUsuarios, getActivity(),origenLlamada);
            recyclerView.setAdapter(listAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String fileName = "";
        try {
            if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
                Uri uri = data.getData();
                File file = new File(uri.getPath());

                Cursor cursor = getActivity().getContentResolver().query(uri, null, null, null, null);
                if (cursor.moveToFirst()) {
                    int columnIndex = cursor.getColumnIndex(DocumentsContract.Document.COLUMN_DISPLAY_NAME);
                    fileName = cursor.getString(columnIndex);
                    System.out.println("fileName ====>:  " + fileName);
                }
                cursor.close();

                try {
                    InputStream inputStream = getContext().getContentResolver().openInputStream(uri);
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = inputStream.read(buffer)) != -1) {
                        byteArrayOutputStream.write(buffer, 0, len);
                    }
                    String encodedFile = Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);


                    //==================================================================================
                    //Enaviar al servidor.

                    //enviar al servidor el pdf
                    //sendPDFToServer(encodedFile);// de prueba  original

                    progressDialog.setMessage("Cargando...");
                    progressDialog.setCancelable(false);
                    progressDialog.show();

                    Map<String, String> params = new HashMap<>();
                    opcion = "IN";
                    params.put("opcion", "IN");
                    params.put("pdf", encodedFile);
                    params.put("nombre_pdf", fileName);
                    IDaoService dao = new IDaoService(getActivity());
                    dao.manejoPDF(params, MaterialEstudio.this);

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (java.lang.OutOfMemoryError ex) {
                    ex.printStackTrace();
                    Toast.makeText(getActivity(), "El archivo es muy pesado", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void sendPDFToServer(String encodedPDF) {
        Log.i("=================================================================================", "");
        //Log.i("encodedPDF =========>: ",encodedPDF);
        //String url = "http://example.com/upload-pdf";
        String url = "http://192.168.1.44/php_api_dislexia/manejo_pdf.php";
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        JSONObject jsonBody = new JSONObject();

        try {
            jsonBody.put("pdf", encodedPDF);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Maneja la respuesta del servidor
                        Log.i("JSONObject ============>:  ", String.valueOf(response));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Maneja el error de la solicitud
                Log.e("Erro ============>:  ", String.valueOf(error));
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return headers;
            }
        };
        Volley.newRequestQueue(getContext()).add(request);
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
                    //  List<String> listFilas = (List<String>) data.getData();
                    String json = gson.toJson(data.getData());
                    Type listType = new TypeToken<List<EntityMap>>() {
                    }.getType();
                    List<EntityMap> listaCursos = gson.fromJson(json, listType);
                    Log.d("Respuesta:  ", String.valueOf(listaCursos));
                    Log.d("Respuesta:  ", listaCursos.get(0).getRUTA());
                    Log.d("Respuesta:  ", listaCursos.get(0).getNOMBRE());
                    // init(listaCursos);

                    initConsulta(listaCursos);

                } else if (opcion.equals("IN")) {
                    consultarPdfsPorCurso();
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