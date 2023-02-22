package com.example.proyfragmentmodal.util;

import android.app.Application;

public class GlobalAplicacion {

    private static String globalUsuario;
    private static String globalPassword;
    private static String globalNomUsuario;
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

    public static String getGlobalPassword() {
        return globalPassword;
    }

    public static void setGlobalPassword(String globalPassword) {
        GlobalAplicacion.globalPassword = globalPassword;
    }

    public static String getGlobalNomUsuario() {
        return globalNomUsuario;
    }

    public static void setGlobalNomUsuario(String globalNomUsuario) {
        GlobalAplicacion.globalNomUsuario = globalNomUsuario;
    }


}
