package org.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class FileSystem {
    static String nombre;
    Date fechaCreacion;
    List<Drives> drives;
    List<Register> registers;
    List<Login> logins;
    List<Switchdrive> switchdrives;
    List<Mkdir> mkdirs;

    public static String getNombre() {
        return nombre;
    }

    public FileSystem(String nombre) {
        FileSystem.nombre = nombre;
        if(!Objects.equals(nombre, "")) {
            this.fechaCreacion = new Date();
        }
        this.drives = new ArrayList<>();
        this.registers = new ArrayList<>();
        this.logins = new ArrayList<>();
        this.switchdrives = new ArrayList<>();
        this.mkdirs = new ArrayList<>();
    }

    public void addDrive(String letra, String name, int capacidad){
        var drive = new Drives(letra, name, capacidad);
        List<String> currentletra = new ArrayList<>();
        for (Drives disco : drives){
            currentletra.add(disco.getletra());
        }
        if(!Objects.equals(FileSystem.getNombre(), "")) {
            if (!currentletra.contains(letra)) {
                drives.add(drive);
            }
        }
    }

    public void register(String usuario){
        var user = new Register(usuario);
        List<String> currentuser = new ArrayList<>();
        for (Register disco : registers){
            currentuser.add(disco.getUsuario());
        }
        if (drives.size() != 0) {
            if(!Objects.equals(FileSystem.getNombre(), "")) {
                if (!currentuser.contains(usuario)) {
                    registers.add(user);
                }
            }
        }
    }

    public void login(String user_login){
        var log = new Login(user_login);
        List<String> currentu = new ArrayList<>();
        for (Register disco : registers){
            currentu.add(disco.getUsuario());
        }
        if (currentu.contains(user_login)) {
            if (logins.size() == 0){
                logins.add(log);
            }
        }
    }
    public void logout(){
        if (logins.size() != 0){
            logins.clear();
        }
    }
    public void switchdrive(String letra_ruta){
        var ruta = new Switchdrive(letra_ruta);
        List<String> currentruta = new ArrayList<>();
        for (Drives disco : drives){
            currentruta.add(disco.getletra());
        }
        if (currentruta.contains(letra_ruta)) {
            if (logins.size() != 0){
                if (switchdrives.size() == 0) {
                    switchdrives.add(ruta);
                }
                switchdrives.set(0,ruta);
            }
        }
    }
    public void mkdir(String nombre_carpeta){
        List<String> currentrut = new ArrayList<>();
        for (Login dis : logins){
            currentrut.add(dis.getnombre());
        }
        if(switchdrives.size() != 0) {
            var mk = new Mkdir(nombre_carpeta, currentrut.get(0), switchdrives.get(0));
            mkdirs.add(mk);
        }
    }
    public void cd(String parametro){
    }
    @Override
    public String toString() {
        return "FileSystem{" +
                "Nombre del Sistema = " + nombre +
                ", Fecha de creación = " + fechaCreacion +
                ", Drives = " + drives +
                ", Usuarios = " + registers +
                ", Login = " + logins +
                ", Ruta = " + switchdrives +
                ", Carpetas = " + mkdirs +
                '}';
    }
}