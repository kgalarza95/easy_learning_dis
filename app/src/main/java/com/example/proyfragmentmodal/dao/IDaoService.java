package com.example.proyfragmentmodal.dao;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Map;

public class IDaoService {

    static String URL = "http://192.168.1.44/php_api_dislexia/";
    private Context context;

    public IDaoService(Context context) {
        this.context = context;
    }

    public void postData(final Map<String, String> params, final DAOCallbackServicio callback) {
        String uri = URL + "validar_usuario.php";
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, uri,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Procesar la respuesta aquí
                        callback.onSuccess(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Procesar el error aquí
                        callback.onError(error);
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                return params;
            }
        };

        queue.add(stringRequest);
    }

    public interface DAOCallbackServicio {
        void onSuccess(String response);
        void onError(VolleyError error);
    }

}
