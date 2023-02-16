package com.example.proyfragmentmodal.util;

import android.app.Application;

public class GlobalAplicacion {

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
