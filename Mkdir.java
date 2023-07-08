package org.example;

import java.util.Date;
public class Mkdir {
    private Date fechaCreacion;
    private Date fechamodificacion;
    private final String usuario_log;
    private String direccion;
    private String prueba;
    public String getDireccion() {
        return direccion;
    }
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public Mkdir(String direccion, String usuario_log) {
        this.direccion = direccion;
        this.fechaCreacion = new Date();
        this.fechamodificacion = new Date();
        this.usuario_log = usuario_log;
    }

    public String getPrueba() {
        return prueba;
    }

    public String getUsuario_log() {
        return usuario_log;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setFechamodificacion(Date fechamodificacion) {
        this.fechamodificacion = fechamodificacion;
    }

    @Override
    public String toString() {
        prueba = "Carpeta = {" +
                direccion +
                ", Fecha de creación = " + fechaCreacion +
                ", Fecha de ultima modificación = " + fechamodificacion +
                ", Usuario = " + usuario_log +
                "}\n";
        return prueba;
    }
}