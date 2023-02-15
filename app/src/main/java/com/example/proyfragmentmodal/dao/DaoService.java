package com.example.proyfragmentmodal.dao;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Deprecated
public class DaoService {

    static String URL = "http://192.168.1.44/php_api_dislexia/";
    Context context = null;
    @Deprecated
    public DaoService() {
    }
    @Deprecated
    public DaoService(Context context) {
        this.context = context;
    }

    @Deprecated
    public static void getLoginAccess(Context context) {
        // Crear una nueva cola de solicitudes
        RequestQueue queue = Volley.newRequestQueue(context);

        // URL del servicio REST
        String uri = URL + "validar_usuario.php";

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

    @Deprecated
    public String getLoginAccess(String usuario, String pass) {
        // URL del servicio REST
        final String[] requestResponse = {""};
        String uri = URL + "validar_usuario.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, uri,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Procesar la respuesta del servidor
                        requestResponse[0] = response;
                        Log.d("Response", response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error", error.toString());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                // Agregar los parámetros a enviar
                Map<String, String> params = new HashMap<>();
                params.put("usuario", usuario);
                params.put("password", pass);
                return params;
            }
        };

        // Añadir la solicitud a la cola de solicitudes
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);

        Log.d("Response ==============================>", stringRequest.toString());
        Log.d("Response ==============================>", stringRequest.getBodyContentType());
        return stringRequest.toString();
    }

    @Deprecated
    //este metodo no sirver, genera un tiempo de espera muy elevado
    public String getLoginAccessSincronic(String usuario, String pass) {
        String response = "";
        String uri = URL + "validar_usuario.php";
        RequestQueue queue = Volley.newRequestQueue(context);
        RequestFuture<String> future = RequestFuture.newFuture();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, uri, future, future) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("usuario", usuario);
                params.put("password", pass);
                return params;
            }
        };

        queue.add(stringRequest);

        try {
            response = future.get(); // Blocking call
            Log.d("Response ==============================>", response);
            // Procesar la respuesta aquí
        } catch (InterruptedException e) {
            // Error
        } catch (ExecutionException e) {
            // Error
        }

        return response;
    }
}
