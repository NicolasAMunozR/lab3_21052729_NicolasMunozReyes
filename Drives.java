package org.example;

public class Drives {
    String letra;
    String name;
    int capacidad;

    public Drives(String letra, String name, int capacidad){
        this.letra = letra;
        this.name = name;
        this.capacidad = capacidad;
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
