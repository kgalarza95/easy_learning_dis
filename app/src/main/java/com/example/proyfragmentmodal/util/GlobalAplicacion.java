package com.example.proyfragmentmodal.util;

import android.app.Application;

public class GlobalAplicacion {

    private static String globalUsuario;
    private static int globalIdUsuario;

    public GlobalAplicacion() {
    }

    public String getGlobalUsuario() {
        return globalUsuario;
    }

    public void setGlobalUsuario(String globalUsuario) {
        GlobalAplicacion.globalUsuario = globalUsuario;
    }

    public static int getGlobalIdUsuario() {
        return globalIdUsuario;
    }

    public static void setGlobalIdUsuario(int globalIdUsuario) {
        GlobalAplicacion.globalIdUsuario = globalIdUsuario;
    }
}
