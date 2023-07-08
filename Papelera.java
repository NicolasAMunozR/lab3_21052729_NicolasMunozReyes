package org.example;

public class Papelera {
    private final Folder elemento;
    public Folder getElemento() {
        return elemento;
    }
    public Papelera(Folder elemento) {
        this.elemento = elemento;
    }
    @Override
    public String toString() {
        return "papelera = {" +
                elemento +
                "}";
    }
}