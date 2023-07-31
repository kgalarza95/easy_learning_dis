package com.example.proyfragmentmodal.util;

import android.app.Application;

public class GlobalAplicacion {

    public static String IP = "192.168.100.185";
    private static String globalUsuario;
    private static String globalPassword;
    private static String globalNomUsuario;
    private static int globalIdUsuario;
    private static int globalNumCurso;

    //pantalla de juegos
    public static long miliSegundos;
    public static String nivel;
    public static String esEstudiante = "N";


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

    public static int getGlobalNumCurso() {
        return globalNumCurso;
    }

    public static void setGlobalNumCurso(int globalNumCurso) {
        GlobalAplicacion.globalNumCurso = globalNumCurso;
    }
}
