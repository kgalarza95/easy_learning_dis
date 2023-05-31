package com.example.proyfragmentmodal.general;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.proyfragmentmodal.R;
import com.example.proyfragmentmodal.dao.IDaoService;
import com.example.proyfragmentmodal.entity.EntityMap;
import com.example.proyfragmentmodal.entity.Respuesta;
import com.example.proyfragmentmodal.util.GlobalAplicacion;
import com.example.proyfragmentmodal.adapter.ListAdaptRecyChat;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatGeneral extends AppCompatActivity
        implements IDaoService.DAOCallbackServicio {

    private EditText smsENVIAR;
    ListAdaptRecyChat chatAdapter;
    private String opcion;
    private Gson gson = new Gson();
    private String idEstudianteSms;
    private ProgressDialog progressDialog;
    private ArrayList<EntityMap> listaRespuesta;
    private String idConversacion ="0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_general);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            idEstudianteSms = extras.getString("idEstudiante");
            Log.d("===========================================================  ", "");
            Log.d("idEstudianteSms:  ", idEstudianteSms);
        }

        smsENVIAR = findViewById(R.id.message_input);
        progressDialog = new ProgressDialog(this);


        ArrayList<EntityMap> litMensaje = new ArrayList<>();

     /*   litMensaje.add(new EntityMap("S", "S", "YO HOLA"));
        litMensaje.add(new EntityMap("N", "N", "TÚ HOLA"));
        litMensaje.add(new EntityMap("S", "S", "YO HOLA 2"));*/


        //ListView listView = findViewById(R.id.lv_lista_mensajes);
        //ListAdapterChatMensajes listAdapter = new ListAdapterChatMensajes( this, litMensaje);
        //listView.setAdapter(listAdapter);

       /* LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);*/

        // Inicializar el RecyclerView y el ChatAdapter
        RecyclerView chatRecyclerView = findViewById(R.id.rv_lista_mensajes);
        chatAdapter = new ListAdaptRecyChat((ArrayList<EntityMap>) litMensaje.clone());

        // Configurar el RecyclerView
        chatRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        chatRecyclerView.setAdapter(chatAdapter);

        // Notificar al adaptador que se han agregado nuevos mensajes
        chatAdapter.notifyDataSetChanged();

        findViewById(R.id.send_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*litMensaje.add(new EntityMap("S", "S", smsENVIAR.getText().toString()));
                chatAdapter = null;
                chatAdapter = new ListAdaptRecyChat((ArrayList<EntityMap>) litMensaje.clone());
                chatRecyclerView.setAdapter(chatAdapter);
                chatAdapter.notifyDataSetChanged();
                smsENVIAR.setText("");*/


                progressDialog.setMessage("Cargando...");
                progressDialog.setCancelable(false);
                progressDialog.show();


                Map<String, String> params = new HashMap<>();


                if (idConversacion.equals("0")){
                    //conversación existente
                    opcion = "IN1";//INSERTAR MENSAJE NUEVO
                }else{
                    //conversación nueva
                    opcion = "IN2";//INSERTAR MENSAJE EXISTENTE
                }

                params.put("opcion", opcion);
                params.put("id_persona1", String.valueOf(GlobalAplicacion.getGlobalIdUsuario()));
                params.put("id_persona2", idEstudianteSms);

                params.put("id_duenio", String.valueOf(GlobalAplicacion.getGlobalIdUsuario()));
                params.put("id_mensaje_relacion", idConversacion);
                params.put("mensaje", smsENVIAR.getText().toString());

                Log.i("=================================================================================", "");
                Log.i("Parámetros enviados: ", String.valueOf(params));
                IDaoService dao = new IDaoService(ChatGeneral.this);
                dao.apiMensajes(params, ChatGeneral.this);
                smsENVIAR.setText("");
            }
        });


        consultaMensajes();
    }

    public void consultaMensajes() {
        progressDialog.setMessage("Cargando...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        opcion = "CN";//CONSULTA MENSAJES

        Map<String, String> params = new HashMap<>();
        params.put("opcion", opcion);
        params.put("id_persona1", String.valueOf(GlobalAplicacion.getGlobalIdUsuario()));
        params.put("id_persona2", idEstudianteSms);

        Log.i("=================================================================================", "");
        Log.i("Parámetros enviados: ", String.valueOf(params));
        IDaoService dao = new IDaoService(ChatGeneral.this);
        dao.apiMensajes(params, ChatGeneral.this);
    }

    public void llenarCajaMensajes(ArrayList<EntityMap> litMensaje) {
        //litMensaje.add(new EntityMap("S", "S", "YO HOLA"));

        // Inicializar el RecyclerView y el ChatAdapter
        RecyclerView chatRecyclerView = findViewById(R.id.rv_lista_mensajes);
        chatAdapter = new ListAdaptRecyChat((ArrayList<EntityMap>) litMensaje.clone());

        // Configurar el RecyclerView
        chatRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        chatRecyclerView.setAdapter(chatAdapter);

        // Notificar al adaptador que se han agregado nuevos mensajes
        chatAdapter.notifyDataSetChanged();
    }


    @Override
    public void onSuccess(String response) {
        progressDialog.dismiss();
        try {
            Log.d("===========================================================  ", "");
            Log.d("Respuesta:  ", response);
            Respuesta data = gson.fromJson(response, Respuesta.class);
            if (data.getCodResponse().equals("00")) {
                if (opcion.equals("CN")) {
                    String json = gson.toJson(data.getData());
                    Type listType = new TypeToken<List<EntityMap>>() {
                    }.getType();
                    listaRespuesta = gson.fromJson(json, listType);
                    Log.d("Respuesta:  ", String.valueOf(listaRespuesta));
                    idConversacion =listaRespuesta.get(0).getID_MENSAJE_RELACION();
                    Log.d("ID_MENSAJE_RELACION:  ", idConversacion);
                    llenarCajaMensajes(listaRespuesta);
                }else if (opcion.equals("IN2") || opcion.equals("IN1")){
                    consultaMensajes();
                }
            } else {
                if (opcion.equals("CN")) {
                    //para iniciar una nueva conversacion
                    idConversacion ="0";
                }else{
                    Toast.makeText(this, data.getMsjResponse(), Toast.LENGTH_SHORT).show();
                }
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
}