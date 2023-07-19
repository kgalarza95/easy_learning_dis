package com.example.proyfragmentmodal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.proyfragmentmodal.util.GlobalAplicacion;

public class ActivityEliminarHtml extends AppCompatActivity {
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_html);

        webView = findViewById(R.id.webview);
        webView.loadUrl("https://www.google.com");

      /*  WebView webView = findViewById(R.id.webview);
        WebSettings webSettings = webView.getSettings();
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
       // webView.loadUrl("file:///android_asset/punto_coma.htmlxxx");
        //"http://"+ GlobalAplicacion.IP+"/php_api_dislexia/juegos/5to/unidad1/punto_coma.html"
        webView.loadUrl("http://"+ GlobalAplicacion.IP+"/php_api_dislexia/juegos/5to/unidad1/punto_coma.html");
    }
}