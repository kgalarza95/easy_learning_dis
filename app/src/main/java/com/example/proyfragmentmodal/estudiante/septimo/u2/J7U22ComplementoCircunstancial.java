package com.example.proyfragmentmodal.estudiante.septimo.u2;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.util.Log;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.example.proyfragmentmodal.util.GlobalAplicacion;

import com.example.proyfragmentmodal.R;


public class J7U22ComplementoCircunstancial extends Fragment {



    View vista;
    ProgressDialog progressDialog;

    public J7U22ComplementoCircunstancial() {
    }

    public static J7U22ComplementoCircunstancial newInstance(String param1, String param2) {
        J7U22ComplementoCircunstancial fragment = new J7U22ComplementoCircunstancial();
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
        vista= inflater.inflate(R.layout.fragment_j7_u22_complemento_circunstancial, container, false);

        try {
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("Cargando...");
            progressDialog.setCancelable(false);
            progressDialog.show();
            vista = inflater.inflate(R.layout.fragment_punto_coma5to, container, false);


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
                    webView.evaluateJavascript("idEstudiante = '" + GlobalAplicacion.getGlobalIdUsuario() + "';", null);
                }
            });

            webView.loadUrl("http://" + GlobalAplicacion.IP + "/php_api_dislexia/juegos/7mo/unidad2/complemento_circunstancial.html");


        } catch (Exception e) {
            Log.e("====================>", String.valueOf(e));
        }

        return vista;
    }
}