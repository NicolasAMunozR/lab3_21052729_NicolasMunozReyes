package org.example;

import java.util.Scanner;

public class Main{

    public static void main(String[] args) {
        var filesystem = new FileSystem("");
        Scanner s = new Scanner(System.in);


        int eleccion;
        do{
            Menu();
            eleccion = s.nextInt();
            s.nextLine();
            switch (eleccion){
                case 0:
                    System.out.println("Salir");
                    System.exit(0);
                    break;

                case 1:
                    System.out.println("ingrese nombre del sistema: ");
                    var nombre = s.nextLine();
                    filesystem = new FileSystem(nombre);
                    System.out.println(filesystem);
                    break;

                case 2:
                    System.out.println("Ingrese letra de la unidad: ");
                    var letra = s.nextLine();
                    if (esLetra(letra)) {
                        char letra1 = letra.charAt(0);
                        String letraString = Character.toString(letra1);
                        System.out.println("Ingrese nombre de la unidad: ");
                        var name = s.nextLine();
                        System.out.println("Ingrese capacidad de la unidad: ");
                        var capacidad = s.nextLine();
                        if (esEntero(capacidad)) {
                            int numero = Integer.parseInt(capacidad);
                            filesystem.addDrive(letraString, name, numero);
                        } else {
                            System.out.println("El valor ingresado en la capacidad no corresponde a un valor int.");
                        }
                    } else {
                        System.out.println("El valor ingresado para la letra de la unidad no corresponde a una letra .");
                    }
                    System.out.println(filesystem);
                    break;

                case 3:
                    System.out.println("Ingrese nombre del usuario: ");
                    var user_name = s.nextLine();
                    filesystem.register(user_name);
                    System.out.println(filesystem);
                    break;

                case 4:
                    System.out.println("Ingrese nombre del usuario con el que quiere iniciar sesión: ");
                    var user_log = s.nextLine();
                    filesystem.login(user_log);
                    System.out.println(filesystem);
                    break;

                case 5:
                    filesystem.logout();
                    System.out.println(filesystem);
                    break;

                case 6:
                    System.out.println("Ingrese letra de la unidad a iniciar: ");
                    var letra_switch = s.nextLine();
                    if (esLetra(letra_switch)) {
                        char letra1 = letra_switch.charAt(0);
                        String letraString = Character.toString(letra1);
                        filesystem.switchdrive(letraString);
                    }
                    else {
                        System.out.println("El valor ingresado para la letra a iniciar no corresponde a una letra .");
                    }
                    System.out.println(filesystem);
                    break;

                case 7:
                    System.out.println("Ingrese nombre de la carpeta: ");
                    var carpeta = s.nextLine();
                    filesystem.mkdir(carpeta);
                    System.out.println(filesystem);
                    break;

                default:
                    System.out.println(eleccion + " No");
                    System.exit(0);
            }
        }
        while (true);
    }
    private static void Menu(){
        System.out.print("Menu \n");
        System.out.print("0. Salir \n");
        System.out.print("1. Ingresar nombre sistema \n");
        System.out.print("2. Ingresar unidad Drive \n");
        System.out.print("3. Ingresar usuario \n");
        System.out.print("4. Ingresar usuario para iniciar sesión \n");
        System.out.print("5. Cerrar sesión \n");
        System.out.print("6. Ingresar letra para iniciar ruta \n");
        System.out.print("7. Ingresar nombre de la carpeta a crear \n");
    }
    public static boolean esEntero(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static boolean esLetra(String input) {
        if (input.length() != 1) {
            return false;
        }
        char caracter = input.charAt(0);
        return Character.isLetter(caracter);
    }
}
        //filesystem.addDrive("C", "Si", 2);
        //filesystem.addDrive("D", "No", 3);
//filesystem.addDrive("C", "Si", 2);
//filesystem.addDrive("E", "No", 5);
//filesystem.register("NOs");
//filesystem.register("NOs");
//filesystem.register("Ns");
//filesystem.login("Ns");
//filesystem.login("NOs");
//filesystem.logout();
//filesystem.login("NOs");
//filesystem.switchdrive("C");
//filesystem.switchdrive("D");
//filesystem.mkdir("gfd");
//System.out.println(filesystem);