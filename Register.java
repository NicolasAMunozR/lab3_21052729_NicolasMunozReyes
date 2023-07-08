package org.example;

public class Register {
    private final String usuario;
    public Register(String usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Usuario = {" +
                "Nombre = " + usuario +
                '}';
    }
    public String getUsuario() {
        return usuario;
    }
}