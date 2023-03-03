package com.example.proyfragmentmodal.entity;

public class Cuento {

    String pregunta1;
    String op1;
    String op2;
    String opCorrecta;

    String pregunta2;
    String op2_1;
    String op2_2;
    String opCorrecta2;

    public Cuento() {
    }

    public Cuento(String pregunta1, String op1, String op2, String opCorrecta, String pregunta2, String op2_1, String op2_2, String opCorrecta2) {
        this.pregunta1 = pregunta1;
        this.op1 = op1;
        this.op2 = op2;
        this.opCorrecta = opCorrecta;
        this.pregunta2 = pregunta2;
        this.op2_1 = op2_1;
        this.op2_2 = op2_2;
        this.opCorrecta2 = opCorrecta2;
    }

    public String getPregunta1() {
        return pregunta1;
    }

    public void setPregunta1(String pregunta1) {
        this.pregunta1 = pregunta1;
    }

    public String getOp1() {
        return op1;
    }

    public void setOp1(String op1) {
        this.op1 = op1;
    }

    public String getOp2() {
        return op2;
    }

    public void setOp2(String op2) {
        this.op2 = op2;
    }

    public String getOpCorrecta() {
        return opCorrecta;
    }

    public void setOpCorrecta(String opCorrecta) {
        this.opCorrecta = opCorrecta;
    }

    public String getPregunta2() {
        return pregunta2;
    }

    public void setPregunta2(String pregunta2) {
        this.pregunta2 = pregunta2;
    }

    public String getOp2_1() {
        return op2_1;
    }

    public void setOp2_1(String op2_1) {
        this.op2_1 = op2_1;
    }

    public String getOp2_2() {
        return op2_2;
    }

    public void setOp2_2(String op2_2) {
        this.op2_2 = op2_2;
    }

    public String getOpCorrecta2() {
        return opCorrecta2;
    }

    public void setOpCorrecta2(String opCorrecta2) {
        this.opCorrecta2 = opCorrecta2;
    }

    @Override
    public String toString() {
        return "Cuento{" +
                "pregunta1='" + pregunta1 + '\'' +
                ", op1='" + op1 + '\'' +
                ", op2='" + op2 + '\'' +
                ", opCorrecta='" + opCorrecta + '\'' +
                ", pregunta2='" + pregunta2 + '\'' +
                ", op2_1='" + op2_1 + '\'' +
                ", op2_2='" + op2_2 + '\'' +
                ", opCorrecta2='" + opCorrecta2 + '\'' +
                '}';
    }
}
