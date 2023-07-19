package com.example.proyfragmentmodal.dao;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Context;
import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.proyfragmentmodal.util.GlobalAplicacion;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Map;

public class IDaoService {

    //static String URL = "http://servidor.local//php_api_dislexia/";
    static String URL = "http://"+ GlobalAplicacion.IP+"/php_api_dislexia/";//house 1
    //static String URL = "http://192.168.100.75/php_api_dislexia/";//house 2
    private Context context;

    public IDaoService(Context context) {
        this.context = context;
    }

    public void validarUsuario(final Map<String, String> params, final DAOCallbackServicio callback) {
        String uri = URL + "validar_usuario.php";
        clienteRestUTF8(URL + "validar_usuario.php", params, callback);
    }


    public void actualizarPass(final Map<String, String> params, final DAOCallbackServicio callback) {
        clienteRestUTF8(URL + "configuraciones.php", params, callback);
    }


    public void crudUsuario(final Map<String, String> params, final DAOCallbackServicio callback) {
        clienteRestUTF8(URL + "crud_usuario.php", params, callback);
    }

    public void crudCursosProf(final Map<String, String> params, final DAOCallbackServicio callback) {
        clienteRestUTF8(URL + "crud_cursos.php", params, callback);
    }


    public void manejoPDF(final Map<String, String> params, final DAOCallbackServicio callback) {
        clienteRestUTF8(URL + "manejo_pdf.php", params, callback);
    }

    public void apiMensajes(final Map<String, String> params, final DAOCallbackServicio callback) {
        clienteRestUTF8(URL + "api_mensajes.php", params, callback);
    }

    public void apiJuegos(final Map<String, String> params, final DAOCallbackServicio callback) {
        clienteRestUTF8(URL + "api_juegos.php", params, callback);
    }

    public void manejoImagenes(final Map<String, String> params, final DAOCallbackServicio callback) {
        clienteRestUTF8(URL + "manejo_imagen.php", params, callback);
    }

    public void crudAsignacion(final Map<String, String> params, final DAOCallbackServicio callback) {
        clienteRestUTF8(URL + "crud_asignaciones.php", params, callback);
    }

    public void crudAsignacionTodas(final Map<String, String> params, final DAOCallbackServicio callback) {
        clienteRestUTF8(URL + "asignaciones_no_entregadas.php", params, callback);
    }





    private void clienteRest(String URL,
                             final Map<String, String> params, final DAOCallbackServicio callback) {
        Log.i("URL:        ", URL);
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
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

    private void clienteRestUTF8(String URL,
                                 final Map<String, String> params,
                                 final DAOCallbackServicio callback) {
        try {
            Log.i("URL:        ", URL);
            RequestQueue queue = Volley.newRequestQueue(context);
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
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

                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    try {
                        //Log.i("conversión-original", new String(response.data));
                        // Obtiene la cadena de bytes de la respuesta HTTP
                        String charset = HttpHeaderParser.parseCharset(response.headers, "UTF-8");
                        String jsonString = new String(response.data, charset);
                        //Log.i("conversión-convert", jsonString);
                        // Devuelve la cadena de caracteres utilizando la codificación adecuada
                        return Response.success(jsonString, HttpHeaderParser.parseCacheHeaders(response));
                    } catch (UnsupportedEncodingException e) {
                        // Manejo de errores
                        Log.e(TAG, "Error de codificación de caracteres", e);
                        return Response.error(new ParseError(e));
                    }
                }

            };

            queue.add(stringRequest);
        } catch (Exception e) {
            Log.e("Error en cliente rest ", e.toString());
        }

    }

    //interfaz interna para utilizar los métodos con la data.
    public interface DAOCallbackServicio {
        void onSuccess(String response);

        void onError(VolleyError error);
    }

}
