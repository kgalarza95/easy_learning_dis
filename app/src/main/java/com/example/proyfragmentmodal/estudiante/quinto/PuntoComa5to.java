package com.example.proyfragmentmodal.estudiante.quinto;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.proyfragmentmodal.R;


public class PuntoComa5to extends Fragment {

    View vista;

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
        } catch (Exception e) {
            Log.e("====================>", String.valueOf(e));
        }
        return vista;
    }
}