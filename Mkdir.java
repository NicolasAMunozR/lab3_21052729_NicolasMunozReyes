package org.example;

import java.util.Date;

public class Mkdir {
    String nombre;
    Date fechaCreacion;
    String usuario_log;
    Switchdrive ruta;
    public Mkdir(String nombre, String usuario_log, Switchdrive ruta) {
        this.nombre = nombre;
        this.fechaCreacion = new Date();
        this.usuario_log = usuario_log;
        this.ruta = ruta;
    }
    @Override
    public String toString() {
        return "Carpeta = {" +
                ruta + nombre + "/" +
                ", Fecha = " + fechaCreacion +
                ", Usuario = " + usuario_log +
                '}';
    }
}
