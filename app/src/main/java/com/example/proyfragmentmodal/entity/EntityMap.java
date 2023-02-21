package com.example.proyfragmentmodal.entity;

public class EntityMap {

    private String ID;
    private String ID_PROFESOR;
    private String NOMBRE;
    private String DESCRIPCION;
    private String ANIO;
    private String ESTADO;
    private String esRemitente;
    private String esEmisor;
    private String mensaje;
    private String ID_CURSO;
    private String RUTA;


    private String USUARIO;
    private String NOMBRES;
    private String APELLIDOS;


    public EntityMap() {
    }

    public EntityMap(String esRemitente, String esEmisor, String mensaje) {
        this.esRemitente = esRemitente;
        this.esEmisor = esEmisor;
        this.mensaje = mensaje;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getID_PROFESOR() {
        return ID_PROFESOR;
    }

    public void setID_PROFESOR(String ID_PROFESOR) {
        this.ID_PROFESOR = ID_PROFESOR;
    }

    public String getNOMBRE() {
        return NOMBRE;
    }

    public void setNOMBRE(String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }

    public String getDESCRIPCION() {
        return DESCRIPCION;
    }

    public void setDESCRIPCION(String DESCRIPCION) {
        this.DESCRIPCION = DESCRIPCION;
    }

    public String getANIO() {
        return ANIO;
    }

    public void setANIO(String ANIO) {
        this.ANIO = ANIO;
    }

    public String getESTADO() {
        return ESTADO;
    }

    public void setESTADO(String ESTADO) {
        this.ESTADO = ESTADO;
    }

    public String getEsRemitente() {
        return esRemitente;
    }

    public void setEsRemitente(String esRemitente) {
        this.esRemitente = esRemitente;
    }

    public String getEsEmisor() {
        return esEmisor;
    }

    public void setEsEmisor(String esEmiso) {
        this.esEmisor = esEmiso;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getID_CURSO() {
        return ID_CURSO;
    }

    public void setID_CURSO(String ID_CURSO) {
        this.ID_CURSO = ID_CURSO;
    }

    public String getRUTA() {
        return RUTA;
    }

    public void setRUTA(String RUTA) {
        this.RUTA = RUTA;
    }

    public String getUSUARIO() {
        return USUARIO;
    }

    public void setUSUARIO(String USUARIO) {
        this.USUARIO = USUARIO;
    }

    public String getNOMBRES() {
        return NOMBRES;
    }

    public void setNOMBRES(String NOMBRES) {
        this.NOMBRES = NOMBRES;
    }

    public String getAPELLIDOS() {
        return APELLIDOS;
    }

    public void setAPELLIDOS(String APELLIDOS) {
        this.APELLIDOS = APELLIDOS;
    }

    @Override
    public String toString() {
        return "EntityMap{" +
                "ID='" + ID + '\'' +
                ", ID_PROFESOR='" + ID_PROFESOR + '\'' +
                ", NOMBRE='" + NOMBRE + '\'' +
                ", DESCRIPCION='" + DESCRIPCION + '\'' +
                ", ANIO='" + ANIO + '\'' +
                ", ESTADO='" + ESTADO + '\'' +
                ", esRemitente='" + esRemitente + '\'' +
                ", esEmisor='" + esEmisor + '\'' +
                ", mensaje='" + mensaje + '\'' +
                ", ID_CURSO='" + ID_CURSO + '\'' +
                ", RUTA='" + RUTA + '\'' +
                ", USUARIO='" + USUARIO + '\'' +
                ", NOMBRES='" + NOMBRES + '\'' +
                ", APELLIDOS='" + APELLIDOS + '\'' +
                '}';
    }
}
