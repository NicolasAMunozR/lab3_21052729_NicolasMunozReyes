package org.example;

import java.util.Date;

public class Folder extends Mkdir {
    private File nombre;
    private Date fechaCreacion;
    private Date fechamodificacion;
    private String prueba;

    public void setNombre(File nombre) {
        this.nombre = nombre;
    }

    public Folder(File nombre, String usuario_log, String direccion) {
        super(direccion, usuario_log);
        this.fechaCreacion = new Date();
        this.fechamodificacion = new Date();
        this.nombre = nombre;
    }

    @Override
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Override
    public void setFechamodificacion(Date fechamodificacion) {
        this.fechamodificacion = fechamodificacion;
    }

    public File getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        prueba ="Carpeta = {" +
                getDireccion() + ", " + nombre +
                ", Fecha de creación = " + fechaCreacion +
                ", Fecha de ultima modificación = " + fechamodificacion +
                ", Usuario = " + getUsuario_log() +
                "}\n";
        return prueba;
    }
    public String getPrueba() {
        return prueba;
    }
}