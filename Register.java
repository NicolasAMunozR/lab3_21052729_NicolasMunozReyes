package org.example;

public class Register {
    String usuario;
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
