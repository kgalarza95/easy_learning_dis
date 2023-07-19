package com.example.proyfragmentmodal.estudiante.quinto;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.proyfragmentmodal.R;
import com.example.proyfragmentmodal.util.GlobalAplicacion;


public class Tilde5to extends Fragment {

    public Tilde5to() {
    }

    public static Tilde5to newInstance(String param1, String param2) {
        Tilde5to fragment = new Tilde5to();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    View vista;
    ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        vista = inflater.inflate(R.layout.fragment_tilde5to, container, false);
        try {
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("Cargando...");
            progressDialog.setCancelable(false);
            progressDialog.show();
            WebView webView = vista.findViewById(R.id.webview);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);

            //permitir que se carguen las fuentes externas
            webView.getSettings().setLoadsImagesAutomatically(true);
            webView.getSettings().setAllowFileAccess(true);
            webView.getSettings().setAllowFileAccessFromFileURLs(true);

            // Habilita el almacenamiento DOM para eventos de arrastrar y soltar
            webView.getSettings().setDomStorageEnabled(true);

            //manejar consola de js - android
            webView.setWebChromeClient(new WebChromeClient() {
                @Override
                public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                    Log.d("MiApp", consoleMessage.message() + " -- Desde JavaScript en WebView");
                    return true;
                }
            });

            //cambiar valores al js despues de cargar la página
            webView.setWebViewClient(new WebViewClient() {
                @Override
                public void onPageFinished(WebView view, String url) {
                    progressDialog.dismiss();
                    //webView.evaluateJavascript("idEstudiante = '89';", null);
                    // webView.evaluateJavascript("asignarIdEstudiante('87');", null);
                }
            });
            webView.loadUrl("http://" + GlobalAplicacion.IP + "/php_api_dislexia/juegos/5to/unidad1/oracion_correcta.html");
        } catch (Exception e) {
            Log.e("====================>", String.valueOf(e));
        }
        return vista;
    }
}