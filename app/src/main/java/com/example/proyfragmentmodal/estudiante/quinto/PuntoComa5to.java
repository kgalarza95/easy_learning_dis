package com.example.proyfragmentmodal.estudiante.quinto;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ConsoleMessage;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.proyfragmentmodal.R;
import com.example.proyfragmentmodal.util.GlobalAplicacion;


public class PuntoComa5to extends Fragment {

    View vista;
    ProgressDialog progressDialog;

    public PuntoComa5to() {
    }

    public static PuntoComa5to newInstance(String param1, String param2) {
        PuntoComa5to fragment = new PuntoComa5to();
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

        try {
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("Cargando...");
            progressDialog.setCancelable(false);
            progressDialog.show();
            vista = inflater.inflate(R.layout.fragment_punto_coma5to, container, false);

           /* vista = inflater.inflate(R.layout.fragment_punto_coma5to, container, false);
            WebView webView = vista.findViewById(R.id.webview);*/
        /*WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webView.setScrollbarFadingEnabled(false);
        webView.setVerticalScrollBarEnabled(false);
        webView.setHorizontalScrollBarEnabled(false);*/

       /* webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                Log.d("MiApp", consoleMessage.message() + " -- Desde JavaScript en WebView");
                return true;
            }
        });*/
            //webView.loadUrl("file:///android_asset/punto_coma.html");


            WebView webView = vista.findViewById(R.id.webview);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);

            //permitir que se carguen las fuentes externas
            webView.getSettings().setLoadsImagesAutomatically(true);
            webView.getSettings().setAllowFileAccess(true);
            webView.getSettings().setAllowFileAccessFromFileURLs(true);

            // Habilita el almacenamiento DOM para eventos de arrastrar y soltar
            webView.getSettings().setDomStorageEnabled(true);

            webView.setWebChromeClient(new WebChromeClient() {
                @Override
                public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                    Log.d("MiApp", consoleMessage.message() + " -- Desde JavaScript en WebView");
                    return true;
                }
            });

            webView.setWebViewClient(new WebViewClient() {
                @Override
                public void onPageFinished(WebView view, String url) {
                    progressDialog.dismiss();
                    webView.evaluateJavascript("idEstudiante = '89';", null);
                    // webView.evaluateJavascript("asignarIdEstudiante('87');", null);
                }
            });
//no eliminar eso hasta mientras
          /*  webView.setWebViewClient(new WebViewClient() {
                @Override
                public void onPageFinished(WebView view, String url) {
                    webView.evaluateJavascript("idEstudiante = '86';", new ValueCallback<String>() {
                        @Override
                        public void onReceiveValue(String value) {
                            // Aquí puedes realizar acciones adicionales después de completar la evaluación
                            Log.d("MiApp", "Evaluación de idEstudiante completada");

                            // Luego, realiza la siguiente evaluación
                            webView.evaluateJavascript("asignarIdEstudiante('86');", null);
                        }
                    });
                }
            });*/

            webView.loadUrl("http://" + GlobalAplicacion.IP + "/php_api_dislexia/juegos/5to/unidad1/punto_coma.html");

            webView.evaluateJavascript("puntaje = 300;", null);
            webView.evaluateJavascript("idEstudiante = 55;", null);

        } catch (Exception e) {
            Log.e("====================>", String.valueOf(e));
        }
        return vista;
    }
}