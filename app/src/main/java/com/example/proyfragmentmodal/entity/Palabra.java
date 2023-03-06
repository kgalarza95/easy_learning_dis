package com.example.proyfragmentmodal.entity;

public class Palabra {

    String palabra1;
    String palabra2;
    String palabra3;
    String palabraCorrecta;

    public Palabra(String palabra1, String palabra2, String palabra3, String palabraCorrecta) {
        this.palabra1 = palabra1;
        this.palabra2 = palabra2;
        this.palabra3 = palabra3;
        this.palabraCorrecta = palabraCorrecta;
    }

    public String getPalabra1() {
        return palabra1;
    }

    public void setPalabra1(String palabra1) {
        this.palabra1 = palabra1;
    }

    public String getPalabra2() {
        return palabra2;
    }

    public void setPalabra2(String palabra2) {
        this.palabra2 = palabra2;
    }

    public String getPalabra3() {
        return palabra3;
    }

    public void setPalabra3(String palabra3) {
        this.palabra3 = palabra3;
    }

    public String getPalabraCorrecta() {
        return palabraCorrecta;
    }

    public void setPalabraCorrecta(String palabraCorrecta) {
        this.palabraCorrecta = palabraCorrecta;
    }
}
