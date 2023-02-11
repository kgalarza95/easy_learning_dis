package com.example.proyfragmentmodal.dao;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class DaoService {

    static String URL="http://192.168.1.44/php_api_dislexia/";

    public static void getLoginAccess(Context context) {
        // Crear una nueva cola de solicitudes
        RequestQueue queue = Volley.newRequestQueue(context);

        // URL del servicio REST
        String uri = URL+"validar_usuario.php";

        // Crear una nueva solicitud de cadena
        StringRequest stringRequest = new StringRequest(Request.Method.GET, uri,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Manejar la respuesta del servicio REST aquí
                        Log.d("Response", response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Manejar errores aquí
                        Log.d("Error", error.toString());
                    }
                });

        // Añadir la solicitud a la cola
        queue.add(stringRequest);
    }

}
