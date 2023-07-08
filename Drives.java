package org.example;

public class Drives {
    private final String letra;
    private String name;
    private final int capacidad;
    public Drives(String letra, String name, int capacidad){
        this.letra = letra;
        this.name = name;
        this.capacidad = capacidad;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Drive = {" +
                "Letra = " + letra +
                ", Nombre = " + name +
                " Capacidad = " + capacidad +
                '}';
    }
    public String getletra() {
        return letra;
    }
}