package org.example;

public class Login {
    private final String nombre;
    public Login(String user_login) {
       this.nombre = user_login;
    }
    @Override
    public String toString() {
        return "Ususario logeado = {" +
                nombre + '}';
    }

    public String getnombre() {
        return nombre;
    }
}