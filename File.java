package org.example;

public class File {
    private String nombre;
    private String extension;
    private String contenido;
    public File(String nombre, String extension, String contenido) {
        this.nombre = nombre;
        this.extension = extension;
        this.contenido = contenido;
    }

    public String getContenido() {
        return contenido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getExtension() {
        return extension;
    }

    @Override
    public String toString() {
        return "Archivo = {" +
                "Nombre = " + nombre +
                ", extensión = " + extension +
                ", contenido = " + contenido +
                '}';
    }
}