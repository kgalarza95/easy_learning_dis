package com.example.proyfragmentmodal.util;

import android.app.Application;

public class GlobalAplicacion extends Application {

    private static String globalUsuario;

    public GlobalAplicacion() {
    }

    public String getGlobalUsuario() {
        return globalUsuario;
    }

    public void setGlobalUsuario(String globalUsuario) {
        this.globalUsuario = globalUsuario;
    }
}
