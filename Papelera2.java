package org.example;

public class Papelera2 {
    private final Mkdir elemento;

    public Mkdir getElemento() {
        return elemento;
    }

    public Papelera2(Mkdir elemento) {
        this.elemento = elemento;
    }
    @Override
    public String toString() {
        return "papelera = {" +
                elemento +
                "}";
    }
}