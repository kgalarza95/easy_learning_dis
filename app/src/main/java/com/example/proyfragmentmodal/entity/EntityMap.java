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

    private String ID_MENSAJE_RELACION;
    private String ID_DUENIO_SMS;

    private String PALABRA_INC;
    private String PALABRA;
    private String OP1;
    private String OP2;
    private String OP3;
    private String OP4;
    private String OP_CORRECTA;

    private String  FRASE;
    private String  PALABRA_ERRADA;
    private String  PALABRA_CORRECTA;

    private String  TITULO;
    private String  INSTRUCCIONES;
    private String  FECHA_VENCIMIENTO;
    private String  SCORE;

    private String  ID_JUEGO;
    private String  NOMBRE_JUEGO;

    private String  ENTREGA;
    private String  FECHA_ENTREGA;
    private String  CALIFICACION;
    private String  RUTA_FILE_TAREA;

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

    public String getID_MENSAJE_RELACION() {
        return ID_MENSAJE_RELACION;
    }

    public void setID_MENSAJE_RELACION(String ID_MENSAJE_RELACION) {
        this.ID_MENSAJE_RELACION = ID_MENSAJE_RELACION;
    }

    public String getID_DUENIO_SMS() {
        return ID_DUENIO_SMS;
    }

    public void setID_DUENIO_SMS(String ID_DUENIO_SMS) {
        this.ID_DUENIO_SMS = ID_DUENIO_SMS;
    }

    public String getPALABRA_INC() {
        return PALABRA_INC;
    }

    public void setPALABRA_INC(String PALABRA_INC) {
        this.PALABRA_INC = PALABRA_INC;
    }

    public String getPALABRA() {
        return PALABRA;
    }

    public void setPALABRA(String PALABRA) {
        this.PALABRA = PALABRA;
    }

    public String getOP1() {
        return OP1;
    }

    public void setOP1(String OP1) {
        this.OP1 = OP1;
    }

    public String getOP2() {
        return OP2;
    }

    public void setOP2(String OP2) {
        this.OP2 = OP2;
    }

    public String getOP3() {
        return OP3;
    }

    public void setOP3(String OP3) {
        this.OP3 = OP3;
    }

    public String getOP4() {
        return OP4;
    }

    public void setOP4(String OP4) {
        this.OP4 = OP4;
    }

    public String getOP_CORRECTA() {
        return OP_CORRECTA;
    }

    public void setOP_CORRECTA(String OP_CORRECTA) {
        this.OP_CORRECTA = OP_CORRECTA;
    }

    public String getFRASE() {
        return FRASE;
    }

    public void setFRASE(String FRASE) {
        this.FRASE = FRASE;
    }

    public String getPALABRA_ERRADA() {
        return PALABRA_ERRADA;
    }

    public void setPALABRA_ERRADA(String PALABRA_ERRADA) {
        this.PALABRA_ERRADA = PALABRA_ERRADA;
    }

    public String getPALABRA_CORRECTA() {
        return PALABRA_CORRECTA;
    }

    public void setPALABRA_CORRECTA(String PALABRA_CORRECTA) {
        this.PALABRA_CORRECTA = PALABRA_CORRECTA;
    }

    public String getTITULO() {
        return TITULO;
    }

    public void setTITULO(String TITULO) {
        this.TITULO = TITULO;
    }

    public String getINSTRUCCIONES() {
        return INSTRUCCIONES;
    }

    public void setINSTRUCCIONES(String INSTRUCCIONES) {
        this.INSTRUCCIONES = INSTRUCCIONES;
    }

    public String getFECHA_VENCIMIENTO() {
        return FECHA_VENCIMIENTO;
    }

    public void setFECHA_VENCIMIENTO(String FECHA_VENCIMIENTO) {
        this.FECHA_VENCIMIENTO = FECHA_VENCIMIENTO;
    }

    public String getSCORE() {
        return SCORE;
    }

    public void setSCORE(String SCORE) {
        this.SCORE = SCORE;
    }

    public String getID_JUEGO() {
        return ID_JUEGO;
    }

    public void setID_JUEGO(String ID_JUEGO) {
        this.ID_JUEGO = ID_JUEGO;
    }

    public String getNOMBRE_JUEGO() {
        return NOMBRE_JUEGO;
    }

    public void setNOMBRE_JUEGO(String NOMBRE_JUEGO) {
        this.NOMBRE_JUEGO = NOMBRE_JUEGO;
    }

    public String getENTREGA() {
        return ENTREGA;
    }

    public void setENTREGA(String ENTREGA) {
        this.ENTREGA = ENTREGA;
    }

    public String getFECHA_ENTREGA() {
        return FECHA_ENTREGA;
    }

    public void setFECHA_ENTREGA(String FECHA_ENTREGA) {
        this.FECHA_ENTREGA = FECHA_ENTREGA;
    }

    public String getCALIFICACION() {
        return CALIFICACION;
    }

    public void setCALIFICACION(String CALIFICACION) {
        this.CALIFICACION = CALIFICACION;
    }

    public String getRUTA_FILE_TAREA() {
        return RUTA_FILE_TAREA;
    }

    public void setRUTA_FILE_TAREA(String RUTA_FILE_TAREA) {
        this.RUTA_FILE_TAREA = RUTA_FILE_TAREA;
    }

    @Override
    public String toString() {
        return "EntityMap=================> "+this.getNOMBRES()+"-"+this.getNOMBRE();
    }
}
