package org.example;

public class Switchdrive {
    String letra;
    public Switchdrive(String letra) {
        this.letra = letra;
    }
    @Override
    public String toString() {
        return "Dirección = " +
                  letra + ":/" ;
    }

    public String getLetra() {
        return letra;
    }
}
